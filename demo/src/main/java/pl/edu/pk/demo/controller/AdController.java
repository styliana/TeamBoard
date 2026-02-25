package pl.edu.pk.demo.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.pk.demo.entity.Ad;
import pl.edu.pk.demo.repository.AdRepository;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/ads")
@CrossOrigin(origins = "http://localhost:4200")
public class AdController {
    private final AdRepository repository;

    public AdController(AdRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Ad> getAllAds() {
        return repository.findAll();
    }

    @PatchMapping("/{id}/join")
    public Ad joinAd(@PathVariable Long id, Principal principal) {
        Ad ad = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono zaproszenia!"));

        String currentUsername = principal.getName();

        // BLOKADA: Jeśli użytkownik już jest na liście, nic nie rób
        if (ad.getParticipantNames().contains(currentUsername)) {
            return ad;
        }

        // Dodajemy użytkownika do zbioru (Set obsłuży unikalność)
        ad.getParticipantNames().add(currentUsername);

        System.out.println("Użytkownik " + currentUsername + " dołączył do ID " + id);
        return repository.save(ad);
    }
}