package object2d;

public class Square extends Polygon {
    private static double width;
    private static double height;

    public double getHeight(){
        return this.height;
    }
    public double getWidth(){
        return this.width;
    }

    public Square(double width,double height){
        super(new Point[]{new Point(0,0),new Point(0,height),new Point(width,height),new Point(width,0)});
        this.width=width;
        this.height=height;
    }

    public Square(double width,double height,Point pos){
        super(new Point[]{new Point(0,0),new Point(0,height),new Point(width,height),new Point(width,0)},pos);
        this.width=width;
        this.height=height;
    }

    public Square(double width,double height,Point pos, double rot){
        super(new Point[]{new Point(0,0),new Point(0,height),new Point(width,height),new Point(width,0)},pos,rot);
        this.width=width;
        this.height=height;
    }
}
