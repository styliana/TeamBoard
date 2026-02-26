package pl.edu.pk.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pk.demo.dto.AdRequestDTO;
import pl.edu.pk.demo.entity.Ad;
import pl.edu.pk.demo.repository.AdRepository;
import java.util.List;

@Service
public class AdService {
    @Autowired
    private AdRepository adRepository;

    public List<Ad> getAllAds() {
        return adRepository.findAll();
    }

    public Ad createAd(AdRequestDTO dto, String authorName) {
        Ad ad = new Ad();
        ad.setTitle(dto.title());
        ad.setDescription(dto.description());
        ad.setCategory(dto.category());
        ad.setAuthor(authorName); // Bezpieczne przypisanie autora
        return adRepository.save(ad);
    }
}