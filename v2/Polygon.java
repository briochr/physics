import Vector.*;
import Point.*;
import java.awt.*;
import java.awt.event.*;

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

    public Point centre(Vecteur[] vecs) {
        float sumX = 0;
        float sumY = 0;
        for (Vector vec : vecs) {
            sumX += vec.getX();
            sumY += vec.getY();
        }
        return new Point(sumX / vecs.length, sumY / vecs.length);
    }

    public void show(Graphics g) {
        Vector[] vecs = this.getLPts(); // Assuming getLPts() returns an array of Vector representing polygon vertices
        Object obj = new Object(); // Assuming this is an instance of the transformation object

        // Draw lines between consecutive vertices
        for (int i = 0; i < vecs.length - 1; i++) {
            Vector transformed1 = obj.conversion(vecs[i].getX(), vecs[i].getY());
            Vector transformed2 = obj.conversion(vecs[i + 1].getX(), vecs[i + 1].getY());
            g.drawLine((int) transformed1.getX(), (int) transformed1.getY(), (int) transformed2.getX(), (int) transformed2.getY());
        }

        // Draw the last line connecting the last and first vertices to close the polygon
        Vector transformedFirst = obj.conversion(vecs[0].getX(), vecs[0].getY());
        Vector transformedLast = obj.conversion(vecs[vecs.length - 1].getX(), vecs[vecs.length - 1].getY());
        g.drawLine((int) transformedLast.getX(), (int) transformedLast.getY(), (int) transformedFirst.getX(), (int) transformedFirst.getY());
        for (int i = 0; i < vecs.length; i++) {
            Vector current = vecs[i];
            Vector next = vecs[(i + 1) % vecs.length]; // To ensure cyclical iteration
            Vector transformedCurrent = obj.conversion(current.getX(), current.getY());
            Vector transformedNext = obj.conversion(next.getX(), next.getY());
            g.drawLine((int) transformedCurrent.getX(), (int) transformedCurrent.getY(), (int) transformedNext.getX(), (int) transformedNext.getY());
        }
    }
}