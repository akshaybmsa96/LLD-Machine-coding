package questions.elevator;

import java.util.HashSet;

class Elevator {

    int MAX_FLOOR = 9, MIN_FLOOR = 0;

    int currentFloor;
    HashSet<Integer> cabinRequest = HashSet.newHashSet(10); //size 10;
    Direction movingDirection;
    void open(){
        System.out.println("Elevator doors are open");
    }
    void close(){
        System.out.println("Elevator doors are closed");
    }


    void addRequest(int floor){

        if(floor < MIN_FLOOR || floor > MAX_FLOOR){
            throw new WrongFloorSelection("Wrong floor selected");
        }
        cabinRequest.add(floor);
        if(movingDirection == Direction.IDLE){
            movingDirection = currentFloor-floor > 0 ? Direction.DOWN: Direction.UP;
        }
    }

    void step(){
        if(movingDirection == Direction.IDLE){
            System.out.println("Cannot move Lift, No Destination");
            return;
        }

        if(cabinRequest.isEmpty()){
            movingDirection = Direction.IDLE;
        } else {
            if(cabinRequest.contains(currentFloor)){
                open();
                cabinRequest.remove(currentFloor);
                close();
            }

            if(movingDirection == Direction.UP){
                if(currentFloor == MAX_FLOOR){
                    if(!cabinRequest.isEmpty())
                        movingDirection = Direction.DOWN;
                    else
                        movingDirection = Direction.IDLE;

                    return;
                }
                currentFloor++;
            }

            else{
                if(currentFloor == MIN_FLOOR){
                    if(!cabinRequest.isEmpty())
                        movingDirection = Direction.UP;

                    else
                        movingDirection = Direction.IDLE;

                    return;

                }
                currentFloor--;
            }
        }


    }

    int getCurrentFloor(){
        return currentFloor;
    }
    Direction getMovingDirection(){
        return movingDirection;
    }

    boolean isAvailable(){
        return movingDirection == Direction.IDLE;
    }

}