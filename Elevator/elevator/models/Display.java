package Elevator.elevator.models;

// We could have made this inner class of Elevator class as this is only a part of Elevator
public class Display {

    private final State state;

    public Display(final State state) {
        this.state = state;
    }

    public Direction getDirection() {
        return state.getDirection();
    }

    public int getFloor() {
        return state.getCurrentFloor();
    }

    public boolean isMoving() { return state.isMoving(); }
}
