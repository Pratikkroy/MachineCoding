package Elevator.elevator.models.button.impl;

public class NumberButton extends AbstractButton {

    private final int number;

    public NumberButton(final int number) {
        this.number = number;
    }

    @Override
    public void onPress() {
        this.toggle();
    }
}
