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

    @Bean // Ta adnotacja mówi Springowi, żeby uruchomił tę metodę przy starcie
    CommandLineRunner init(AdRepository repository) {
        return args -> {
            // Czyścimy bazę przed dodaniem, żeby nie dublować ogłoszeń przy każdym restarcie
            repository.deleteAll();

            // Dodajemy testowe ogłoszenia
            repository.save(new Ad("Kawa u Styliany", "Zapraszam na espresso i wspólne debugowanie frontendu!", "Kraków"));
            repository.save(new Ad("Java Masterclass", "Nauka Spring Boota przy dobrej kawie.", "Warszawa"));

            System.out.println("☕ Kawusia została zaparzona i dodana do bazy PostgreSQL!");
        };
    }
}