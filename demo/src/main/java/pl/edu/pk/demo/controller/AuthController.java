package pl.edu.pk.demo.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.demo.dto.UserRegisterDTO;
import pl.edu.pk.demo.entity.User;
import pl.edu.pk.demo.repository.UserRepository;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterDTO dto) {
        if (userRepository.findByUsername(dto.username()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Użytkownik już istnieje!"));
        }

        User user = new User(dto.username(), passwordEncoder.encode(dto.password()));
        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "Zarejestrowano pomyślnie!"));
    }
}