package Elevator.elevator.models.button;

public interface Button {
    void onPress();

    void toggle();

    boolean isActive();

    void setActive(final boolean active);
}
