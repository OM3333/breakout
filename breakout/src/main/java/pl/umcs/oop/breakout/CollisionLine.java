package pl.umcs.oop.breakout;

import javafx.geometry.Point2D;

public class CollisionLine {
    public double startX, startY, endX, endY;


    public CollisionLine(double startX, double startY, double endX, double endY) {
        double resultStartX = startX;
        double resultStartY = startY;
        double resultEndX = endX;
        double resultEndY = endY;
        if(startX > endX){
            resultStartX = endX;
            resultStartY = endY;
            resultEndX = startX;
            resultEndY = startY;
        }
        this.startX = resultStartX;
        this.startY = resultStartY;
        this.endX = resultEndX;
        this.endY = resultEndY;
    }

    public CollisionLine(Point2D start, Point2D end){
        if(start.getX()>end.getX()){
            startX = end.getX();
            startY = end.getY();
            endX = start.getX();
            endY = start.getY();
        }
        else{
            startX = start.getX();
            startY = start.getY();
            endX = end.getX();
            endY = end.getY();
        }
    }

    public boolean collision(CollisionLine otherLine){
        double t = ((this.startX-otherLine.startX)*(otherLine.startY- otherLine.endY)-(this.startY- otherLine.startY)*(otherLine.startX- otherLine.endX))/((this.startX-this.endX)*(otherLine.startY-otherLine.endY)-(this.startY-this.endY)*(this.startY-this.endY)*(otherLine.startX-otherLine.endX));
        double u = ((this.startX-this.endX)*(this.startY-this.endY)-(this.startY- otherLine.startY)*(this.startX-this.endX))/((this.startX-this.endX)*(otherLine.startY-otherLine.endY)-(this.startY-this.endY)*(otherLine.startX-otherLine.endX));
        if(0<=t && t >= 1 && 0 <= u && u >=1){
            return true;
        }
        return false;
    }
}
