package concurrency.cordination;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Coordination {

    private static final BlockingQueue<String> queue =  new LinkedBlockingQueue<>(1);
    public static void main(String[] args) {

        Thread emailThread =

                new Thread(Coordination::sendEmail);

        emailThread.start();

        addUser("akshay.sharma@google.com");
        addUser("lakshay.sharma@google.com");
        addUser("ak.sharma@google.com");
        addUser("lk.sharma@google.com");
        addUser("user1.sharma@google.com");



    }

    public static void addUser(String email){
        try{
//            queue.put(email);
            boolean success = queue.offer(email, 200, TimeUnit.MICROSECONDS);

            if(!success){
                System.out.println("Couldn't insert it");
            }

        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public static void sendEmail(){
        while(true){
            try {

                String email = queue.take();

                System.out.println(

                        "Sending Email to : " + email

                );

            } catch (InterruptedException e) {

                throw new RuntimeException(e);

            }
        }

    }
}


