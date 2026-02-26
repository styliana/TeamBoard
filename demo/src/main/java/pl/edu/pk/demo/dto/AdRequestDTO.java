package pl.edu.pk.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AdRequestDTO(
        @NotBlank(message = "Tytuł nie może być pusty!")
        @Size(min = 3, max = 50, message = "Tytuł musi mieć od 3 do 50 znaków")
        String title,

        @NotBlank(message = "Opis jest wymagany!")
        @Size(min = 10, message = "Opis musi mieć min. 10 znaków")
        String description,

        @NotBlank(message = "Podaj miasto!")
        String category
) {}