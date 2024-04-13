import Point.*;
import java.awt.*;
import java.awt.event.*;

class Vector extends Point {
    private float xStart;
    private float yStart;

    // Le constructeur du vecteur
    public Vector(float xCoor, float yCoor) {
        super(xCoor, yCoor);
        this.setNorme();
        xStart = 0;
        yStart = 0;
    }

    public Vector(Point p) {
        super(p.getX(), p.getY());
        this.setNorme();
        xStart = 0;
        yStart = 0;
    }

    // Pour modifier la valeur du x d'un point
    public void setX(float newX) {
        this.xStart = newX;
        this.setNorme();
    }

    // Pour modifier la valeur du y d'un point
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
        return (float) (this.getX() * u.getX() + this.getY() * v.getY());
    }

    // champs vectoriels
    public Vector gravityField(float x, float y) {
        return (new Vector(0, (-9.8f / ((x + 100) * (x + 100)))));
    }

    // affichage des vecteurs
    public void show(Graphics g) {
        // on change l'origine
        Vector transformated = new Vector(0, 0);
        Vector transformated2 = new Vector(0, 0);
        Object obj = new Object();
        transformated = obj.conversion(getX(), getY());
        transformated2 = obj.conversion(0, 0);
        g.drawLine(transformated2.getX(), transformated2.getY(), transformated.getX(), transformated.getY());
    }
}