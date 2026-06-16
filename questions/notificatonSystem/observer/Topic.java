package questions.notificatonSystem.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject (GoF). Topic khud apne observers ki list rakhta hai aur publish par
 * sabhi subscribers ko khud notify karta hai (control distributed).
 */
public class Topic {

    private final String name;
    private final List<Subscriber> subscribers = new ArrayList<>();

    public Topic(String name) {
        this.name = name;
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void publish(String message) {
        System.out.println("\nPublishing to topic " + name + " (" + subscribers.size() + " subscribers): " + message);
        for (Subscriber subscriber : subscribers) {
            subscriber.update(name, message);
        }
    }
}
