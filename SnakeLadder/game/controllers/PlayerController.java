package SnakeLadder.game.controllers;

import SnakeLadder.game.models.Player;

public class PlayerController {

    public void movePlayer(Player player, int newPosition) {
        player.setPosition(newPosition);
    }

    public int getPlayerPosition(Player player) {
        return player.getPosition();
    }

}
