package Elevator.elevator;

import Elevator.elevator.controllers.ElevatorController;

public class Driver {
    public static void main(String[] args) {
        final int MAX_PEOPLE_ACCOMODATE = 20;
        final int MAX_FLOORS = 10;
        ElevatorController elevatorController = new ElevatorController(MAX_PEOPLE_ACCOMODATE, MAX_FLOORS);

        sleep(3);

        int previousFloor = 0;
        int nextFloor = 0;

        previousFloor = nextFloor;
        nextFloor = 5;
        elevatorController.requestToGoDownwardDirection(nextFloor);
        sleep(nextFloor-previousFloor+2);

        previousFloor = nextFloor;
        nextFloor = 2;
        elevatorController.requestToGoToFloor(nextFloor);
//        sleep(nextFloor-previousFloor);

        previousFloor = nextFloor;
        nextFloor = 4;
        elevatorController.requestToGoUpwardDirection(nextFloor);
        sleep(nextFloor-previousFloor);

        previousFloor = nextFloor;
        nextFloor = 8;
        elevatorController.requestToGoToFloor(nextFloor);
        sleep(nextFloor-previousFloor);
    }

    private static void sleep(int timeInSeconds){
        timeInSeconds = Math.abs(timeInSeconds);
        try {
            Thread.currentThread().join(timeInSeconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
