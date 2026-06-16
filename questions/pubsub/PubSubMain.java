package questions.pubsub;

public class PubSubMain {
    public static void main(String[] args) {


        Subscriber akshay = new SubscriberUser("Akshay");
        Subscriber rahul = new SubscriberUser("Rahul");
        Subscriber shivam = new SubscriberUser("Shivam");

        MessageBroker broker  = MessageBroker.getInstance();

        broker.createTopic("cricket");
        broker.createTopic("football");


        broker.addSubscriber("cricket", akshay);
        broker.addSubscriber("cricket", rahul);
        broker.addSubscriber("cricket", shivam);

        broker.addSubscriber("football", akshay);


        Publisher crickBuzz = new Publisher("CrickBuzz");
        Publisher espn = new Publisher("ESPN");

        String messageCricket = "India Won by 150 runs against Australia in 2023 woorlcup final - Rohit sharma made century";
        String messageFootball ="France Defeated Argentina in WC final in shootouts, Mbappé the hero! Messi the looser!";

        crickBuzz.publish("cricket", messageCricket);
        espn.publish("football", messageFootball);

        // async delivery tasks complete hone do, phir clean exit (pool ke non-daemon threads band)
        broker.shutdown();
    }
}