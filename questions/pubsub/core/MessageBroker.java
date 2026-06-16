package questions.pubsub;

import questions.pubsub.exception.TopicNotFound;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageBroker {

    private static final MessageBroker INSTANCE = new MessageBroker();
    final Map<String, Topic> topicNameMapping = new ConcurrentHashMap<>();
    private final ExecutorService service = Executors.newFixedThreadPool(10);


    private MessageBroker() {
    }

    static MessageBroker getInstance(){
        return INSTANCE;
    }

    public void createTopic(String topicName){
        if(topicName == null || topicName.isEmpty()){
            throw new IllegalArgumentException("Invalid topic name");
        }
        topicNameMapping.putIfAbsent(topicName, new Topic(topicName, service));
    }

    public void deleteTopic(String topicName){
        if(topicName == null || topicName.isEmpty()){
            throw new IllegalArgumentException("Invalid topic name");
        }
        Topic topic = topicNameMapping.get(topicName);
        if(topic == null){
            throw new TopicNotFound("Topic not found exception");
        }
        topicNameMapping.remove(topicName);
    }

    public void addSubscriber(String topicName, Subscriber subscriber){
        if(topicName == null || topicName.isEmpty() || subscriber == null){
            throw new IllegalArgumentException("Invalid Data provided");
        }

        Topic topic = topicNameMapping.get(topicName);
        if(topic == null){
            throw new TopicNotFound("Topic not found");
        }

        topic.addSubscriber(subscriber);
    }


    public void removeSubscriber(String topicName, Subscriber subscriber){
        if(topicName == null || subscriber == null){
            throw new IllegalArgumentException("Invalid Data provided");
        }

        Topic topic = topicNameMapping.get(topicName);
        if(topic == null){
            throw new TopicNotFound("Topic not found");
        }

        topic.removeSubscriber(subscriber);
    }

    public void publish(String topicName, String message, String publisher){
        if(topicName == null || topicName.isEmpty() || message == null){
            throw new IllegalArgumentException("Invalid Data provided");
        }

        Topic topic = topicNameMapping.get(topicName);
        if(topic == null){
            throw new TopicNotFound("Topic not created yet");
        }

        Message messageObj = new Message(message, Instant.now().toString(), topicName, publisher);
        System.out.println("Publishing new message on " + topic + ": " + message);
        notifySubscribers(topic, messageObj);
    }

    private void notifySubscribers(Topic topic, Message message){
        if(topic == null || message == null){
            throw new NullPointerException("Invalid Data provided");
        }
        topic.notifySubscribers(message);
    }

    public void shutdown(){
        service.shutdown();
    }

}
