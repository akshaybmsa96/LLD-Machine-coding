package questions.parkingLot;

public class ParkingSpot {

    private final ParkingSpotSize size;
    private ParkingState state;
    private Ticket ticket;

    public ParkingSpot(ParkingSpotSize size) {
        this.size = size;
        this.state = ParkingState.AVAILABLE;
    }

    public ParkingSpotSize getSize() {
        return size;
    }

    public ParkingState getState() {
        return state;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void assignParkingSpot(Ticket ticket) {
        this.ticket = ticket;
        this.state = ParkingState.OCCUPIED;
    }

    public void freeParkingSpot() {

        this.ticket = null;
        this.state = ParkingState.AVAILABLE;
    }

}
