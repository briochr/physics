package object2d;

public class Point {
    private static double x;
    private static double y;
    private static double lenght;

    public Point(double posX, double posY){
        this.x=posX;
        this.y=posY;
        this.lenght=Math.sqrt(this.x*this.x+this.y*this.y);
    }

    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public double getLenght(){
        return this.lenght;
    }

    public Point add(Point b){
        return new Point(this.getX()+b.getX(), this.getY()+b.getY());
    }
}
