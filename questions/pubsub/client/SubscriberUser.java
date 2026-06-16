package questions.pubsub;

public class SubscriberUser implements Subscriber{

    private final String subscriber;

    public SubscriberUser(String subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void onMessage(Message message) {
        System.out.println(subscriber  + " - " + message.getPayload() + " - " + message.getPublisher() );
    }

}
