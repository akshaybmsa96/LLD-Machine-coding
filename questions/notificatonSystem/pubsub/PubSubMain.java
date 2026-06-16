package questions.notificatonSystem.pubsub;

import questions.notificatonSystem.dispatcher.NotificationDispatcher;
import questions.notificatonSystem.dispatcher.NotificationDispatcherStrategy;
import questions.notificatonSystem.model.NotificationType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PubSubMain {
    public static void main(String[] args) {
        System.out.println("===== Pub-Sub (SubscriptionManager) Demo =====");

        // Global per-user channel preferences (har topic ke liye same channels).
        Map<String, List<NotificationType>> preferences = new HashMap<>();
        preferences.put("user1", List.of(NotificationType.SMS, NotificationType.EMAIL));
        preferences.put("user2", List.of(NotificationType.PUSH));
        preferences.put("user3", List.of(NotificationType.SMS, NotificationType.EMAIL, NotificationType.PUSH));

        // Existing dispatcher reuse. Async chahiye to: new AsyncNotificationDispatcherStrategy(preferences)
        NotificationDispatcher dispatcher = new NotificationDispatcherStrategy(preferences);
        SubscriptionManager manager = new SubscriptionManager(dispatcher);

        // Subscriptions
        manager.subscribe("ORDERS", "user1");
        manager.subscribe("ORDERS", "user3");
        manager.subscribe("SALES", "user2");

        // Publish -> sirf us topic ke subscribers ko jaata hai
        manager.publish("ORDERS", "Your order has been placed!");   // user1, user3
        manager.publish("SALES", "Flash sale starts now!");          // user2

        // Unsubscribe ke baad us user ko notification nahi milegi
        manager.unsubscribe("ORDERS", "user1");
        manager.publish("ORDERS", "Your order has been shipped!");   // sirf user3
    }
}
