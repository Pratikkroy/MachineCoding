package Elevator.elevator.models.action.impl;

import Elevator.elevator.models.Door;
import Elevator.elevator.models.action.Action;

public class DoorCloseAction implements Action {

    private final Door door;

    public DoorCloseAction(final Door door) {
        this.door = door;
    }

    @Override
    public void action() {
        this.door.close();
    }
}
