package SnakeLadder.game.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private int                  size;
    private List<Snake>          snakes;
    private List<Ladder>         ladders;
    private Map<String, Integer> playerPieces;

    public Board(int size) {
        this.size = size;
        this.snakes = new ArrayList<Snake>();
        this.ladders = new ArrayList<Ladder>();
        this.playerPieces = new HashMap<String, Integer>();
    }

    public int getSize() {
        return size;
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

    public Map<String, Integer> getPlayerPieces() {
        return playerPieces;
    }

    public void setPlayerPieces(final Map<String, Integer> playerPieces) {
        this.playerPieces = playerPieces;
    }
}
