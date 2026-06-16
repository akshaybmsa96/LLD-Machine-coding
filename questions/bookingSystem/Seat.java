package questions.bookingSystem;

import java.util.concurrent.locks.ReentrantLock;

public class Seat{
    String seatNumber; //A10
    ReentrantLock lock;

    String getSeatNumber(){
        return seatNumber;
    }

    boolean getLock(){
       return lock.tryLock();
    }

    void releaseLock(){
        lock.unlock();
    }
}