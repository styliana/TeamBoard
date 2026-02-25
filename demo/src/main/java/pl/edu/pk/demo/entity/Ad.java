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

    // Zmieniamy int na kolekcję nazw użytkowników
    @ElementCollection
    private Set<String> participantNames = new HashSet<>();

    public Ad() {}

    public Ad(String title, String description, String category, String author) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.author = author;
    }

    // Gettery i Settery
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public String getAuthor() { return author; }

    public Set<String> getParticipantNames() { return participantNames; }

    // Metoda pomocnicza dla frontendu (zwraca liczbę osób)
    public int getParticipantsCount() {
        return participantNames.size();
    }
}