package Elevator.elevator;

import Elevator.elevator.controllers.ElevatorController;

public class Driver {
    public static void main(String[] args) {
        final int MAX_PEOPLE_ACCOMODATE = 20;
        final int MAX_FLOORS = 10;
        ElevatorController elevatorController = new ElevatorController(MAX_PEOPLE_ACCOMODATE, MAX_FLOORS);

        elevatorController.requestToGoDownwardDirection(5);
        elevatorController.requestToGoToFloor(2);
        elevatorController.requestToGoUpwardDirection(4);
        elevatorController.requestToGoToFloor(8);
    }
}
