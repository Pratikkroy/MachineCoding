package Elevator.elevator.models.button.impl;

import Elevator.elevator.models.button.Button;

public abstract class AbstractButton implements Button {
    private boolean isActive;

    public AbstractButton() {
        this.isActive = false;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(final boolean active) {
        isActive = active;
    }

    @Override
    public void toggle() {
        setActive(!isActive());
    }
}
