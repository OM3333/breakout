package pl.umcs.oop.breakout;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameCanvas extends Canvas {
    private GraphicsContext graphicsContext;
    private Paddle paddle;
    private Ball ball;
    private boolean gameRunning = false;

    private List<CollisionLine> collisionLineList;

    private boolean shouldBallBounceHorizontally(double diff){
        Point2D point2D = ball.newPosition(diff);
        CollisionLine pathCollisionLine = new CollisionLine(new Point2D(ball.x,ball.y),point2D);
        for(CollisionLine collisionLine : collisionLineList){
            if(pathCollisionLine.collision(collisionLine)){
                return true;
            }
        }
        return false;
    }
    private boolean shouldBallBounceVertically(double diff){
        Point2D point2D = ball.newPosition(diff);
        CollisionLine pathCollisionLine = new CollisionLine(new Point2D(ball.x,ball.y),point2D);
        for(CollisionLine collisionLine : collisionLineList){
            if(pathCollisionLine.collision(collisionLine)){
                return true;
            }
        }
        return false;
    }
    private boolean shouldBallBounceFromPaddle(double diff){
        if(Math.abs(paddle.x-ball.x)< paddle.getWidth()/2){
            if(Math.abs(paddle.y-ball.y)< paddle.getHeight()/2){
                return true;
            }
        }
        return false;
    }

    private AnimationTimer animationTimer = new AnimationTimer() {
        private long lastUpdate;
        @Override
        public void handle(long now) {
            double diff = (now - lastUpdate)/1_000_000_000.;
            lastUpdate = now;
            if(shouldBallBounceVertically(diff)){
                ball.bounceVertically();
            }
            else if(shouldBallBounceHorizontally(diff)){
                ball.bounceHorizontally();
            }
            else if(shouldBallBounceFromPaddle(diff)){
                ball.bounceVertically();
            }
            ball.updatePosition(diff);
            draw();
        }

        @Override
        public void start() {
            super.start();
            lastUpdate = System.nanoTime();
            collisionLineList = List.of(
                    new CollisionLine(0,0,getWidth(),0),
                    new CollisionLine(getWidth(),0,getWidth(),getHeight()),
                    new CollisionLine(getWidth(),getHeight(),0,getHeight()),
                    new CollisionLine(0,getHeight(),0,0)
            );
        }
    };

    public GameCanvas() {
        super(640, 700);

        this.setOnMouseMoved(mouseEvent -> {
            paddle.setPosition(mouseEvent.getX());
            if(!gameRunning)
                ball.setPosition(new Point2D(mouseEvent.getX(), paddle.getY() - ball.getWidth() / 2));
//            else
//                ball.updatePosition();
            draw();
        });

        this.setOnMouseClicked(mouseEvent -> {
            gameRunning = true;
            animationTimer.start();
        });
    }

    public void initialize() {
        graphicsContext = this.getGraphicsContext2D();
        GraphicsItem.setCanvasSize(getWidth(), getHeight());
        paddle = new Paddle();
        ball = new Ball();
    }

    public void draw() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, getWidth(), getHeight());

        paddle.draw(graphicsContext);
        ball.draw(graphicsContext);
    }
}
