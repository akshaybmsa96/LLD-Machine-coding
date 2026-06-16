package questions.notificatonSystem.dispatcher;

import questions.notificatonSystem.channel.NotificationChannel;
import questions.notificatonSystem.channel.NotificationChannelFactory;
import questions.notificatonSystem.model.Notification;
import questions.notificatonSystem.model.NotificationType;

import java.util.List;
import java.util.Map;

public class NotificationDispatcherStrategy implements NotificationDispatcher {
    Map<String, List<NotificationType>> userPreferences;

    public NotificationDispatcherStrategy(Map<String, List<NotificationType>> userPreferences) {
        this.userPreferences = userPreferences;
    }

    public void dispatchNotification(Notification notification) {
        List<NotificationType> userPref = userPreferences.get(notification.userId);

        for (NotificationType type : userPref) {
            NotificationChannel channel = NotificationChannelFactory.getChannel(type);
            channel.sendNotification(notification);
        }
    }
}
