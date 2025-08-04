package view;

import model.Status;

import java.util.Scanner;

/**
 * This class is the console-based implement of {@link GameView}.
 * <p>
 *     This class is used to show the game on the console. Takes input from the console and updates the game.
 * </p>
 *
 * @author Chongzhe Jin
 * @version 1.0, 2025/8/5
 * @since 2025/8/5 01:13
 */
public class ConsoleGameView implements GameView {
    private final Scanner scanner;

    public ConsoleGameView(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readInput() {
        System.out.println("Please enter ur move or reset: ");
        if (scanner.hasNext()) {
            return scanner.next().trim();
        }
        return null;
    }

    @Override
    public void showStart() {
        System.out.println("Welcome to 2048!");
        System.out.println("U can move the board by entering \"left\", \"right\", \"up\", and \"down\". " +
                "When two tiles with the same number touch, they merge into one!");
        System.out.println("U can reset the game by entering \"reset\".");
        System.out.println("U can view the help by entering \"help\".");
        System.out.println("U can quit the game by entering \"quit\".");
        System.out.println("U will win the game, if u get a 2048 tile.");
        System.out.println("Let's begin!");
    }

    @Override
    public void showHelp() {
        System.out.println("U can move the board by entering \"left\", \"right\", \"up\", and \"down\". " +
                "When two tiles with the same number touch, they merge into one!");
        System.out.println("U can reset the game by entering \"reset\".");
        System.out.println("U can view the help by entering \"help\".");
        System.out.println("U can quit the game by entering \"quit\".");
        System.out.println("U will win the game, if u get a 2048 tile.");
    }

    @Override
    public void update(int[][] board, int score) {
        System.out.println("Score: " + score);
        for (int[] row : board) {
            for (int i : row) {
                System.out.print(i + " ");
                System.out.print("|");
            }
        }
    }

    @Override
    public void showGameOver(Status status, int score) {
        switch (status) {
            case WIN:
                System.out.println("Your score: " + score);
                System.out.println("Congratulations! You win the game!");
                break;
            case LOSE:
                System.out.println("Your score: " + score);
                System.out.println("Sorry, you lose the game.");
                break;
            case CONTINUE:
                break;
        }
    }
}
