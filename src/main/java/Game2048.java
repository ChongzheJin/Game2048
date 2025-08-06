import controller.ConsoleGameController;
import controller.GameController;
import model.GameModel;
import model.GameModelImpl;
import view.ConsoleGameView;
import view.GameView;

import java.util.Scanner;

/**
 * @author Chongzhe Jin
 * @version 1.0, 2025/8/5
 * @since 2025/8/5 00:24
 */
public class Game2048 {
    public static void main(String[] args) {
        GameModel model = new GameModelImpl();
        GameView view = new ConsoleGameView(new Scanner(System.in));
        GameController controller = new ConsoleGameController(model, view);

        controller.start();
    }
}
