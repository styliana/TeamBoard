package pl.edu.pk.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.demo.dto.AdDTO;
import pl.edu.pk.demo.entity.Ad;
import pl.edu.pk.demo.service.AdService;
import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdController {

    @Autowired
    private AdService adService;

    @GetMapping
    public List<Ad> getAllAds() {
        return adService.getAllAds();
    }

    @PostMapping
    public Ad createAd(@Valid @RequestBody AdDTO adDTO) {
        return adService.createAd(adDTO);
    }
}