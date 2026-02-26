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

    // Zainicjalizowanie HashSet rozwiązuje błąd 500 przy zapisie do bazy
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> participantNames = new HashSet<>();

    public Ad() {}

    // Gettery i Settery (Muszą być wszystkie!)
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
    public void setParticipantNames(Set<String> participantNames) { this.participantNames = participantNames; }
}