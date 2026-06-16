package questions.bookingSystem;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BookingSystem {

    Map<String, Booking> bookings;
    ScheduledExecutorService scheduler;

    public BookingSystem() {

        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::cleanup, 5, 5, TimeUnit.SECONDS);
    }

//    List<ShowTime> getShows(String search){
//
//    }
//
//    List<Seat> getAvailableSeats(ShowTime showtime){
//
//    }
    Booking makeBooking(List<Seat> seats, ShowTime showtime){


        if(showtime.startTime > Instant.now().getEpochSecond()){
            throw new RuntimeException("Cannot book for this show, as show already started");
        }

            Booking booking =  showtime.makeBooking(seats);

            bookings.put(booking.bookingId, booking);

            return booking;
    }

    void cancelBooking(String bookingId){

        if(bookingId == null || bookingId.isEmpty()){
            throw new RuntimeException("No Booking Id provided");
        }

        Booking booking = bookings.getOrDefault(bookingId, null);
        if(booking == null){
            throw new RuntimeException("Booking doesn't exists");
        }

        if(booking.status == BookingStatus.CANCELLED){
            throw new RuntimeException("Booking already cancelled");
        }

        booking.showtime.cancelBooking(bookingId);

    }
//    Booking getBooking(String bookingId){
//
//    }

    void cleanup(){
        System.out.println("Running Cleanup");
    }
}
