package pl.edu.pk.demo.controller;

import jakarta.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.demo.dto.AdRequestDTO;
import pl.edu.pk.demo.entity.Ad;
import pl.edu.pk.demo.service.AdService;
import pl.edu.pk.demo.repository.AdRepository;
import pl.edu.pk.demo.event.UserJoinedEvent;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/ads")
@CrossOrigin(origins = "http://localhost:4200")
public class AdController {
    private final AdService adService;
    private final AdRepository repository;
    private final ApplicationEventPublisher eventPublisher; // Narzędzie do wysyłania zdarzeń

    // Dodaliśmy eventPublisher do konstruktora
    public AdController(AdService adService, AdRepository repository, ApplicationEventPublisher eventPublisher) {
        this.adService = adService;
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping
    public List<Ad> getAllAds() {
        return adService.getAllAds();
    }

    @PostMapping
    public Ad createAd(@Valid @RequestBody AdRequestDTO dto, Principal principal) {
        return adService.createAd(dto, principal.getName());
    }

    @PatchMapping("/{id}/join")
    public Ad joinAd(@PathVariable Long id, Principal principal) {
        Ad ad = repository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono!"));
        if (!ad.getParticipantNames().contains(principal.getName())) {
            ad.getParticipantNames().add(principal.getName());
            Ad savedAd = repository.save(ad);

            // KLUCZOWE: Publikujemy zdarzenie!
            eventPublisher.publishEvent(new UserJoinedEvent(this, ad.getTitle(), ad.getAuthor(), principal.getName()));

            return savedAd;
        }
        return ad;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAd(@PathVariable Long id, Principal principal) {
        Ad ad = repository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono!"));
        if (ad.getAuthor().equals(principal.getName())) {
            repository.delete(ad);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(403).build();
    }
}