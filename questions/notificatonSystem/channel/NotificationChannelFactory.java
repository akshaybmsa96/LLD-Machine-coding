package questions.notificatonSystem.channel;

import questions.notificatonSystem.model.NotificationType;

public class NotificationChannelFactory {

    public static NotificationChannel getChannel(NotificationType type) {
        return switch (type) {
            case EMAIL -> new EmailNotification();
            case SMS -> new SMSNotification();
            case PUSH -> new PushNotification();
        };
    }
}
