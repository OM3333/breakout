package pl.umcs.oop.breakout;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends GraphicsItem {
    private Point2D moveVector = new Point2D(1, -1).normalize();
    private double velocity = 50;

    public Ball() {
        x = -100;
        y = -100;
        width = height = canvasHeight * .015;
    }

    public void bounceHorizontally(){
        moveVector = new Point2D(-moveVector.getX(), moveVector.getY());
    }

    public void bounceVertically(){
        moveVector = new Point2D(moveVector.getX(), -moveVector.getY());

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

    public Point2D newPosition(double diff){
        return new Point2D(x+moveVector.getX() * velocity * diff,y+moveVector.getY() * velocity * diff);
    }

    public void updatePosition(double diff) {
        Point2D newPosition = newPosition(diff);
        x = newPosition.getX();
        y = newPosition.getY();
    }
}
