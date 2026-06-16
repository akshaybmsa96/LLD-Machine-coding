package questions.pubsub;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

public class Topic {
    final private String topicName;
    final private List<Subscriber> subscribers = new CopyOnWriteArrayList<>();
    final private ExecutorService service;

    public Topic(String topicName, ExecutorService service) {
        this.topicName = topicName;
        this.service = service;
    }

    void addSubscriber(Subscriber subs){
        subscribers.add(subs);
    }

    public void removeSubscriber(Subscriber subscriber){
        subscribers.remove(subscriber);
    }

    @Override
    public String toString() {
        return "Topic " + "topicName='" + topicName + " ";
    }

    public void notifySubscribers(Message message){
        for(Subscriber subscriber: subscribers){
            service.submit(()-> msgSender(subscriber, message));
        }
    }

    private void msgSender(Subscriber subscriber, Message message){
        try{
            subscriber.onMessage(message);
        } catch (Exception e){
            System.out.println("Delivery failed for subscriber on topic " + topicName + ": " + e.getMessage());
        }
    }
}
