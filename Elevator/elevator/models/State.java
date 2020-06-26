package Elevator.elevator.models;

// We could have made this inner class of Elevator class as this is only a part of Elevator
public class State {

    private Direction direction;
    private boolean   isMoving;
    private int       currentFloor;

    public State(final Direction direction, final boolean isMoving, final int currentFloor) {
        this.direction = direction;
        this.isMoving = isMoving;
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(final boolean moving) {
        isMoving = moving;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(final int currentFloor) {
        this.currentFloor = currentFloor;
    }
}

