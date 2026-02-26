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

        };
    }
}