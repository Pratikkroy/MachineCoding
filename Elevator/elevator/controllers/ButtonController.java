package Elevator.elevator.controllers;

import Elevator.elevator.models.button.Button;

public class ButtonController {

    public void toggleButton(final Button button) {
        button.toggle();
    }

    public void activeButton(final Button button) {
        button.setActive(true);
    }

    public void deactiveButton(final Button button) {
        button.setActive(false);
    }

    public boolean isButtonActive(final Button button) { return button.isActive(); }
}
