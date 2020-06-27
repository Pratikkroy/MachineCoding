package Elevator.elevator.controllers;

import Elevator.elevator.models.Direction;
import Elevator.elevator.models.Display;

public class DisplayController {

    private final Display display;

    public DisplayController(final Display display) {
        this.display = display;
    }

    public Direction getDirection() {
        return display.getDirection();
    }

    public int getFloor() {
        return display.getFloor();
    }

    public boolean isMoving() { return display.isMoving(); }

    public void display() {
        System.out.println(Thread.currentThread().getName()+" - Current floor = ["+getFloor()+"]   Current direction "
                + "= ["+getDirection()+"]   IsMoving = "
                + "["+isMoving()+"]" );
    }
}
