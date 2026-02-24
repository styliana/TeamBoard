package pl.edu.pk.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String category; // To nasze "Kraków" lub kategoria

    // 1. Pusty konstruktor - WYMAGANY przez JPA (Hibernate)
    public Ad() {
    }

    // 2. Konstruktor z argumentami - To jego brakował w DemoApplication!
    public Ad(String title, String description, String category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }

    // Gettery i Settery (potrzebne, żeby dane "płynęły" do Angulara)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}