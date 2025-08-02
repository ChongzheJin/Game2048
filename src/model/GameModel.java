package model;


/**
 * This interface is used to define behavior of the game. It has a 4*4 board as game board and a score counter. It will
 * start with 2 number blocks(2 or 4) on the board, random location. The game will continue until the player can't make
 * the next move or the player gets a 2048 number block. Player can move all the number blocks in the board once by
 * using keyboard arrow keys(up, down, left, right).
 *
 * @author Chongzhe Jin
 * @version 1.0, 2025/8/2
 * @since 2025/8/2 23:22
 */
public interface GameModel {
    int getScore();

    int[][] getBoard();

    /**
     * Reset the game to initial state.
     */
    void reset();

    /**
     * Move all number blocks in the game board in one direction, as far as they can go. They can be stopped by boarder
     * of the board or other number blocks. If a number block reaches other number block with the same number,
     * they will merge into one larger number block.
     *
     * @param d one of the four directions
     * @return true if the move is successful, false otherwise
     */
    boolean move(Direction d);

    /**
     * Determine whether the game is over. The status is LOSE if the player can't make any move; it is WIN if the player
     * gets a 2048 number block; otherwise it is CONTINUE.
     *
     * @return the status of the game
     */
    Status isGameOver();
}
