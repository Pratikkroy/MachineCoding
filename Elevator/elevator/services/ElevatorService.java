package Elevator.elevator.services;

import Elevator.elevator.controllers.ButtonController;
import Elevator.elevator.controllers.DisplayController;
import Elevator.elevator.controllers.DoorController;
import Elevator.elevator.controllers.StateController;
import Elevator.elevator.models.Direction;
import Elevator.elevator.models.Elevator;
import Elevator.elevator.models.button.impl.ActionButton;

import java.util.Map;
import java.util.Objects;

public class ElevatorService {

    private final Elevator elevator;
    private final DoorController doorController;
    private final StateController stateController;
    private final ButtonController buttonController;
    private final DisplayController displayController;

    public ElevatorService(final Elevator elevator, final DoorController doorController,
            final StateController stateController, final ButtonController buttonController,
            final DisplayController displayController) {
        this.elevator = elevator;
        this.doorController = doorController;
        this.stateController = stateController;
        this.buttonController = buttonController;
        this.displayController = displayController;
    }

    /**
     * ========= elevator actions =========
     */

    public void startElevator() {
        /**
         * takeElevatorToNextFloor will be called only one time.
         */
        closeElevatorDoor();
        takeElevatorToNextFloor();
        int elevatorCurrentFloor = getElevatorCurrentFloor();
        deactiveElevatorNumberButton(elevatorCurrentFloor);
        deactiveElevatorRequestButton(elevatorCurrentFloor);
        displayElevatorStatus();
        openElevatorDoor();
    }

    /**
     * this method will only return false to indicate that elevator has reached the floor requested and stopped now.
     */
    private boolean takeElevatorToNextFloor() {
        Direction directionToMove = getElevatorDirectionToMove(
                getElevatorCurrentFloor(), getElevatorCurrentDirection());
        if(Objects.isNull(directionToMove)) {
            return false;
        }
        setElevatorDirection(directionToMove);
        startElevatorMoving();
        while(isElevatorMoving()) {

            displayElevatorStatus();
            if(isElevatorMovingUp()){
                moveElevatorUp();
            }
            else if(isElevatorMovingDown()) {
                moveElevatorDown();
            }
            else {
                break;
            }

            int currentFloor = getElevatorCurrentFloor();
            if(checkIfNumberButtonIsActive(currentFloor) || checkIfElevatorRequestButtonIsActive(currentFloor)) {
                stopElevator();
                break;
            }
        }
        return false;
    }

    public void startElevatorMoving() {
       stateController.startMoving();
    }

    public void moveElevatorUp() {
        stateController.setCurrentFloor(getElevatorCurrentFloor()+1);
    }

    public void moveElevatorDown() {
        stateController.setCurrentFloor(getElevatorCurrentFloor()-1);
    }

    public void stopElevator() {
        stateController.stopMoving();
    }

    public void setElevatorDirection(Direction direction) {
        stateController.setDirection(direction);
    }

    public void activeElevatorRequestButtonToGoUp(final int requestAtFloor) {
        activeElevatorRequestButton(requestAtFloor, Direction.UP);
    }

    public void activeElevatorRequestButtonToGoDown(final int requestAtFloor) {
        activeElevatorRequestButton(requestAtFloor, Direction.DOWN);
    }

    public void deactiveElevatorRequestButton(final int floor) {
        Map<Direction, ActionButton> elevatorRequestButtonMapAtFloor = elevator.getElevatorRequestButtonMap().get(floor);
        for(Map.Entry<Direction,ActionButton> entry: elevatorRequestButtonMapAtFloor.entrySet()){
            buttonController.deactiveButton(entry.getValue());
        }
    }

    public void openElevatorDoor() {
        doorController.openDoor();
    }

    public void closeElevatorDoor() {
        doorController.closeDoor();
    }

    public void activeElevatorNumberButton(final int floor) {
        buttonController.activeButton(elevator.getFloorNumbersButton().get(floor));
    }

    public void deactiveElevatorNumberButton(final int floor) {
        buttonController.deactiveButton(elevator.getFloorNumbersButton().get(floor));
    }

    private void activeElevatorRequestButton(final int floor, final Direction direction) {
        buttonController.activeButton(elevator.getElevatorRequestButtonMap().get(floor).get(direction));
    }

    private void toggleElevatorNumberButton(final int floor) {
        buttonController.toggleButton(elevator.getFloorNumbersButton().get(floor));
    }

    /**
     * ========= elevator services =========
     */

    public boolean isElevatorAtTopFloor() {
       return getElevatorCurrentFloor() == elevator.getMaxFloors();
    }

    public boolean isElevatorAtGroundFloor() {
        return getElevatorCurrentFloor() == 0;
    }

    public int getElevatorCurrentFloor() {
        return stateController.getCurrentFloor();
    }

    public int getMaxNumberOfFloor() {
        return elevator.getMaxFloors();
    }

    public Direction getElevatorCurrentDirection() {
        return stateController.getDirection();
    }

    public boolean isElevatorMoving() {
        return stateController.isMoving();
    }

    public boolean isElevatorMovingUp() {
        return getElevatorCurrentDirection().equals(Direction.UP);
    }

    public boolean isElevatorMovingDown() {
        return getElevatorCurrentDirection().equals(Direction.DOWN);
    }

    public void displayElevatorStatus() {
        try{
            Thread.sleep(1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        displayController.display();
    }

    private boolean checkIfNumberButtonIsActive(final int number) {
        return buttonController.isButtonActive(elevator.getFloorNumbersButton().get(number));
    }

    private boolean checkIfElevatorRequestButtonIsActive(final int floor) {
        Map<Direction, ActionButton> elevatorRequestButtonMapAtFloor = elevator.getElevatorRequestButtonMap().get(floor);
        for(Map.Entry<Direction,ActionButton> entry: elevatorRequestButtonMapAtFloor.entrySet()){
            if(buttonController.isButtonActive(entry.getValue())) {
                return true;
            }
        }
        return false;
    }



    /**
     * Calculate in which direction the elevator will be moving
     */
    public Direction getElevatorDirectionToMove(final int elevatorCurrentFloor,
            final Direction elevatorCurrentDirection) {
        if(isElevatorAtTopFloor()) {
            return Direction.DOWN;
        }
        else if(isElevatorAtGroundFloor()) {
            return Direction.UP;
        }

        boolean hasElevatorRequestedToGoUp = hasElevatorRequestedToGoUp(elevatorCurrentFloor);
        boolean hasElevatorRequestedToGoDown = hasElevatorRequestedToGoDown(elevatorCurrentFloor);

        if(elevatorCurrentDirection.equals(Direction.UP)) {
            if(hasElevatorRequestedToGoUp) {
                return Direction.UP;
            }
            else if(hasElevatorRequestedToGoDown) {
                return Direction.DOWN;
            }
        }

        if(elevatorCurrentDirection.equals(Direction.DOWN)) {
            if(hasElevatorRequestedToGoDown) {
                return Direction.DOWN;
            }
            else if(hasElevatorRequestedToGoUp) {
                return Direction.UP;
            }
        }
        return null;
    }

    /**
     * check if
     * there is a request from a floor > elevator.currentFloor from outside the elevator
     * OR
     * there is a request to go to a floor > elevator.currentFloor from inside the elevator
     */
    private boolean hasElevatorRequestedToGoUp(final int elevatorCurrentFloor) {
        for(int floorNumber = elevator.getMaxFloors(); floorNumber > elevatorCurrentFloor; floorNumber--) {
            if(checkIfElevatorRequestButtonIsActive(floorNumber) || checkIfNumberButtonIsActive(floorNumber)){
                return true;
            }
        }
        return false;
    }

    /**
     * check if
     * there is a request from a floor < elevator.currentFloor from outside the elevator
     * OR
     * there is a request to go to a floor < elevator.currentFloor from inside the elevator
     */
    private boolean hasElevatorRequestedToGoDown(final int elevatorCurrentFloor) {
        for(int floorNumber = 0; floorNumber < elevatorCurrentFloor; floorNumber++) {
            if(checkIfElevatorRequestButtonIsActive(floorNumber) || checkIfNumberButtonIsActive(floorNumber)){
                return true;
            }
        }
        return false;
    }
}
