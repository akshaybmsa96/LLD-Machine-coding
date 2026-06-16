package questions.bookingSystem;

import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

public class Booking{
    String bookingId;
    ShowTime showtime;
    List<Seat> seats;
    BookingStatus status;

    public Booking(ShowTime showtime, List<Seat> seats, BookingStatus status) {
        this.bookingId = UUID.randomUUID().toString();
        this.showtime = showtime;
        this.seats = seats;
        this.status = status;
    }
}

