package Elevator.elevator.models;

public class Door {

    private boolean isClosed;

    public Door() {
        this.isClosed = true;
    }

    public void open() {
        isClosed = false;
        System.out.println(Thread.currentThread().getName()+" - Door is opened.");
    }

    public void close() {
        isClosed = true;
        System.out.println(Thread.currentThread().getName()+" - Door is closed.");
    }

    public boolean isClosed() {
        return isClosed;
    }
}
