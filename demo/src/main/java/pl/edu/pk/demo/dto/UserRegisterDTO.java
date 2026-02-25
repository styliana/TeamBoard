package pl.edu.pk.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(
        @NotBlank(message = "Login jest wymagany")
        @Size(min = 3, message = "Login musi mieć min. 3 znaki")
        String username,

        @NotBlank(message = "Hasło jest wymagane")
        @Size(min = 6, message = "Hasło musi mieć min. 6 znaków")
        String password
) {}