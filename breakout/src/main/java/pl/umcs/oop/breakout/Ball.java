package pl.umcs.oop.breakout;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends GraphicsItem {
    private Point2D moveVector = new Point2D(1, -1).normalize();
    private double velocity = 100;

    public Ball() {
        x = -100;
        y = -100;
        width = height = canvasHeight * .015;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillOval(x, y, width, height);
    }

    public void setPosition(Point2D point) {
        this.x = point.getX() - width/2;
        this.y = point.getY() - height/2;
    }

    public void updatePosition(double diff) {
        x += moveVector.getX() * velocity * diff;
        y += moveVector.getY() * velocity * diff;
    }
}
