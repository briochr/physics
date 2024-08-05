package object2d;

public class Point {
    public static double x;
    public static double y;
    public static double lenght;

    public Point(double posX, double posY){
        this.x=posX;
        this.y=posY;
        this.lenght=Math.sqrt(this.x*this.x+this.y*this.y);
    }
}
