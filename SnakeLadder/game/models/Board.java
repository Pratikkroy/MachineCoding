package SnakeLadder.game.models;

import java.util.*;

public class Board {

    private final int BOARD_SIZE;

    private List<Snake>  snakes;
    private List<Ladder> ladders;

    public Board(final int boardSize) {
        BOARD_SIZE = boardSize;
        snakes = new ArrayList<>();
        ladders = new ArrayList<>();
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(final List<Snake> snakes) {
        this.snakes = snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public void setLadders(final List<Ladder> ladders) {
        this.ladders = ladders;
    }

    public int getBoardSize() {
        return this.BOARD_SIZE;
    }
}
