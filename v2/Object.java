import java.awt.*;
import Vector.*;
import Point.*;

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

    public void setAcceleration(Vector a) {
        this.acceleration = a;
    }

    public Vector calculVecteurAcceleration(Object o) {
        return (new Vector((float) o.force.getX() / o.mass, (float) o.force.getY() / o.mass));
    }

    public Vector conversion(Vector data) {
        Vector ret = new Vector(0, 0);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenX = screenSize.width();
        int screenY = screenSize.height();
        ret.setX(data.getX() - screenX / 2);
        ret.setY(data.getY() - screenY / 2);
        return ret;
    }

    public Point conversion(Point data) {
        Point ret = new Point(0, 0);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenX = screenSize.width();
        int screenY = screenSize.height();
        ret.setX(data.getX() - screenX / 2);
        ret.setY(data.getY() - screenY / 2);
        return ret;
    }
}