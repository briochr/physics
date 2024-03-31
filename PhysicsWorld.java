// import java.util.Arrays;

class Vector {
    // Les vecteurs sont en 2D, ils n'ont comme composantes que x et y
    private float x;
    private float y;
    private float norme;

    // Le constructeur du vecteur
    public Vector(float xCoor, float yCoor) {
        this.x = xCoor;
        this.y = yCoor;
        this.norme = (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    // Pour obtenir le x d'un vecteur
    public float getX() {
        return this.x;
    }

    // Pour obtenir le y d'un vecteur
    public float getY() {
        return this.y;
    }

    // Pour obtenir la norme d'un vecteur
    public float getNorme() {
        return this.norme;
    }

    // Pour modifier la valeur du x d'un vecteur
    public void setX(float newX) {
        this.x = newX;
        this.norme = (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    // Pour modifier la valeur du y d'un vecteur
    public void setY(float newY) {
        this.y = newY;
        this.norme = (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public void add(Vector v) {
        this.x += v.x;
        this.y += v.y;
        this.norme = (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public void substract(Vector v) {
        this.x -= v.x;
        this.y -= v.y;
        this.norme = (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public void multiply(float k) {
        this.x *= k;
        this.y *= k;
        this.norme *= k;
    }

    public void divide(float k) {
        this.x /= k;
        this.y /= k;
        this.norme /= k;
    }

    // opération vectorielle
    public float det(Vector u) {
        return (this.x * u.y) - (this.y * u.x);
    }

    public float scalar(Vector v) {
        Vector u = new Vector(this.x, this.y);
        u.substract(v);
        return (float) (this.norme * this.norme + v.norme * v.norme - u.norme * u.norme);
    }

    // champs vectoriels
    public Vector gravityField(float x, float y) {
        return (new Vector(9.8f / ((x + 100) * (x + 100)), (9.8f / ((x + 100) * (x + 100)))));
    }
}

class Polygon {
    private Vector[] lPts;

    public Polygon(Vector[] listPoints) {
        this.lPts = listPoints;
    }

    public Vector[] getLPts() {
        return this.lPts;
    }

    public float surface() {
        float surface = 0;
        for (int i = 0; i < this.lPts.length; i++) {
            int j = (i + 1) % this.lPts.length;
            surface += this.lPts[i].getX() * this.lPts[i].getY() - this.lPts[j].getX() * this.lPts[j].getY();
        }
        return Math.abs(surface) / 2.0;
    }
    /*
     * pas opti
     * 
     * float surface = 0;
     * if (this.lPts.length < 3) {
     * return surface;
     * } else
     * for (int i = 1; i < (this.lPts.length + 1); i++) {
     * Vector u = new Vector(this.lPts[i].getX() - this.lPts[0].getX(),
     * this.lPts[i].getY() - this.lPts[0].getY());
     * surface += u.det(new Vector(this.lPts[i + 1].getX() - this.lPts[0].getX(),
     * this.lPts[i].getY() - this.lPts[0].getY()));
     * }
     * return Math.abs(surface / 2f);
     */
}

class Object {
    Vector position;
    Vector velocity;
    Vector force; // force/mass = acceleration = velocity/time
    Vector acceleration;
    float mass;

    // Les arguments (dans l'ordre) sont: position, velocité, force, masse.
    public Object(Vector p, Vector v, Vector f, float m) {
        this.position = p;
        this.velocity = v;
        this.force = f;
        this.mass = m;
    }
}

public class PhysicsWorld {

    // La fonction main est appelée quand le programme se lance
    public static void main(String[] args) {

        Object testObj = new Object(new Vector(5, 3), new Vector(3, 0), new Vector(0, 1), 1);

        System.out.println("position.x = " + testObj.position.getX());
        System.out.println("force.y = " + testObj.force.getY());

        testObj.velocity.setX(69);
        System.out.println(testObj.velocity.getX());
        testObj.position.setY(420);
        System.out.println(testObj.position.getY());
    }
}