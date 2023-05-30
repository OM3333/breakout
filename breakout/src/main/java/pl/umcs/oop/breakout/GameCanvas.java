package pl.umcs.oop.breakout;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas extends Canvas {
    private GraphicsContext graphicsContext;
    private Paddle paddle;
    private Ball ball;
    private boolean gameRunning = false;

    private boolean shouldBallBounceHorizontally(double diff){
        Point2D point2D = ball.newPosition(diff);
        double xDiff = point2D.getX()-ball.x;
        double yDiff = point2D.getY()-ball.y;

        if(this.widthProperty().get() < ball.x+ball.width || ball.x-ball.width < 0){
            return true;
        }
        return false;
    }
    private boolean shouldBallBounceVertically(double diff){
        if(this.heightProperty().get() < ball.y+ball.height || ball.y-ball.height < 0){
            return true;
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
