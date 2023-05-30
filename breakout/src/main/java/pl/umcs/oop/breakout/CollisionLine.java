package pl.umcs.oop.breakout;

public class CollisionLine {
    public final double startX, startY, endX, endY;


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

    public boolean collision(CollisionLine otherLine){
        double t = ((this.startX-otherLine.startX)*(otherLine.startY- otherLine.endY)-(this.startY- otherLine.startY)*(otherLine.startX- otherLine.endX))/((this.startX-this.endX)*(otherLine.startY-otherLine.endY)-(this.startY-this.endY)*(this.startY-this.endY)*(otherLine.startX-otherLine.endX));
        double u = ((this.startX-this.endX)*(this.startY-this.endY)-(this.startY- otherLine.startY)*(this.startX-this.endX))/((this.startX-this.endX)*(otherLine.startY-otherLine.endY)-(this.startY-this.endY)*(otherLine.startX-otherLine.endX));
        if(0<=t && t >= 1 && 0 <= u && u >=1){
            return true;
        }
        return false;
    }
}
