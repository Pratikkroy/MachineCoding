package SnakeLadder.game.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SnakeAndLadderGame {

    private final int INITIAL_NUMBER_OF_PLAYERS;
    private final int DICE_COUNT;
    private       boolean       isGameCompleted = false;
    private final Queue<Player> players;
    private final Board         board;

    public SnakeAndLadderGame(final List<Player> players, final Board board, final int diceCount) {
        this.INITIAL_NUMBER_OF_PLAYERS = players.size();
        this.players = new LinkedList<>(players);
        this.board = board;
        this.DICE_COUNT = diceCount;
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

    public int getCurrentPlayers() {
        return this.players.size();
    }

    public int getDiceCount() { return this.DICE_COUNT; }
}
