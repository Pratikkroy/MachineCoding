package Elevator.elevator.models.action.impl;

import Elevator.elevator.models.Door;
import Elevator.elevator.models.action.Action;

public class DoorOpenAction implements Action {

    private final Door door;

    public DoorOpenAction(final Door door) {
        this.door = door;
    }

    @Override
    public void action() {
        this.door.open();
    }
}
