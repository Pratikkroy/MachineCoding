package SnakeLadder.game.controllers;

import SnakeLadder.game.models.*;
import SnakeLadder.game.services.DiceService;

import java.util.List;

import static SnakeLadder.game.constants.ApplicationConstants.DICE_MAX;
import static SnakeLadder.game.constants.ApplicationConstants.DICE_ROLL_MAX_TURNS;

public class GameController {

    private final SnakeAndLadderGame snakeAndLadderGame;
    private final BoardController    boardController;
    private final PlayerController   playerController;


    public GameController(final int boardSize, final int diceCount,
            final List<Snake> snakes, final List<Ladder> ladders, final List<Player> players) {

        // initialise board
        final Board board = new Board(boardSize);
        board.setSnakes(snakes);
        board.setLadders(ladders);
        boardController = new BoardController(board);

        // initialise players
        playerController = new PlayerController();

        // initialise game
        snakeAndLadderGame = new SnakeAndLadderGame(players, board, diceCount);

    }

    public void startGame() {
        while (!snakeAndLadderGame.hasGameCompleted()) {
            Player player = snakeAndLadderGame.getNextPlayer();

            int playerCurrentPosition = playerController.getPlayerPosition(player);
            int stepsToMove = getStepsToMove(snakeAndLadderGame.getDiceCount());
            int newPosition = boardController.getNewPosition(playerCurrentPosition, stepsToMove);

            if(boardController.canMoveToNewPosition(newPosition)) {
                playerController.movePlayer(player, newPosition);
            }

            if (hasPlayerWon(player)) {
                System.out.println(player.getName()+" has won.");
            }
            else {
                snakeAndLadderGame.setNextPlayer(player);
            }

            if (snakeAndLadderGame.getCurrentPlayers() == 1) {
                snakeAndLadderGame.setGameCompleted(true);
            }
        }
    }

    private boolean hasPlayerWon(final Player player) {
        return boardController.getBoardSize() == playerController.getPlayerPosition(player);
    }

    private int rollDice(final int diceCount) {
        int total = 0;
        for (int i = 0; i < diceCount; i++) {
            total += DiceService.rollDice();
        }
        return total;
    }

    private int getStepsToMove(final int diceCount) {
        int total = 0;
        for (int i = 1; i <= DICE_ROLL_MAX_TURNS; i++) {
            total += rollDice(diceCount);
            if (total != (i + 1) * DICE_MAX * diceCount) {
                return total;
            }
        }
        // On getting three consecutive sixes all get cancelled
        return 0;
    }
}
