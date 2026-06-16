package questions.notificatonSystem.channel;

import questions.notificatonSystem.model.Notification;

public class EmailNotification implements NotificationChannel {

    @Override
    public void sendNotification(Notification notification) {
        System.out.println("Sending Email Notification to user email and userID " + notification.userId + " message: " + notification.message);
    }
}
