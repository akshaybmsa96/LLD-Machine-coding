package questions.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingMain {

    public static void main(String[] args) {

        ParkingSpot ps1 = new ParkingSpot(ParkingSpotSize.SMALL);
        ParkingSpot ps2 = new ParkingSpot(ParkingSpotSize.SMALL);
        ParkingSpot ps3 = new ParkingSpot(ParkingSpotSize.MEDIUM);
        ParkingSpot ps4 = new ParkingSpot(ParkingSpotSize.LARGE);

        List<ParkingSpot> parkingSpotList = new ArrayList<>();
        parkingSpotList.add(ps1);
        parkingSpotList.add(ps2);
        parkingSpotList.add(ps3);
        parkingSpotList.add(ps4);

        ParkingLot parkingLot = new ParkingLot(parkingSpotList);

        String t1 = parkingLot.assignParkingSpot(new Vehicle(VehicleType.BIKE));
        String t2 = parkingLot.assignParkingSpot(new Vehicle(VehicleType.BIKE));
        String t3 = parkingLot.assignParkingSpot(new Vehicle(VehicleType.BIKE));

        System.out.println("Pay t1: " + parkingLot.makeVehicleExit(t1));
        System.out.println("Pay t2: " + parkingLot.makeVehicleExit(t2));
        String t4 = parkingLot.assignParkingSpot(new Vehicle(VehicleType.BIKE));
        String t5 = parkingLot.assignParkingSpot(new Vehicle(VehicleType.BIKE));

        String t6 = parkingLot.assignParkingSpot(new Vehicle(VehicleType.CAR));
        String t7 = parkingLot.assignParkingSpot(new Vehicle(VehicleType.TRUCK));

        System.out.println("Pay t6: " + parkingLot.makeVehicleExit(t6));
        System.out.println("Pay t7: " + parkingLot.makeVehicleExit(t7));

        String t8 = parkingLot.assignParkingSpot(new Vehicle(VehicleType.CAR));
        String t9 = parkingLot.assignParkingSpot(new Vehicle(VehicleType.TRUCK));

        String t10 = parkingLot.assignParkingSpot(new Vehicle(VehicleType.TRUCK));
        //System.out.println("Pay t10: " + parkingLot.makeVehicleExit(t10));
    }
}
