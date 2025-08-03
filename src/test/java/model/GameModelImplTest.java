package model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;


/**
 * @author Chongzhe Jin
 * @version 1.0, 2025/8/3
 * @since 2025/8/3 17:33
 */
public class GameModelImplTest {
    private GameModel gameModel;

    @Before
    public void setUp() {
        gameModel = new GameModelImpl();
    }

    @Test
    public void testMoveNoChange() {
        // left
        gameModel = new GameModelImpl(0, new int[][]{{2, 0, 0, 0}, {2, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                new Random(1));
        assertFalse(gameModel.move(Direction.LEFT));
        assertEquals(Arrays.deepToString(gameModel.getBoard()),
                Arrays.deepToString(new int[][]{{2, 0, 0, 0}, {2, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}));

        // right
        gameModel = new GameModelImpl(0, new int[][]{{0, 0, 0, 2}, {0, 0, 0, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                new Random(1));
        assertFalse(gameModel.move(Direction.RIGHT));
        assertEquals(Arrays.deepToString(gameModel.getBoard()),
                Arrays.deepToString(new int[][]{{0, 0, 0, 2}, {0, 0, 0, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}}));

        // up
        gameModel = new GameModelImpl(0, new int[][]{{0, 0, 2, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                new Random(1));
        assertFalse(gameModel.move(Direction.UP));
        assertEquals(Arrays.deepToString(gameModel.getBoard()),
                Arrays.deepToString(new int[][]{{0, 0, 2, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}));

        // down
        gameModel = new GameModelImpl(0, new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, 2}},
                new Random(1));
        assertFalse(gameModel.move(Direction.DOWN));
        assertEquals(Arrays.deepToString(gameModel.getBoard()),
                Arrays.deepToString(new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, 2}}));
    }

    @Test
    public void testMoveLeft2Tiles() {
        gameModel = new GameModelImpl(0, new int[][]{{0, 0, 0, 2}, {0, 0, 0, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                new Random(1));
        assertTrue(gameModel.move(Direction.LEFT));
        assertEquals(Arrays.deepToString(gameModel.getBoard()),
                Arrays.deepToString(new int[][]{{2, 0, 0, 0}, {2, 0, 0, 0}, {0, 0, 0, 0}, {0, 4, 0, 0}}));
        assertEquals(0, gameModel.getScore());
    }

    @Test
    public void testMoveLeftMergeOnce() {
        gameModel = new GameModelImpl(0, new int[][]{{0, 0, 2, 2}, {0, 0, 0, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                new Random(1));
        assertTrue(gameModel.move(Direction.LEFT));
        assertEquals(Arrays.deepToString(gameModel.getBoard()),
                Arrays.deepToString(new int[][]{{4, 0, 0, 0}, {2, 0, 0, 0}, {0, 0, 0, 0}, {0, 4, 0, 0}}));
        assertEquals(4, gameModel.getScore());

        gameModel = new GameModelImpl(0, new int[][]{{0, 2, 2, 2}, {0, 0, 0, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                new Random(1));
        assertTrue(gameModel.move(Direction.LEFT));
        assertEquals(Arrays.deepToString(gameModel.getBoard()),
                Arrays.deepToString(new int[][]{{4, 2, 0, 0}, {2, 0, 0, 4}, {0, 0, 0, 0}, {0, 0, 0, 0}}));
        assertEquals(4, gameModel.getScore());
    }

    @Test
    public void testMoveLeftMergeTwice() {
        gameModel = new GameModelImpl(0, new int[][]{{2, 2, 2, 2}, {0, 0, 0, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                new Random(1));
        assertTrue(gameModel.move(Direction.LEFT));
        assertEquals(Arrays.deepToString(gameModel.getBoard()),
                Arrays.deepToString(new int[][]{{4, 4, 0, 0}, {2, 0, 0, 4}, {0, 0, 0, 0}, {0, 0, 0, 0}}));
        assertEquals(8, gameModel.getScore());
    }

    @Test
    public void testMoveRight2Tiles() {
        gameModel = new GameModelImpl(0, new int[][]{{0, 2, 0, 0}, {0, 2, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                new Random(1));
        assertTrue(gameModel.move(Direction.RIGHT));
        assertEquals(Arrays.deepToString(gameModel.getBoard()),
                Arrays.deepToString(new int[][]{{0, 0, 0, 2}, {0, 0, 0, 2}, {0, 0, 0, 0}, {0, 4, 0, 0}}));
        assertEquals(0, gameModel.getScore());
    }

    @Test
    public void testMoveRightMergeOnce() {
        gameModel = new GameModelImpl(0, new int[][]{{0, 2, 2, 0}, {0, 2, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                new Random(1));
        assertTrue(gameModel.move(Direction.RIGHT));
        assertEquals(Arrays.deepToString(gameModel.getBoard()),
                Arrays.deepToString(new int[][]{{0, 0, 0, 4}, {0, 0, 0, 2}, {0, 0, 0, 0}, {0, 4, 0, 0}}));
        assertEquals(4, gameModel.getScore());

        gameModel = new GameModelImpl(0, new int[][]{{0, 2, 2, 2}, {0, 2, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                new Random(1));
        assertTrue(gameModel.move(Direction.RIGHT));
        assertEquals(Arrays.deepToString(gameModel.getBoard()),
                Arrays.deepToString(new int[][]{{0, 0, 2, 4}, {0, 0, 4, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}}));
        assertEquals(4, gameModel.getScore());
    }
}