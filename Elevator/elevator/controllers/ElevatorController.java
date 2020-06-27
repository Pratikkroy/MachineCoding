package Elevator.elevator.controllers;

import Elevator.elevator.models.Elevator;
import Elevator.elevator.services.ElevatorService;


public class ElevatorController {

    private final ElevatorService elevatorService;

    public ElevatorController(final int maxNumberOfPeople, final int maxNumberOfFloor) {
        Elevator elevator = new Elevator(maxNumberOfPeople, maxNumberOfFloor);
        DoorController doorController = new DoorController(elevator.getElevatorDoor());
        StateController stateController = new StateController(elevator.getElevatorState());
        ButtonController buttonController = new ButtonController();
        DisplayController displayController = new DisplayController(elevator.getElevatorDisplay());
        this.elevatorService = new ElevatorService(elevator, doorController, stateController, buttonController, displayController);

        Thread thread = new Thread(() -> {
            Thread.currentThread().setName("ELEVATOR THREAD");
            System.out.println(Thread.currentThread().getName()+" - ELEVATOR START");
            while(true) {
                elevatorService.startElevator();
            }
        });
        thread.start();
    }

    /**
     * Methods to be called from outside the elevator
     */
    public void requestToGoUpwardDirection(final int requestAtFloor) {
        System.out.println(Thread.currentThread().getName()+" - requestToGoUpwardDirection from requestAtFloor = ["+requestAtFloor+"]");
        if(!checkIfItIsSafeToExecute(requestAtFloor)) {
            System.out.println(Thread.currentThread().getName()+" - Invalid input");
            return;
        }
        if(requestAtFloor == elevatorService.getMaxNumberOfFloor()) {
            System.out.println(Thread.currentThread().getName()+" - Can't go upward. Already at top floor");
            return;
        }
        elevatorService.activeElevatorRequestButtonToGoUp(requestAtFloor);
//        elevatorService.startElevator();

    }

    public void requestToGoDownwardDirection(final int requestAtFloor) {
        System.out.println(Thread.currentThread().getName()+" - requestToGoDownwardDirection from requestAtFloor = ["+requestAtFloor+"]");
        if(!checkIfItIsSafeToExecute(requestAtFloor)) {
            System.out.println(Thread.currentThread().getName()+" - Invalid input");
            return;
        }
        if(requestAtFloor == 0) {
            System.out.println(Thread.currentThread().getName()+" - Can't go downward. Already at ground floor");
            return;
        }
        elevatorService.activeElevatorRequestButtonToGoDown(requestAtFloor);
//        elevatorService.startElevator();
    }

    /**
     * Methods to be called from inside the elevator
     */
    public void requestToGoToFloor(final int floorNumber) {
        System.out.println(Thread.currentThread().getName()+" - requestToGoToFloor = ["+floorNumber+"]");
        if(!checkIfItIsSafeToExecute(floorNumber)) {
            System.out.println(Thread.currentThread().getName()+" - Invalid input");
            return;
        }
        elevatorService.activeElevatorNumberButton(floorNumber);
//        elevatorService.startElevator();
    }

    public void requestToNoToGoFloor(final int floorNumber) {
        System.out.println(Thread.currentThread().getName()+" - requestToNoToGoFloor = ["+floorNumber+"]");
        elevatorService.deactiveElevatorNumberButton(floorNumber);
//        elevatorService.startElevator();
    }

    public void requestToOpenDoor() {
        System.out.println(Thread.currentThread().getName()+" - requestToOpenDoor");
        elevatorService.openElevatorDoor();
    }

    public void requestToCloseDoor() {
        System.out.println(Thread.currentThread().getName()+" - requestToCloseDoor");
        elevatorService.closeElevatorDoor();
    }


    private boolean checkIfItIsSafeToExecute(int floorNumber) {
        if(floorNumber>=0 && floorNumber<=elevatorService.getMaxNumberOfFloor()) {
            return true;
        }
        return false;
    }
}
