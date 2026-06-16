package questions.bookingSystem;

import java.net.NoRouteToHostException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ShowTime {
        int startTime;
        int endTime;
        Show show;
        List<Booking> bookings;
        List<Seat> seats;
        Map<String, SeatStatus> seatStatus;
        Map<String, Booking>  bookingMapping;

        synchronized void cancelBooking(String bookingId){

                Booking booking = bookingMapping.get(bookingId);

                List<Seat> seats = booking.seats;

                for(Seat seat: seats){
                        seatStatus.put(seat.getSeatNumber(), SeatStatus.FREE);
                }

                booking.status = BookingStatus.CANCELLED;

        }

        synchronized Booking makeBooking(List<Seat> seats){

                if(seats == null || seats.isEmpty()){
                    throw new IllegalArgumentException("Not valid seats");
                }

                for(Seat seat: seats){
                        if(seatStatus.get(seat.seatNumber) == null || !seatStatus.get(seat.seatNumber).equals(SeatStatus.FREE)){
                                throw new IllegalArgumentException("Selected Seats are not available");
                        }
                }

                //blocking seats
                for(Seat seat: seats){
                        seatStatus.put(seat.seatNumber, SeatStatus.OCCUPIED);
                }


                Booking newBooking = new Booking( this, seats,  BookingStatus.CONFIRMED);
                bookings.add(newBooking);
                bookingMapping.put(newBooking.bookingId, newBooking);

                return newBooking;

        }

        synchronized List<Seat> getAvailableSeats(){
                return seats.stream().filter(seat -> seatStatus.get(seat.seatNumber).equals(SeatStatus.FREE)).toList();
        }

}
