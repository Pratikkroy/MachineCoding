package SnakeLadder.game.controllers;

import SnakeLadder.game.models.Board;
import SnakeLadder.game.models.Ladder;
import SnakeLadder.game.models.Snake;

public class BoardController {

    private final Board board;

    public BoardController(final Board board) {
        this.board = board;
    }


    /**
     * ================== Board Services ==================
     */

    public int getNewPosition(final int position, final int stepsToMove) {

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
