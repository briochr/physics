package object2d;

public class Vector extends Point{
    private static double startX;
    private static double startY;

    public double getStartX(){
        return this.startX;
    }
    public double getStartY(){
        return this.startY;
    }



    public Vector(double x, double y){
        super(x, y);
        this.startX=0;
        this.startY=0;
    }

    public Vector(Point p){
        super(p.getX(), p.getY());
        this.startX=0;
        this.startY=0;
    }

    public Vector(double startX, double startY,double x, double y){
        super(x-startX, y-startX);
        this.startX=startX;
        this.startY=startY;
    }

    public Vector(Point startPoint,Point endPoint){
        super(endPoint.getX()-startPoint.getX(),endPoint.getY()-startPoint.getY());
        this.startX=startPoint.getX();
        this.startY=startPoint.getY();
    }

    public Vector toDirectional(){
        return new Vector(this.startX,this.startY,this.getX()/this.getLenght(),this.getY()/this.getLenght());
    }

    public double dotProduct(Vector u){
        return(this.getX()*u.getX()+this.getX()*u.getX());
    }
    public double dotProduct(Vector u,Vector v){
        return(v.getX()*u.getX()+v.getY()*u.getY());
    }

    public double det(Vector u,Vector v){
        return u.getX()*v.getY()-v.getX()*u.getY();
    }

    public double orthogonalProj(Point a,Vector axis){
        return (dotProduct(axis.toDirectional(), new Vector(a)));
    }

    public double axialProj(Point a,Vector axis){
        return (dotProduct(axis.toDirectional(),new Vector(a.getX()-axis.startX,a.getY()-axis.startY)));
    }
}
