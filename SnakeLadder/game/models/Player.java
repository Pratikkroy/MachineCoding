package SnakeLadder.game.models;

import java.util.UUID;

import static SnakeLadder.game.constants.ApplicationConstants.PLAYER_INITIAL_POSITION;

public class Player {

    private final String id;
    private final String name;
    private       int    position;

    public Player(final String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.position = PLAYER_INITIAL_POSITION;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
