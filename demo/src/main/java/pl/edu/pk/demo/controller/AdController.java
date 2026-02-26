package pl.edu.pk.demo.controller;

import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public Ad createAd(@RequestBody Ad ad, java.security.Principal principal) {
        ad.setAuthor(principal.getName()); // Automatyczne ustawienie autora z sesji
        return repository.save(ad);
    }

    @PatchMapping("/{id}/join")
    public Ad joinAd(@PathVariable Long id, java.security.Principal principal) {
        Ad ad = repository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono!"));
        String user = principal.getName();

        // Jeśli użytkownika nie ma na liście, dodaj go (blokuje multiklik)
        if (!ad.getParticipantNames().contains(user)) {
            ad.getParticipantNames().add(user);
            return repository.save(ad);
        }
        return ad;
    }

    @DeleteMapping("/{id}")
    public org.springframework.http.ResponseEntity<?> deleteAd(@PathVariable Long id, java.security.Principal principal) {
        Ad ad = repository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono!"));

        // Tylko właściciel może usunąć (bezpieczeństwo 4.0+)
        if (ad.getAuthor().equals(principal.getName())) {
            repository.delete(ad);
            return org.springframework.http.ResponseEntity.ok().build();
        }
        return org.springframework.http.ResponseEntity.status(403).build();
    }
}