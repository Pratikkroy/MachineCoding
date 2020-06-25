package SnakeLadder.game.controllers;

import SnakeLadder.game.models.*;
import SnakeLadder.game.services.BoardService;

import java.util.List;

public class GameController {

    private final Game              snakeAndLadderGame;
    private final BoardController   boardController;
    private final PlayersController playersController;


    public GameController(final int boardSize, final int diceCount,
            final List<Snake> snakes, final List<Ladder> ladders, final List<Player> players) {

        // initialise board
        final Board board = new Board(boardSize);
        board.setSnakes(snakes);
        board.setLadders(ladders);
        BoardService boardService = new BoardService(board, diceCount);
        boardController = new BoardController(boardService);

        // initialise players
        playersController = new PlayersController(players);

        // initialise game
        snakeAndLadderGame = new Game(players, board);

    }

    public void startGame() {
        while (!snakeAndLadderGame.hasGameCompleted()) {
            Player player = playersController.getNextPlayer();

            int newPosition = boardController.getNewPosition(playersController.getPlayerPosition(player));

            if(boardController.canMoveToNewPosition(newPosition)) {
                playersController.movePlayer(player, newPosition);
            }

            if (hasPlayerWon(player)) {
                System.out.println(player.getName()+" has won.");
            }
            else {
                playersController.setNextPlayer(player);

            }

            if (playersController.getCurrentPlayers() == 1) {
                snakeAndLadderGame.setGameCompleted(true);
            }
        }
    }

    private boolean hasPlayerWon(final Player player) {
        return boardController.getBoardSize() == playersController.getPlayerPosition(player);
    }

}
