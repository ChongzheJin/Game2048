package view;

import model.Status;

import java.io.IOException;

/**
 * This interface is used to define the view of the game.
 *
 * @author Chongzhe Jin
 * @version 1.0, 2025/8/5
 * @since 2025/8/5 00:47
 */
public interface GameView {
    /**
     * Read the input from the user.
     *
     * @return the trimmed input string or {@code null} if no input
     */
    String readInput();

    /**
     * Show the start screen.
     */
    void showStart();

    /**
     * Show the help screen.
     */
    void showHelp();

    /**
     * Show the game board and score.
     *
     * @param board the game board
     * @param score the score
     */
    void update(int[][] board, int score);

    /**
     * Show the game over screen.
     *
     * @param status the game status
     * @param score  the score
     */
    void showGameOver(Status status, int score);
}
