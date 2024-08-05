package object2d;

public class Vector extends Point{
    public static double startX;
    public static double startY;

    public Vector(double x, double y){
        super(x, y);
        this.startX=0;
        this.startY=0;
    }

    public Vector(Point p){
        super(p.x, p.y);
        this.startX=0;
        this.startY=0;
    }

    public Vector(double startX, double startY,double x, double y){
        super(x, y);
        this.startX=startX;
        this.startY=startY;
    }

    public Vector(Point startPoint,Point endPoint){
        super(0,0);
        this.startX=0;
        this.startY=0;
    }

    public Vector toDirectional(){
        return new Vector(this.startX,this.startY,this.x/this.lenght,this.y/this.lenght);
    }


    public double dotProduct(Vector u){
        return(this.x*u.x+this.y*u.y);
    }
    public double dotProduct(Vector u,Vector v){
        return(v.x*u.x+v.y*u.y);
    }

    public double det(Vector u,Vector v){
        return u.x*v.y-v.x*u.y;
    }

    public double orthogonalProj(Point a,Vector axis){
        return dotProduct(axis.toDirectional(), new Vector(a));
    }
}
