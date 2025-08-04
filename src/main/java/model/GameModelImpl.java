package model;

import java.util.Arrays;
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
    private final int col;
    private final int row;
    private final Random random;

    /**
     * Create a new game model with score of 0, a 4*4 board and a random number generator with no seed.
     */
    public GameModelImpl() {
        this.score = 0;
        this.board = new int[4][4];
        this.col = 4;
        this.row = 4;
        this.random = new Random();
        this.generateTile();
        this.generateTile();
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
        this.col = board[0].length;
        this.row = board.length;
        this.random = random;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public int[][] getBoard() {
        int[][] copy = new int[this.col][this.row];
        for (int i = 0; i < this.col; i++) {
            System.arraycopy(this.board[i], 0, copy[i], 0, this.board[i].length);
        }
        return copy;
    }

    @Override
    public void reset() {
        this.score = 0;
        this.board = new int[this.col][this.row];
        this.generateTile();
        this.generateTile();
    }

    // generate a tile in the board randomly
    private void generateTile() {
        // count the number of empty positions in the board
        int emptyPos = 0;
        for (int i = 0; i < this.col; i++) {
            for (int j = 0; j < this.row; j++) {
                if (this.board[i][j] == 0) {
                    emptyPos++;
                }
            }
        }
        // if there is no empty position, return
        if (emptyPos == 0) {
            return;
        }

        // generate a random position
        int pos = this.random.nextInt(emptyPos);
        for (int i = 0; i < this.col; i++) {
            for (int j = 0; j < this.row; j++) {
                if (this.board[i][j] == 0) {
                    if (pos == 0) {
                        // generate a random tile: 75% 2 or 25% 4
                        this.board[i][j] = random.nextInt(100) < 75 ? 2 : 4;
                        return;
                    }
                    pos--;
                }
            }
        }
    }

    @Override
    public Status move(Direction d) {
        int[][] copy = this.getBoard();
        Status res = switch (d) {
            case UP -> this.moveUp();
            case DOWN -> this.moveDown();
            case LEFT -> this.moveLeft();
            case RIGHT -> this.moveRight();
        };

        // check if the board has changed
        if (!Arrays.deepEquals(copy, this.board)) {
            // generate a new tile, if tiles actually moved
            this.generateTile();
        }

        // Check for lose condition: board is full and no moves possible
        if (res != Status.WIN) {
            res = this.isLose();
        }


        return res;
    }

    private Status moveRight() {
        Status res = Status.CONTINUE;
        for (int i = 0; i < this.row; i++) {
            boolean[] merged = new boolean[this.col];
            for (int j = this.col - 2; j >= 0; j--) {
                // skip empty positions
                if (this.board[i][j] == 0) {
                    continue;
                }
                int targetCol = j;
                // move target to the rightmost empty position
                while (targetCol < this.col - 1 && this.board[i][targetCol + 1] == 0) {
                    board[i][targetCol + 1] = board[i][targetCol];
                    board[i][targetCol] = 0;
                    targetCol++;
                }

                // check if the target can be merged
                if (targetCol < this.col - 1
                        && !merged[targetCol + 1]
                        && this.board[i][targetCol] == this.board[i][targetCol + 1]) {
                    this.board[i][targetCol + 1] *= 2;
                    this.board[i][targetCol] = 0;
                    this.score += this.board[i][targetCol + 1];
                    if (this.board[i][targetCol + 1] == 2048) {
                        res = Status.WIN;
                    }
                    merged[targetCol + 1] = true;
                }
            }
        }

        return res;
    }

    private Status moveLeft() {
        Status res = Status.CONTINUE;
        for (int i = 0; i < this.row; i++) {
            boolean[] merged = new boolean[this.col];
            for (int j = 1; j < this.col; j++) {
                // skip empty positions
                if (this.board[i][j] == 0) {
                    continue;
                }
                int targetCol = j;
                // move target to the leftmost empty position
                while (targetCol > 0 && this.board[i][targetCol - 1] == 0) {
                    board[i][targetCol - 1] = board[i][targetCol];
                    board[i][targetCol] = 0;
                    targetCol--;
                }

                // check if the target can be merged
                if (targetCol > 0
                        && !merged[targetCol - 1]
                        && this.board[i][targetCol] == this.board[i][targetCol - 1]) {
                    this.board[i][targetCol - 1] *= 2;
                    this.board[i][targetCol] = 0;
                    this.score += this.board[i][targetCol - 1];
                    if (this.board[i][targetCol - 1] == 2048) {
                        res = Status.WIN;
                    }
                    merged[targetCol - 1] = true;
                }
            }
        }
        
        return res;
    }

    private Status moveDown() {
        Status res = Status.CONTINUE;
        for (int j = 0; j < this.col; j++) {
            boolean[] merged = new boolean[this.row];
            for (int i = this.row - 2; i >= 0; i--) {
                // skip empty positions
                if (this.board[i][j] == 0) {
                    continue;
                }
                int targetRow = i;
                // move target to the down most empty position
                while (targetRow < this.row - 1 && this.board[targetRow + 1][j] == 0) {
                    board[targetRow + 1][j] = board[targetRow][j];
                    board[targetRow][j] = 0;
                    targetRow++;
                }

                // check if the target can be merged
                if (targetRow < this.row - 1
                        && !merged[targetRow + 1]
                        && this.board[targetRow][j] == this.board[targetRow + 1][j]) {
                    this.board[targetRow + 1][j] *= 2;
                    this.board[targetRow][j] = 0;
                    this.score += this.board[targetRow + 1][j];
                    if (this.board[targetRow + 1][j] == 2048) {
                        res = Status.WIN;
                    }
                    merged[targetRow + 1] = true;
                }
            }
        }

        return res;
    }

    private Status moveUp() {
        Status res = Status.CONTINUE;
        for (int j = 0; j < this.col; j++) {
            boolean[] merged = new boolean[this.row];
            for (int i = 1; i < this.row; i++) {
                // skip empty positions
                if (this.board[i][j] == 0) {
                    continue;
                }
                int targetRow = i;
                // move target to the upmost empty position
                while (targetRow > 0 && this.board[targetRow - 1][j] == 0) {
                    board[targetRow - 1][j] = board[targetRow][j];
                    board[targetRow][j] = 0;
                    targetRow--;
                }

                // check if the target can be merged
                if (targetRow > 0
                        && !merged[targetRow - 1]
                        && this.board[targetRow][j] == this.board[targetRow - 1][j]) {
                    this.board[targetRow - 1][j] *= 2;
                    this.board[targetRow][j] = 0;
                    this.score += this.board[targetRow - 1][j];
                    if (this.board[targetRow - 1][j] == 2048) {
                        res = Status.WIN;
                    }
                    merged[targetRow - 1] = true;
                }
            }
        }

        return res;
    }

    private Status isLose() {
        boolean isFull = true;
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                if (this.board[i][j] == 0) {
                    isFull = false;
                    break;
                }
            }
            if (!isFull) break;
        }

        if (isFull) {
            boolean canMove = isCanMove();

            if (!canMove) {
                return Status.LOSE;
            }
        }

        return Status.CONTINUE;
    }

    private boolean isCanMove() {
        boolean canMove = false;
        // Check if any moves are possible
        for (int i = 0; i < this.row && !canMove; i++) {
            for (int j = 0; j < this.col && !canMove; j++) {
                // Check right
                if (j < this.col - 1 && this.board[i][j] == this.board[i][j + 1]) {
                    canMove = true;
                }
                // Check down
                else if (i < this.row - 1 && this.board[i][j] == this.board[i + 1][j]) {
                    canMove = true;
                }
            }
        }
        return canMove;
    }
}