package model;

/**
 * @author Chongzhe Jin
 * @version 1.0, 2025/8/3
 * @since 2025/8/3 00:19
 */
public class GameModelImpl implements GameModel {
    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public int[][] getBoard() {
        return new int[0][];
    }

    @Override
    public void reset() {

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
