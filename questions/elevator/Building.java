package questions.elevator;

import java.util.ArrayList;
import java.util.Comparator;

class Building{
    int floors;
    ArrayList<Elevator> elevators;

    Elevator requestElevator( int floor, Direction direction){
        // 3 combination to pick from

        //1st. call free elevator
        //2nd. call elevator coming to the same direction with shorter difference
        Elevator elevator = elevators.stream().filter
                (e1-> e1.isAvailable() ||
                        (e1.movingDirection == Direction.UP &&
                                    direction == Direction.UP &&
                                    e1.getCurrentFloor() <floor)
                        ||
                                (                      e1.movingDirection == Direction.DOWN &&
                                        direction == Direction.DOWN &&
                                        e1.getCurrentFloor() > floor)
                        ).min(Comparator.comparingInt(
                        e -> Math.abs(e.getCurrentFloor() - floor)
                ))
                .orElse(elevators.getFirst());

        elevator.addRequest(floor);

        return  elevator;


    }
    void step(){

    }
}