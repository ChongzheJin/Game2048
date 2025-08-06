package controller;

import model.Direction;
import model.GameModel;
import model.Status;
import view.GameView;

/**
 * This class is the controller which is using console view.
 *
 * @author Chongzhe Jin
 * @version 1.0, 2025/8/6
 * @since 2025/8/6 17:30
 */
public class ConsoleGameController implements GameController {
    private final GameModel model;
    private final GameView view;

    public ConsoleGameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void start() {
        this.view.showStart();
        String input;
        Status status = Status.CONTINUE;

        while (true) {
            this.view.update(this.model.getBoard(), this.model.getScore());

            while (status == Status.CONTINUE) {
                input = this.view.readInput();
                switch (input) {
                    case "up":
                        status = this.model.move(Direction.UP);
                        this.view.update(this.model.getBoard(), this.model.getScore());
                        break;
                    case "down":
                        status = this.model.move(Direction.DOWN);
                        this.view.update(this.model.getBoard(), this.model.getScore());
                        break;
                    case "left":
                        status = this.model.move(Direction.LEFT);
                        this.view.update(this.model.getBoard(), this.model.getScore());
                        break;
                    case "right":
                        status = this.model.move(Direction.RIGHT);
                        this.view.update(this.model.getBoard(), this.model.getScore());
                        break;
                    case "quit":
                        System.out.println("Thank you for playing!");
                        return;
                    case "help":
                        this.view.showHelp();
                        break;
                    case "reset":
                        status = this.model.reset();
                        this.view.update(this.model.getBoard(), this.model.getScore());
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            }

            this.view.showGameOver(status, this.model.getScore());
            while (status != Status.CONTINUE) {
                input = this.view.readInput();
                switch (input) {
                    case "quit":
                        System.out.println("Thank you for playing!");
                        return;
                    case "reset":
                        status = this.model.reset();
                        break;
                    case "help":
                        this.view.showHelp();
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            }

        }
    }
}
