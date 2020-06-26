package Elevator.elevator.models.button.impl;

import Elevator.elevator.models.action.Action;

public class ActionButton extends AbstractButton {

    private final Action action;

    public ActionButton(final Action action) {
        this.action = action;
    }

    @Override
    public void onPress() {
        this.toggle();
        this.action.action();
    }
}
