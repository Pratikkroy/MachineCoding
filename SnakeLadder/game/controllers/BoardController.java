package SnakeLadder.game.controllers;

import SnakeLadder.game.services.BoardService;

public class BoardController {

    final BoardService boardService;

    public BoardController(final BoardService boardService) {
        this.boardService = boardService;
    }


    /**
     * ================== Board actions ==================
     */

    public int getNewPosition(int position) {
        return boardService.getNewPosition(position);
    }
    public int getBoardSize() {
        return boardService.getBoardSize();
    }

    public boolean canMoveToNewPosition(int newPosition) {
        return boardService.canMoveToNewPosition(newPosition);
    }

}
