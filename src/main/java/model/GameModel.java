package model;


/**
 * This interface is used to define behavior of the game.
 * <p>
 *     It maintains a game board and score. And support operations to move tiles, reset the game, and check game status.
 * </p>
 *
 * @author Chongzhe Jin
 * @version 3.0, 2025/8/6
 * @since 2025/8/2 23:22
 */
public interface GameModel {
    int getScore();

    int[][] getBoard();

    /**
     * Reset the game to initial state.
     */
    Status reset();

    /**
     * Move all number blocks in the game board in one direction, as far as they can go. They can be stopped by boarder
     * of the board or other number blocks. If a number block reaches other number block with the same number,
     * they will merge into one larger number block.
     *
     * @param d one of the four directions
     * @return status of the game after the move
     */
    Status move(Direction d);
}
