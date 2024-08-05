import object2d.*;

public class PhysicsWorld {

    // La fonction main est appelée quand le programme se lance
    public static void main(String[] args) {
        Point a = new Point(2,3);
        Vector u = new Vector(a);
        Point[] lPoints = new Point[]{new Point(-2, 0),new Point(-6, 0),new Point(-6, -4),new Point(-2, -4)};
        Polygon poly = new Polygon(lPoints);


        System.out.println(Polygon.newSquare(2d, 3d).collisionSAT(Polygon.newSquare(1d, 2d)));
    }
}