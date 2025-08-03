package model;

import java.util.Random;

/**
 * Default implementation of {@link GameModel}.
 * <p>
 *     This class uses a 4*4 board as the game board and handles tile merging logic, score calculation,
 *     and random tile generation.
 * </p>
 *
 * @author Chongzhe Jin
 * @version 1.0, 2025/8/3
 * @since 2025/8/3 00:19
 */
public class GameModelImpl implements GameModel {
    private int score;
    private int[][] board;
    private Random random;

    /**
     * Create a new game model witha score of 0, a 4*4 board and a random number generator with no seed.
     */
    public GameModelImpl() {
        score = 0;
        board = new int[4][4];
        random = new Random();
    }

    /**
     * Create a new game model with a specified score, board, and random number generator.
     *
     * @param score the score of the game
     * @param board the game board as a 2D array
     * @param random the random number generator to be used for tile generation
     */
    public GameModelImpl(int score, int[][] board, Random random) {
        this.score = score;
        this.board = board;
        this.random = random;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public int[][] getBoard() {
        int[][] copy = new int[this.board.length][this.board[0].length];
        for (int i = 0; i < this.board.length; i++) {
            System.arraycopy(this.board[i], 0, copy[i], 0, this.board[i].length);
        }
        return copy;
    }

    @Override
    public void reset() {
        this.score = 0;
        this.board = new int[4][4];
        this.random = new Random();
        this.generateTile();
        this.generateTile();
    }

    // generate a tile in the board randomly
    // TODO implement this method
    private void generateTile() {
    }

    @Override
    public boolean move(Direction d) {
        return false;
    }

    @Override
    public Status isGameOver() {
        return null;
    }
}
