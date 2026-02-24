package pl.edu.pk.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AdDTO {
    @NotBlank(message = "Tytuł nie może być pusty")
    @Size(min = 5, max = 100, message = "Tytuł musi mieć od 5 do 100 znaków")
    private String title;

    @NotBlank(message = "Opis nie może być pusty")
    private String description;

    private String category;

    // Gettery i Settery
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}