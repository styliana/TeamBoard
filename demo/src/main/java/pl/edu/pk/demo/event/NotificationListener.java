package pl.edu.pk.demo.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    // Profesjonalny logger wbudowany w Springa
    private static final Logger logger = LoggerFactory.getLogger(NotificationListener.class);

    @EventListener
    public void handleUserJoinedEvent(UserJoinedEvent event) {
        // Tekst bez polskich znaków i emoji - w 100% bezpieczny dla każdej konsoli
        logger.info("[SYSTEM POWIADOMIEN]: Uzytkownik '{}' wpadnie na kawe '{}' (Autor: {})",
                event.getParticipant(), event.getAdTitle(), event.getAuthor());
    }
}