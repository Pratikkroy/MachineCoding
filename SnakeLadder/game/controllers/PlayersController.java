package SnakeLadder.game.controllers;

import SnakeLadder.game.models.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PlayersController {

    private final Queue<Player> players;

    public PlayersController(final List<Player> players) {
        this.players = new LinkedList<>(players);
    }

    public void setNextPlayer(Player player) {
        this.players.add(player);
    }

    public Player getNextPlayer() {
        return this.players.poll();
    }

    public void movePlayer(Player player, int newPosition) {
        player.setPosition(newPosition);
    }

    public int getPlayerPosition(Player player) {
        return player.getPosition();
    }

    public int getCurrentPlayers() {
        return this.players.size();
    }
}
