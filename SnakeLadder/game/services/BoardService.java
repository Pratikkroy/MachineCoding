package SnakeLadder.game.services;

import SnakeLadder.game.models.Board;
import SnakeLadder.game.models.Ladder;
import SnakeLadder.game.models.Snake;

import static SnakeLadder.game.constants.ApplicationConstants.DICE_MAX;
import static SnakeLadder.game.constants.ApplicationConstants.DICE_ROLL_MAX_TURNS;

public class BoardService {

    private final Board board;
    private final int DICE_COUNT;

    public BoardService(final Board board, final int diceCount) {
        this.board = board;
        this.DICE_COUNT = diceCount;
    }

    private int rollDice() {
        int total = 0;
        for (int i = 0; i < DICE_COUNT; i++) {
            total += DiceService.rollDice();
        }
        return total;
    }

    private int getStepsToMove() {
        int total = 0;
        for (int i = 1; i <= DICE_ROLL_MAX_TURNS; i++) {
            total += rollDice();
            if (total != (i + 1) * DICE_MAX * DICE_COUNT) {
                return total;
            }
        }
        // On getting three consecutive sixes all get cancelled
        return 0;
    }

    public int getNewPosition(int position) {
        int stepsToMove = getStepsToMove();

        int previousPosition = position;
        int newPosition = previousPosition;

        do {
            for(Ladder ladder: board.getLadders()) {
                if(previousPosition + stepsToMove == ladder.getStart()) {
                    newPosition = ladder.getEnd();
                }
            }

            for(Snake snake: board.getSnakes()) {
                if(previousPosition + stepsToMove == snake.getStart()) {
                    newPosition = snake.getEnd();
                }
            }
        }while(previousPosition!=newPosition);

        return newPosition;
    }

    public int getBoardSize() {
        return board.getBoardSize();
    }

    public boolean canMoveToNewPosition(int newPosition) {
        return newPosition <= getBoardSize();
    }

}
