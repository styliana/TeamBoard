package pl.edu.pk.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pk.demo.entity.Ad;

public interface AdRepository extends JpaRepository<Ad, Long> {
}