import controller.ConsoleGameController;
import controller.GameController;
import model.GameModel;
import model.GameModelImpl;
import view.ConsoleGameView;
import view.GameView;

import java.util.Scanner;

/**
 * This is the main class of the game.
 *
 * @author Chongzhe Jin
 * @version 1.0, 2025/8/5
 * @since 2025/8/5 00:24
 */
public class Game2048 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java Game2048 <mode>");
            System.err.println("Mode: console, gui");
            System.exit(1);
        }
        String mode = args[0].toLowerCase().trim();

        switch (mode) {
            case "console":
                GameModel model = new GameModelImpl();
                GameView view = new ConsoleGameView(new Scanner(System.in));
                GameController controller = new ConsoleGameController(model, view);

                controller.start();
                break;
            case "gui":
                // TODO: implement GUI
                break;
            default:
                System.err.println("Unknown mode: " + mode);
                System.err.println("Mode: console, gui");
                System.exit(1);
        }
    }
}
