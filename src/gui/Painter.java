package gui;

import logic.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


import static gui.Main.moves;
import static logic.Grid.SIZE;
import static gui.Main.doneMoves;

/**
 * @author Kacper Bąk
 * @version 3
 */
public class Painter {


    public static void paint(Grid grid, GraphicsContext gc) {
        gc.setFill(Grid.COLOR);
        gc.fillRect(0, 0, grid.getWidth(), grid.getHeight());

        // Now the Food
        gc.setFill(Food.COLOR);
        paintPoint(grid.getFood().getPoint(), gc);

        // Now the Snake
        Snake snake = grid.getSnake();
        gc.setFill(Snake.COLOR);
        snake.getPoints().forEach(point -> paintPoint(point, gc));
        if (!snake.isSafe()) {
            gc.setFill(Snake.DEAD);
            paintPoint(snake.getHead(), gc);
        }

        // The score
        gc.setFill(Color.BEIGE);
        gc.fillText("Score: " + 1 * snake.getPoints().size(), 10, 475);
        gc.fillText("Moves left: " + moves, 10, 490);
    }

    private static void paintPoint(Point point, GraphicsContext gc) {
        gc.fillRect(point.getX() * SIZE, point.getY() * SIZE, SIZE, SIZE);
    }

    public static void paintResetMessage(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillText("You lose. Hit RETURN to reset.", 10, 20);
        gc.fillText("You have moved " + doneMoves + " times.", 10, 35);
        gc.fillText("You have " + moves + " moves left.", 10, 50);
    }

    public static void paintWonMessage(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillText("You won! Hit RETURN to reset.",10,20);
        gc.fillText("You have moved " + doneMoves + " times.", 10, 35);
        gc.fillText("You have scored " + Snake.length + " points.", 10, 50);
    }
}