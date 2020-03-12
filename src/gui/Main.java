package gui;

import logic.GameLoop;
import logic.Grid;
import logic.Snake;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * This is the place where the threads are dispatched.
 *
 * @author Kacper Bąk
 * @version 3
 */
public class Main extends Application {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    public static int doneMoves = 0;
    public static int moves;

    private GameLoop loop;
    private Grid grid;
    private GraphicsContext context;


    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.createScene(primaryStage);
    }

    public void createScene(Stage primaryStage) {
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        context = canvas.getGraphicsContext2D();

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e -> {
            Snake snake = grid.getSnake();
            if (loop.isKeyPressed() && !loop.isPaused()) {
                return;
            }
            loop.setKeyPressed();
            switch (e.getCode()) {
                case UP:
                    snake.setUp();
                    break;
                case DOWN:
                    snake.setDown();
                    break;
                case LEFT:
                    snake.setLeft();
                    break;
                case RIGHT:
                    snake.setRight();
                    break;
                case ENTER:
                    if (loop.isPaused())
                        createScene(primaryStage);
            }
        });

        reset();

        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake 2D");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

        (new Thread(loop)).start();

    }
    public void reset(){
        moves = getMoves();
        grid = new Grid(WIDTH, HEIGHT);
        loop = new GameLoop(grid, context);
        Painter.paint(grid, context);
        doneMoves = 0;

    }

    public static int getMoves() {
        int movesss = Integer.parseInt(JOptionPane.showInputDialog("How many moves should the snake make?", Main.moves));
        return movesss;
    }
}