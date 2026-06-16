package questions.notificatonSystem.channel;

import questions.notificatonSystem.model.Notification;

public class PushNotification implements NotificationChannel {

    @Override
    public void sendNotification(Notification notification) {
        System.out.println("Sending Push Notification to user device and userID " + notification.userId + " message: " + notification.message);
    }
}
