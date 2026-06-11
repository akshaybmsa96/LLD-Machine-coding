package questions.parkingLot;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private Map<String, ParkingSpot> ticketIdParkingSpotMap = new ConcurrentHashMap<>();
    private List<ParkingSpot> parkingSpotList = new ArrayList<>();
    private final Double hourlyRate = 10.1;


    public ParkingLot(List<ParkingSpot> parkingSpotList) {
        this.parkingSpotList = parkingSpotList;
    }

    public String assignParkingSpot(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }
        for (ParkingSpot spot : parkingSpotList) {
            synchronized (spot) {
                if (spot.getState() == ParkingState.AVAILABLE && isVehicleTypeAndParkingTypeMatching(vehicle.getType(), spot.getSize())) {
                    Ticket ticket = new Ticket(Instant.now().getEpochSecond(), spot);
                    spot.assignParkingSpot(ticket);
                    ticketIdParkingSpotMap.put(ticket.getTicketId(), spot);
                    return ticket.getTicketId();
                }
            }
        }
        System.out.println("No available parking spot for vehicle type " + vehicle.getType());
        return null;
    }

    public Double makeVehicleExit(String ticketId) {
        if (ticketId == null || ticketId.isBlank()) {
            throw new RuntimeException("Invalid ticket");
        }
        ParkingSpot spot = ticketIdParkingSpotMap.get(ticketId);
        if (spot == null) {
            System.out.printf("Ticket ID %s not valid%n", ticketId);
            throw new RuntimeException("Invalid ticket");
        }

        synchronized (spot) {
            Ticket ticket = spot.getTicket();
            if (ticket.getState() != TicketState.VALID) {
                throw new RuntimeException("Invalid ticket");
            }
            if (ticket.makeExit(Instant.now().getEpochSecond())) {
                spot.freeParkingSpot();
                ticketIdParkingSpotMap.remove(ticketId);
                return getTotalFees(ticket.getTotalHours());
            }
        }
        throw new RuntimeException("Something went wrong");
    }

    public Double getTotalFees(Double totalHours) {
        return hourlyRate * totalHours;
    }

    private boolean isVehicleTypeAndParkingTypeMatching(VehicleType vehicleType, ParkingSpotSize size) {


        if(vehicleType == null || size == null) {
            return false;
        }
        if(vehicleType == VehicleType.CAR && size == ParkingSpotSize.MEDIUM) {
            return true;
        }
        if(vehicleType == VehicleType.BIKE && size == ParkingSpotSize.SMALL) {
            return true;
        }
        return vehicleType == VehicleType.TRUCK && size == ParkingSpotSize.LARGE;
    }
}
