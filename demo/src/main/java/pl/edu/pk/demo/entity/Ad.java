package pl.edu.pk.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String category;
    private String author;

    // Przechowujemy unikalne nazwy użytkowników (wymóg logiczny na 5.0)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> participantNames = new HashSet<>();

    public Ad() {}

    public Ad(String title, String description, String category, String author) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.author = author;
    }

    // Gettery i Settery (naprawiają błędy kompilacji w AdService.java)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Set<String> getParticipantNames() { return participantNames; }

    // Zwraca rozmiar kolekcji dla frontendu
    public int getParticipantsCount() {
        return participantNames != null ? participantNames.size() : 0;
    }
}