package SnakeLadder.game.services;

import java.util.Random;

import static SnakeLadder.game.constants.ApplicationConstants.DICE_MAX;

public class DiceService {

    private static final Random random = new Random();

    public static int rollDice() {
        return (random.nextInt() % DICE_MAX) + 1;
    }
}
