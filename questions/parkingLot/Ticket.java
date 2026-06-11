package questions.parkingLot;

import java.util.concurrent.TimeUnit;

public class Ticket {
    private final long issueAtTimestamp;
    private long exitAtTimestamp;
    private final ParkingSpot spot;
    private TicketState state;
    private final String ticketId;

    public Ticket(long issueAtTimestamp, ParkingSpot spot) {
        this.issueAtTimestamp = issueAtTimestamp;
        this.spot = spot;
        this.ticketId = generateTicketId();
        state = TicketState.VALID;
    }

    private String generateTicketId() {
        return String.valueOf(Math.random());
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public String getTicketId() {
        return ticketId;
    }

    public boolean makeExit(long exitAtTime){
        if(exitAtTime >= issueAtTimestamp && state == TicketState.VALID){
            this.exitAtTimestamp = exitAtTime;
            state = TicketState.INVALID;
            return true;
        }

        System.out.println("Ticket is not in valid state");
        //throw error
        return false;
    }

    public TicketState getState() {
        return state;
    }

    public Double getTotalHours(){
        double hours = (double)(exitAtTimestamp - issueAtTimestamp) / 3600.0;
        return Math.max(1.0, Math.ceil(hours));
    }


}
