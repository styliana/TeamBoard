package pl.edu.pk.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.edu.pk.demo.entity.Ad;
import pl.edu.pk.demo.repository.AdRepository;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(AdRepository repository) {
        return args -> {
            repository.deleteAll();
            repository.save(new Ad("Espresso u Styliany", "Debugowanie i kawa!", "Kraków", "Styliana"));
            repository.save(new Ad("Java Masterclass", "Spring Boot od podstaw.", "Warszawa", "Admin"));
        };
    }
}