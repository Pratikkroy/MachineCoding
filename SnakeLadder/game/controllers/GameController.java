package SnakeLadder.game.controllers;

import SnakeLadder.game.models.Board;
import SnakeLadder.game.models.Ladder;
import SnakeLadder.game.models.Player;
import SnakeLadder.game.models.Snake;
import SnakeLadder.game.services.BoardService;

import java.util.List;

public class GameController {

    private final int               INITIAL_NUMBER_OF_PLAYERS;
    private final BoardController   boardController;
    private final PlayersController playersController;
    private       boolean           isGameCompleted = false;

    public GameController(final int boardSize, final int diceCount,
            final List<Snake> snakes, final List<Ladder> ladders, final List<Player> players) {
        this.INITIAL_NUMBER_OF_PLAYERS = players.size();

        // initialise board
        Board board = new Board(boardSize);
        board.setSnakes(snakes);
        board.setLadders(ladders);
        BoardService boardService = new BoardService(board, diceCount);
        boardController = new BoardController(boardService);

        // initialise players
        playersController = new PlayersController(players);
    }

    public void startGame() {
        while (!isGameCompleted) {
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
                isGameCompleted = true;
            }
        }
    }

    private boolean hasPlayerWon(Player player) {
        return boardController.getBoardSize() == playersController.getPlayerPosition(player);
    }
}
