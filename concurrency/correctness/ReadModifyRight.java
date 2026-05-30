package concurrency.correctness;

import java.util.concurrent.atomic.AtomicInteger;

public class ReadModifyRight {
    public static void main(String[] args) {
        Increment counter = new Increment(2);
        IncrementSync counter1 = new IncrementSync(2);



        Thread t1 = new Thread(counter::increment);
        Thread t2 = new Thread(counter::increment);

        t1.start();
        t2.start();

        Thread t3 = new Thread(counter1::increment);
        Thread t4 = new Thread(counter1::increment);

        t3.start();
        t4.start();

    }
}

class Increment{
    private int num;

    public Increment(int num) {
        this.num = num;
    }

    public void increment() {

        int temp = num;

        try {

            Thread.sleep(100);

        } catch (InterruptedException e) {

            throw new RuntimeException(e);

        }

        num = temp + 1;

        System.out.println("Thread: " + Thread.currentThread().getName() +" Updated value: " + num);

    }
}


class IncrementSync{
    private final AtomicInteger num;

    public IncrementSync(int num) {
        this.num = new AtomicInteger(num);
    }

    public void increment(){
        num.getAndIncrement();
        System.out.println("Thread: " + Thread.currentThread().getName() +" Updated value: " + num);
    }
}
