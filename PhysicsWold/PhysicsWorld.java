import java.awt.*;

class Point {
    private float x;
    private float y;
    private float norme;

    public Point(float xCoor, float yCoor) {
        this.x = xCoor;
        this.y = yCoor;
    }

    // Pour obtenir le x d'un point
    public float getX() {
        return this.x;
    }

    // Pour obtenir le y d'un point
    public float getY() {
        return this.y;
    }

    // Pour obtenir la norme d'un point
    public float getNorme() {
        return this.norme;
    }

    // Pour modifier la valeur du x d'un point
    public void setX(float newX) {
        this.x = newX;
        this.setNorme();
    }

    // Pour modifier la valeur du y d'un point
    public void setY(float newY) {
        this.y = newY;
        this.setNorme();
    }

    // pour recalculer la norme d'un vecteur après modification des composantes
    public void setNorme() {
        this.norme = (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }
}

class Vector extends Point {
    private float xStart;
    private float yStart;

    // Le constructeur du vecteur
    public Vector() {
        super(0,0);
        this.setNorme();
        xStart = 0;
        yStart = 0;
    }
    public Vector(float xCoor, float yCoor) {
        super(xCoor, yCoor);
        this.setNorme();
        xStart = 0;
        yStart = 0;
    }
    public Vector(float xVecStart, float yVecStart,float xCoor, float yCoor) {
        super(xCoor, yCoor);
        this.setNorme();
        xStart = xVecStart;
        yStart = yVecStart;
    }

    public Vector(Point p) {
        super(p.getX(), p.getY());
        this.setNorme();
        xStart = 0;
        yStart = 0;
    }
    public Vector(Point s, Point p) {
        super(p.getX(), p.getY());
        this.setNorme();
        xStart = s.getX();
        yStart = s.getY();
    }

    // Pour modifier la valeur du x d'un vecteur
    public void setX(float newX) {
        this.xStart = newX;
        this.setNorme();
    }

    // Pour modifier la valeur du y d'un vecteur
    public void setY(float newY) {
        this.yStart = newY;
        this.setNorme();
    }

    public void add(Vector v) {
        this.setX(this.getX() + v.getX());
        this.setX(this.getY() + v.getY());
        this.setNorme();
    }

    public void substract(Vector v) {
        this.setX(this.getX() - v.getX());
        this.setX(this.getX() - v.getY());
        this.setNorme();
    }

    public void multiply(float k) {
        this.setX(this.getX() * k);
        this.setX(this.getX() * k);
        this.setNorme();
    }

    public void divide(float k) {
        this.setX(this.getX() / k);
        this.setX(this.getX() / k);
        this.setNorme();
    }

    // opération vectorielle
    public float det(Vector u) {
        return (this.getX() * u.getY()) - (this.getY() * u.getX());
    }

    public float scalar(Vector u) {
        return (float) (this.getX() * u.getX() + this.getY() * u.getY());
    }


    // affichage des vecteurs
    public void show(Graphics g) {
        // on change l'origine
        Point transformated = new Point(0, 0);
        Point transformated2 = new Point(0, 0);
        Object obj = new Object();
        transformated = obj.conversion(new Vector(getX(), getY()));
        transformated2 = obj.conversion(new Vector(0, 0));
        g.drawLine((int) transformated2.getX(), (int) transformated2.getY(), (int) transformated.getX(), (int) transformated.getY());
    }
}

class Polygon {
    private Point[] lPts;
    private Vector[] vecteursDefinitionTrigo;

    public Polygon(Point[] listPoints) {
        this.lPts = listPoints;
    }

    public Point[] getLPts() {
        return this.lPts;
    }

    public float surface() {
        float surface = 0;
        for (int i = 0; i < this.lPts.length; i++) {
            int j = (i + 1) % this.lPts.length;
            surface += this.lPts[i].getX() * this.lPts[i].getY() - this.lPts[j].getX() * this.lPts[j].getY();
        }
        return (float) (Math.abs(surface) / 2.0);
    }

    public Point centre(Vector[] vecs) {
        float sumX = 0;
        float sumY = 0;
        for (Vector vec : vecs) {
            sumX += vec.getX();
            sumY += vec.getY();
        }
        return new Point(sumX / vecs.length, sumY / vecs.length);
    }
}

class Object {
    Point position;
    Vector velocity;
    Vector force; // force/mass = acceleration = velocity/time
    Vector acceleration;
    float mass;

    // Les arguments (dans l'ordre) sont: position, velocité, force, masse.
    public Object() {
        this.position = new Point(0,0);
        this.velocity = new Vector(0,0);
        this.force = new Vector(0,0);
        this.mass = 0;
        this.acceleration=new Vector(0,0);
    }
    public Object(Point p, Vector v, Vector f, float m) {
        this.position = p;
        this.velocity = v;
        this.force = f;
        this.mass = m;
    }

    public void setAcceleration(Vector a) {
        this.acceleration = a;
    }

    public Vector calculVecteurAcceleration(Object o) {
        return (new Vector((float) o.force.getX() / o.mass, (float) o.force.getY() / o.mass));
    }

    public Vector conversion(Vector data) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenX = (int) screenSize.getWidth();
        int screenY = (int) screenSize.getHeight();
        return new Vector(data.getX() - screenX / 2, data.getY() - screenY / 2);
    }

    public Point conversion(Point data) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenX = (int) screenSize.getWidth();
        int screenY = (int) screenSize.getHeight();
        return new Vector(data.getX() - screenX / 2, data.getY() - screenY / 2);
    }
}

public class PhysicsWorld {

    // La fonction main est appelée quand le programme se lance
    public static void main(String[] args) {

        Object testObj = new Object(new Point(5, 3), new Vector(3, 0), new Vector(0, 1), 1);

        System.out.println("position.x = " + testObj.position.getX());
        System.out.println("force.y = " + testObj.force.getY());

        testObj.velocity.setX(69);
        System.out.println(testObj.velocity.getX());
        testObj.position.setY(420);
        System.out.println(testObj.position.getY());
    }
}