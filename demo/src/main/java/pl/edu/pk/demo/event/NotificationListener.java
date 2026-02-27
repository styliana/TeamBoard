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
        // Profesjonalny log (zamiast System.out.println)
        logger.info("🔔 SYSTEM POWIADOMIEŃ: Użytkownik '{}' wpadnie na kawę '{}' (Autor: {})",
                event.getParticipant(), event.getAdTitle(), event.getAuthor());
    }
}