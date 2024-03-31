import java.awt.*;
import java.awt.event.*;
import PhysicsWorld.*;

public class Graphic extends Frame {

    public static void main(String[] args) {
        System.out.println("helo there");
        new Graphic();
    }

    public Graphic() {
        // windowLayout();
        setSize(720, 480);
        setVisible(true);
    }

    // Cette fonction est censée être appelée toute seule
    // Ca a pas l'air de marcher :[
    // A L'AIDE ANTOINE
    public void paint(Graphics g) {
        System.out.println("wow");
        g.setColor(Color.BLACK);

        Vector pos = new Vector(100, 100);
        Vector move = new Vector(700, 400);
        showVector(g, pos, move);
    }

    // Affiche le vecteur sur la fenêtre selon les coordonnées de départ et
    // d'arrivée
    public void showVector(Graphics g, Vector start, Vector end) {
        int startX = (int) start.getX();
        int startY = (int) start.getY();
        int endX = (int) end.getX();
        int endY = (int) end.getY();

        g.drawLine(startX, startY, endX, endY);

        int arrowSize = 10;
        double angle = Math.atan2(endX - startX, endY - startY);
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        xPoints[0] = endX;
        yPoints[0] = endY;
        xPoints[1] = (int) (endX - arrowSize * Math.cos(angle - Math.PI / 6));
        yPoints[1] = (int) (endY - arrowSize * Math.sin(angle - Math.PI / 6));
        xPoints[2] = (int) (endX - arrowSize * Math.cos(angle + Math.PI / 6));
        yPoints[2] = (int) (endY - arrowSize * Math.sin(angle + Math.PI / 6));
        g.fillPolygon(xPoints, yPoints, 3);
    }

    public void showPolygon(Graphics g, Polygon poly) {
        Vector[] vecs = poly.getLPts();
        for (int i : vecs) {
            g.drawLine();
        }
    }

    // S'occupe de l'affichage des fenêtres
    public void windowLayout() {
        Frame f = new Frame();

        Label l = new Label("salutations je suis un label rouge made in france", Label.CENTER);
        l.setForeground(new Color(255, 255, 255));
        l.setBounds(700, 100, 300, 100);

        TextField tf = new TextField();
        tf.setBounds(700, 200, 100, 40);

        Button b = new Button("SUS");
        b.setBounds(900, 200, 100, 40);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tf.setText("AMOGUS");
            }
        });

        f.add(l);
        f.add(b);
        f.add(tf);

        f.setTitle("Physics World");
        f.setSize(1600, 900);
        f.setBackground(new Color(0, 0, 0));
        f.setLayout(null);
        f.setVisible(true);
    }
}
