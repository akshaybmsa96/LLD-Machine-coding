package questions.notificatonSystem.observer;

import questions.notificatonSystem.model.NotificationType;

import java.util.List;

public class ObserverMain {
    public static void main(String[] args) {
        System.out.println("===== Observer Pattern (Topic/Subject) Demo =====");

        // Subscriber rich objects: userId + apne channel preferences.
        User user1 = new User("user1", List.of(NotificationType.SMS, NotificationType.EMAIL));
        User user2 = new User("user2", List.of(NotificationType.PUSH));
        User user3 = new User("user3", List.of(NotificationType.SMS, NotificationType.EMAIL, NotificationType.PUSH));

        Topic orders = new Topic("ORDERS");
        Topic sales = new Topic("SALES");

        orders.subscribe(user1);
        orders.subscribe(user3);
        sales.subscribe(user2);

        // Publish -> Topic khud apne subscribers ko notify karta hai
        orders.publish("Your order has been placed!");   // user1, user3
        sales.publish("Flash sale starts now!");          // user2

        // Unsubscribe ke baad us user ko notification nahi milegi
        orders.unsubscribe(user1);
        orders.publish("Your order has been shipped!");   // sirf user3
    }
}
