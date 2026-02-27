package pl.edu.pk.demo.event;

import org.springframework.context.ApplicationEvent;

public class UserJoinedEvent extends ApplicationEvent {
    private final String adTitle;
    private final String author;
    private final String participant;

    public UserJoinedEvent(Object source, String adTitle, String author, String participant) {
        super(source); // Wymagane przez Springa
        this.adTitle = adTitle;
        this.author = author;
        this.participant = participant;
    }

    public String getAdTitle() { return adTitle; }
    public String getAuthor() { return author; }
    public String getParticipant() { return participant; }
}