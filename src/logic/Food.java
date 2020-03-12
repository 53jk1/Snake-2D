package logic;

import javafx.scene.paint.Color;

/**
 * A simple class to represent food that takes up only one square.
 *
 * @author Kacper Bąk
 * @version 3
 */
public class Food {
    public static final Color COLOR = Color.YELLOW;

    private Point point;

    Food(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}