package pl.edu.pk.demo.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.demo.dto.AdRequestDTO; // To naprawia błąd "cannot find symbol"
import pl.edu.pk.demo.entity.Ad;
import pl.edu.pk.demo.repository.AdRepository;
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
    public Ad createAd(@Valid @RequestBody AdRequestDTO dto) {
        // Mapowanie DTO na encję [cite: 42]
        Ad ad = new Ad(dto.title(), dto.description(), dto.category(), dto.author());
        return repository.save(ad);
    }

    @PatchMapping("/{id}/join")
    public Ad joinAd(@PathVariable Long id) {
        Ad ad = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono ogłoszenia!"));
        ad.setParticipantsCount(ad.getParticipantsCount() + 1);
        return repository.save(ad);
    }
}