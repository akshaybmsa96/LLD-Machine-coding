package concurrency.correctness;

import java.util.HashMap;
import java.util.Map;

public class CheckThenAct {
    public static void main(String[] args) throws InterruptedException {


        Map<Integer, Boolean> seats = new HashMap<>();


//        Booking booking = new Booking(10);
//        //booking.bookSeat(seats);
//
//        Booking booking2 = new Booking(10);
//        //booking2.bookSeat(seats);
//
//        Thread t1 = new Thread(() -> booking.bookSeat(seats));
//
//        Thread t2 = new Thread(() -> booking2.bookSeat(seats));
//
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();
//        System.out.println("Back to main");

        BookingSynchronized bookingSynchronized = new BookingSynchronized(20);
        //bookingSynchronized.bookSeat(seats);

        BookingSynchronized bookingSynchronized2 = new BookingSynchronized(20);
        //bookingSynchronized.bookSeat(seats);

        BookingSynchronized bookingSynchronized3 = new BookingSynchronized(30);

        Thread t3 = new Thread(() -> bookingSynchronized.bookSeat(seats));

        Thread t4 = new Thread(() -> bookingSynchronized2.bookSeat(seats));


        t3.start();
        t4.start();
        bookingSynchronized3.bookSeat(seats);



        t3.join();
        t4.join();
        System.out.println("Back to main");

    }
}

class Booking {
    private final int selectedSeat;
    private boolean seatConfirmed;

    public Booking(int selectedSeat) {
        this.selectedSeat = selectedSeat;
    }

    public void bookSeat(Map<Integer, Boolean> seats){

        // problem for 2 parallel threads
        if(checkIfAvailable(selectedSeat, seats)){
            confirmBooking();
            System.out.println("Booked Seat: "+ selectedSeat);
            seats.put(selectedSeat, false);
            return;
        }
        System.out.println("Already booked");
        seatConfirmed = false;
    }

    private boolean checkIfAvailable(int selectedSeat, Map<Integer, Boolean> seats){
        return seats.get(selectedSeat);
    }

    private void confirmBooking(){
        this.seatConfirmed = true;
    }
}


class BookingSynchronized {
    private final int selectedSeat;
    private boolean seatConfirmed;
    private static Map<Integer, Object> locks = new HashMap<>();

    public BookingSynchronized(int selectedSeat) {
        this.selectedSeat = selectedSeat;
    }

    public void bookSeat(Map<Integer, Boolean> seats){
        // whole block will be a atomic operation
        synchronized (locks.computeIfAbsent(selectedSeat, k-> new Object())){
            if(checkIfAvailable(selectedSeat, seats)){
                confirmBooking();
                System.out.println("Booked Seat: "+ selectedSeat);
                seats.put(selectedSeat, false);
                return;
            }
            System.out.println("Already booked, seat: "+ selectedSeat);
            seatConfirmed = false;
        }


    }

    private boolean checkIfAvailable(int selectedSeat, Map<Integer, Boolean> seats){
        return seats.computeIfAbsent(selectedSeat, k-> true);
    }

    private void confirmBooking(){
        this.seatConfirmed = true;
    }
}
