package questions.notificatonSystem.observer;

import questions.notificatonSystem.channel.NotificationChannel;
import questions.notificatonSystem.channel.NotificationChannelFactory;
import questions.notificatonSystem.model.Notification;
import questions.notificatonSystem.model.NotificationType;

import java.util.List;

/**
 * Concrete observer. Pub-Sub ke ulat, yahan subscriber ek rich object hai jo apne
 * channel preferences khud rakhta hai aur update() par khud channels pe bhejta hai.
 */
public class User implements Subscriber {

    private final String userId;
    private final List<NotificationType> preferredChannels;

    public User(String userId, List<NotificationType> preferredChannels) {
        this.userId = userId;
        this.preferredChannels = preferredChannels;
    }

    @Override
    public void update(String topic, String message) {
        for (NotificationType type : preferredChannels) {
            NotificationChannel channel = NotificationChannelFactory.getChannel(type);
            Notification notification = new Notification();
            notification.userId = userId;
            notification.message = "[" + topic + "] " + message;
            channel.sendNotification(notification);
        }
    }
}
