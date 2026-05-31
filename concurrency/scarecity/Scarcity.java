package concurrency.scarecity;

import java.util.concurrent.Semaphore;

public class Scarcity {

    public static void main(String[] args) {

        Funnel funnel = new Funnel();
        Thread t1 = new Thread(()->funnel.sendMessage("Hello 1 message"));
        Thread t2 = new Thread(()->funnel.sendMessage("Hello 2 message"));
        Thread t3 = new Thread(()->funnel.sendMessage("Hello 3 message"));
        Thread t4 = new Thread(()->funnel.sendMessage("Hello 4 message"));
        Thread t5 = new Thread(()->funnel.sendMessage("Hello 5 message"));
        Thread t6 = new Thread(()->funnel.sendMessage("Hello 6 message"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();



    }
}


class Funnel {
    private final static Semaphore limit = new Semaphore(3);
    void sendMessage(String msg){

        try{
            limit.acquire();
            Thread.sleep(1000);
            System.out.println("Sending msg--------------->" + msg);
        } catch (InterruptedException e){
            System.out.println("Exception caught" + e.getMessage());
        } finally {
            limit.release();
        }

    }
}