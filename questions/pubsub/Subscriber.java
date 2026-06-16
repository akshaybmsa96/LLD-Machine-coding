package questions.pubsub;

public interface Subscriber {
    void onMessage(Message message);
}
