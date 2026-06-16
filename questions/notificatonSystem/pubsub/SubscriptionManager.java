package questions.notificatonSystem.pubsub;

import questions.notificatonSystem.dispatcher.NotificationDispatcher;
import questions.notificatonSystem.model.Notification;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Pub-Sub broker approach.
 * Subscriber yahan sirf ek userId hai. Saari subscriptions central manager me rehti hain.
 * Publish par subscribers nikaal ke existing dispatcher reuse hota hai (channels/preferences as-is).
 * Real-world analogy: AWS SNS / Kafka topics.
 */
public class SubscriptionManager {

    // topic -> us topic ke subscribe kiye hue userIds
    private final Map<String, Set<String>> topicSubscribers = new HashMap<>();
    private final NotificationDispatcher dispatcher;

    public SubscriptionManager(NotificationDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public void subscribe(String topic, String userId) {
        topicSubscribers.computeIfAbsent(topic, k -> new HashSet<>()).add(userId);
        System.out.println(userId + " subscribed to topic " + topic);
    }

    public void unsubscribe(String topic, String userId) {
        topicSubscribers.getOrDefault(topic, new HashSet<>()).remove(userId);
        System.out.println(userId + " unsubscribed from topic " + topic);
    }

    public void publish(String topic, String message) {
        Set<String> subscribers = topicSubscribers.getOrDefault(topic, Set.of());
        System.out.println("\nPublishing to topic " + topic + " (" + subscribers.size() + " subscribers): " + message);

        for (String userId : subscribers) {
            Notification notification = new Notification();
            notification.userId = userId;
            notification.message = message;
            dispatcher.dispatchNotification(notification);
        }
    }
}
