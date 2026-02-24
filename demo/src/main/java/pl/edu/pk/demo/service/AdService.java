package pl.edu.pk.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pk.demo.dto.AdDTO;
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

    public Ad createAd(AdDTO adDTO) {
        Ad ad = new Ad();
        ad.setTitle(adDTO.getTitle());
        ad.setDescription(adDTO.getDescription());
        ad.setCategory(adDTO.getCategory());
        return adRepository.save(ad);
    }
}