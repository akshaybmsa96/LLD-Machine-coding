package questions.notificatonSystem.channel;

import questions.notificatonSystem.model.Notification;

public class SMSNotification implements NotificationChannel {

    @Override
    public void sendNotification(Notification notification) {
        System.out.println("Sending SMS Notification to user phone and userID " + notification.userId + " message: " + notification.message);
    }
}
