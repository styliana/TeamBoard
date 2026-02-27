package pl.edu.pk.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pk.demo.dto.AdRequestDTO;
import pl.edu.pk.demo.entity.Ad;
import pl.edu.pk.demo.repository.AdRepository;
import pl.edu.pk.demo.service.AdService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdServiceTest {

    @Mock
    private AdRepository adRepository; // "Udawana" baza danych

    @InjectMocks
    private AdService adService; // Prawdziwy serwis, który testujemy

    private AdRequestDTO testDto;
    private Ad testAd;

    @BeforeEach
    void setUp() {
        // Przygotowanie danych testowych przed każdym testem
        testDto = new AdRequestDTO("Kawa w Krakowie", "Szukam chętnych na szybkie espresso", "Kraków");

        testAd = new Ad();
        testAd.setId(1L);
        testAd.setTitle(testDto.title());
        testAd.setDescription(testDto.description());
        testAd.setCategory(testDto.category());
        testAd.setAuthor("janek123");
    }

    @Test
    void shouldReturnAllAds() {
        // Given (Zakładamy, że baza zwraca jedną listę)
        when(adRepository.findAll()).thenReturn(Arrays.asList(testAd));

        // When (Wykonujemy logikę serwisu)
        List<Ad> ads = adService.getAllAds();

        // Then (Sprawdzamy, czy wynik jest poprawny)
        assertEquals(1, ads.size(), "Powinno zwrócić jedno ogłoszenie");
        assertEquals("Kawa w Krakowie", ads.get(0).getTitle());

        // Weryfikacja, czy serwis faktycznie odpytał repozytorium
        verify(adRepository).findAll();
    }

    @Test
    void shouldCreateAdWithCorrectAuthor() {
        // Given
        String currentLoggedUser = "admin_user";

        // Symulacja zapisu do bazy - zwracamy to, co weszło + nadajemy ID
        when(adRepository.save(any(Ad.class))).thenAnswer(invocation -> {
            Ad savedAd = invocation.getArgument(0);
            savedAd.setId(99L);
            return savedAd;
        });

        // When (Próba utworzenia ogłoszenia przez serwis)
        Ad result = adService.createAd(testDto, currentLoggedUser);

        // Then (Sprawdzenie, czy serwis dobrze zmapował obiekt)
        assertNotNull(result);
        assertEquals(99L, result.getId(), "Ogłoszenie powinno dostać ID po zapisie");
        assertEquals("Kawa w Krakowie", result.getTitle());

        // Najważniejszy test bezpieczeństwa: czy autor został nadpisany z sesji?
        assertEquals("admin_user", result.getAuthor(), "Autor musi zgadzać się z przekazanym loginem z sesji");

        // Sprawdzenie czy kolekcja uczestników nie jest nullem (ochrona przed błędem 500)
        assertNotNull(result.getParticipantNames(), "Lista uczestników nie może być nullem");
        assertTrue(result.getParticipantNames().isEmpty(), "Nowe ogłoszenie nie powinno mieć uczestników");

        // Weryfikacja, czy metoda save została wywołana dokładnie raz
        verify(adRepository).save(any(Ad.class));
    }
}