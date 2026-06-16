package questions.notificatonSystem.channel;

import questions.notificatonSystem.model.Notification;

public interface NotificationChannel {
    void sendNotification(Notification notification);
}
