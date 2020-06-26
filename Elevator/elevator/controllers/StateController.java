package Elevator.elevator.controllers;

import Elevator.elevator.models.Direction;
import Elevator.elevator.models.State;

public class StateController {

    private final State state;

    public StateController(final State state) {
        this.state = state;
    }

    public int getCurrentFloor() {
        return state.getCurrentFloor();
    }

    public void setCurrentFloor(final int floor) {
        state.setCurrentFloor(floor);
    }

    public Direction getDirection() {
        return state.getDirection();
    }

    public void setDirection(Direction direction) {
        state.setDirection(direction);
    }

    public boolean isMoving() {
        return state.isMoving();
    }

    public void setMoving(boolean isMoving) {
        state.setMoving(isMoving);
    }

    public void startMoving() {
        setMoving(true);
    }

    public void stopMoving() {
        setMoving(false);
    }
}
