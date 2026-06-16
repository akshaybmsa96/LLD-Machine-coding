package questions.pubsub;

public class Message {
    private final String payload;
    private final String timestamp;
    private final String topic;
    private final String publisher;

    public String getPublisher() {
        return publisher;
    }

    public Message(String payload, String timestamp, String topic, String publisher) {
        this.payload = payload;
        this.timestamp = timestamp;
        this.topic = topic;
        this.publisher = publisher;
    }

    public String getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "Message{" +
                "payload='" + payload + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", topic='" + topic + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
