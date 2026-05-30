package patterns.creational;

class FactoryPatternsDemo {
    public static void main(String[] args) {

            Notification emailService = NotificationFactory.create(NotificationType.EMAIL);
            emailService.sendNotification("Hi User, try our new Product");


            Notification smsService = NotificationFactory.create(NotificationType.SMS);
            smsService.sendNotification("Hi, User, your transaction was successful");

            Notification whatsappService = NotificationFactory.create(NotificationType.WHATSAPP);
            whatsappService.sendNotification("This item from your wishlist is on SALE!, Checkout NOW");

            Notification test = NotificationFactory.create(null);
            test.sendNotification("YO");
    }
}



/**
 * Factory Design Pattern is a Creational design pattern that provides an interface for creating objects in a superclass,
 * but allows subclasses to alter the type of objects that will be created. 
 * It promotes loose coupling by eliminating the need to bind application-specific classes into the code. 
 * The client code interacts with the factory method, which creates and returns the appropriate object based on the input parameters or configuration.
 */


interface Notification{
    void sendNotification(String message);
}

enum NotificationType { EMAIL, SMS, WHATSAPP };


class EmailNotification implements Notification {
    public void sendNotification(String message) {
        System.out.println("Sending Email Notification: " + message);
    }
}

class SMSNotification implements Notification {
    public void sendNotification(String message){
        System.out.println("Sending SMS Notification: " + message);
    }
}

class WhatsappNotification implements Notification {
    public void sendNotification(String message){
        System.out.println("Sending Whatsapp Notification: " + message);
    }
}




class NotificationFactory {
    public static Notification create(NotificationType type){


        if(type == null){
            throw new IllegalArgumentException("Notification type cannot be null");
        }

        return switch (type) {
            case NotificationType.EMAIL -> new EmailNotification();
            case NotificationType.SMS -> new SMSNotification();
            case NotificationType.WHATSAPP -> new WhatsappNotification();
        };

    }

}