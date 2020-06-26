package Elevator.elevator.controllers;

import Elevator.elevator.models.Door;

public class DoorController {

    private final Door door;

    public DoorController(final Door door) {
        this.door = door;
    }

    public void openDoor() {
        if(isDoorClosed())
            this.door.open();
    }

    public void closeDoor() {
        if(isDoorOpened())
            this.door.close();
    }

    public boolean isDoorClosed() {
        return this.door.isClosed();
    }

    public boolean isDoorOpened() {
        return !this.door.isClosed();
    }
}
