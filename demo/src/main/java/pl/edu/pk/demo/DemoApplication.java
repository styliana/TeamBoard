package pl.edu.pk.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.edu.pk.demo.entity.Ad;
import pl.edu.pk.demo.entity.User;
import pl.edu.pk.demo.repository.AdRepository;
import pl.edu.pk.demo.repository.UserRepository;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(AdRepository adRepo, UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            // 1. Czyścimy bazę, żeby nie było duplikatów
            adRepo.deleteAll();
            userRepo.deleteAll();

            // 2. Dodajemy ogłoszenia (Wymóg 3.0)
            adRepo.save(new Ad("Espresso u Styliany", "Debugowanie i kawa!", "Kraków", "Styliana"));
            adRepo.save(new Ad("Java Masterclass", "Spring Boot od podstaw.", "Warszawa", "Admin"));

            // 3. Dodajemy użytkownika z haszowaniem (Wymóg 4.0/5.0)
            String hashedPw = encoder.encode("kawa2026");
            userRepo.save(new User("olunia", hashedPw));

            System.out.println("✅ Baza gotowa! Login: olunia, Hasło: kawa2026");
        };
    }
}