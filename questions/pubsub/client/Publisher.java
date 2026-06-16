package questions.pubsub;

public class Publisher {

    private final String publisherName;
    private final MessageBroker msgBroker;

    public Publisher(String publisherName) {
        this.publisherName = publisherName;
        msgBroker = MessageBroker.getInstance();
    }

    void publish(String topic, String msg){
        msgBroker.publish(topic, msg, publisherName);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "publisherName='" + publisherName + '\'' +
                '}';
    }
}
