package questions.notificatonSystem;

import questions.notificatonSystem.dispatcher.AsyncNotificationDispatcherStrategy;
import questions.notificatonSystem.dispatcher.NotificationDispatcher;
import questions.notificatonSystem.dispatcher.NotificationDispatcherStrategy;
import questions.notificatonSystem.model.Notification;
import questions.notificatonSystem.model.NotificationType;
import questions.notificatonSystem.service.UserPreferenceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationMain {
    public static void main(String[] args) {
        System.out.println("Starting System");

        Map<String, List<NotificationType>> preferences = new HashMap<>();
        preferences.put("user1", List.of(NotificationType.SMS, NotificationType.EMAIL));
        preferences.put("user2", List.of(NotificationType.PUSH));
        preferences.put("user3", List.of(NotificationType.SMS, NotificationType.EMAIL, NotificationType.PUSH));

        UserPreferenceService userPreferenceServices = new UserPreferenceService(preferences);
        userPreferenceServices.addPreferences("user4", List.of(NotificationType.SMS, NotificationType.EMAIL, NotificationType.PUSH));

        Notification n1 = new Notification();
        n1.userId = "user1";
        n1.message = "Your order has been placed!";

        Notification n2 = new Notification();
        n2.userId = "user2";
        n2.message = "Flash sale starts now!";

        Notification n3 = new Notification();
        n3.userId = "user3";
        n3.message = "Your password was changed.";

        NotificationDispatcher dispatcher = new NotificationDispatcherStrategy(userPreferenceServices.getUserPreferences());
        dispatcher.dispatchNotification(n1);
        dispatcher.dispatchNotification(n2);
        dispatcher.dispatchNotification(n3);

//        NotificationDispatcher dispatcherAsync = new AsyncNotificationDispatcherStrategy(preferences);
//        dispatcherAsync.dispatchNotification(n1);
//        dispatcherAsync.dispatchNotification(n2);
//        dispatcherAsync.dispatchNotification(n3);
    }
}
