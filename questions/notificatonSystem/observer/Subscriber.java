package questions.notificatonSystem.observer;

/**
 * Observer interface (GoF). Subject (Topic) ke paas Subscriber ka direct reference hota hai
 * aur naye message par update() call karta hai.
 */
public interface Subscriber {
    void update(String topic, String message);
}
