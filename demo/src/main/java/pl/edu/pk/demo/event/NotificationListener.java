package pl.edu.pk.demo.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    // Ta adnotacja sprawia, że Spring sam wywoła tę metodę w odpowiednim momencie!
    @EventListener
    public void handleUserJoinedEvent(UserJoinedEvent event) {
        // Symulujemy wysłanie powiadomienia (np. e-mail) w konsoli serwera
        System.out.println("\n======================================================");
        System.out.println("🔔 SYSTEM POWIADOMIEŃ (Spring Event Triggered)");
        System.out.println("Do użytkownika: " + event.getAuthor());
        System.out.println("Treść: Hej! Użytkownik '" + event.getParticipant() + "' wpadnie na Twoją kawę pt. '" + event.getAdTitle() + "'!");
        System.out.println("======================================================\n");
    }
}