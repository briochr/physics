import java.awt.*;
import java.awt.event.*;

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

    public void show(Graphics g) {
        // on change l'origine
        Point transformated = new Point(0, 0);
        Object obj = new Object();
        transformated = obj.conversion(getX(), getY());
        g.drawLine(transformated2.getX(), transformated2.getY(), transformated.getX(), transformated.getY());
    }
}