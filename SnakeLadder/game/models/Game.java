package SnakeLadder.game.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {

    private final int           INITIAL_NUMBER_OF_PLAYERS;
    private final Queue<Player> players;
    private final Board         board;
    private       boolean       isGameCompleted = false;

    public Game(final List<Player> players, final Board board) {
        this.INITIAL_NUMBER_OF_PLAYERS = players.size();
        this.players = new LinkedList<>(players);
        this.board = board;
    }

    public void setNextPlayer(final Player player) {
        this.players.add(player);
    }

    public Player getNextPlayer() {
        return this.players.poll();
    }

    public boolean hasGameCompleted() {
        return isGameCompleted;
    }

    public void setGameCompleted(final boolean isGameCompleted) {
        this.isGameCompleted = isGameCompleted;
    }
}
