package SnakeLadder.game.models;

public class Snake {

    private int start;
    private int end;

    public Snake(final int start, final int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
