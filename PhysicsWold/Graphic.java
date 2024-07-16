import java.awt.*;
import java.awt.event.*;

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

    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Cette fonction est censée être appelée toute seule
    // Ca a pas l'air de marcher :[
    // A L'AIDE ANTOINE
    public void paint(Graphics g) {
        System.out.println("wow");
        g.setColor(Color.BLACK);

        Vector pos = new Vector(100, 100);
        Vector move = new Vector(500, 200);
        showVector(g, pos, move);
        for (int i = 0; i < 100; i++) {
            pos.setX(pos.getX() + (float) i * 5.0f);
            pos.setY(pos.getY() + (float) i * 0.5f);
            showVector(g, pos, move);
            sleep(200);
        }
    }

    // Affiche le vecteur sur la fenêtre selon les coordonnées de départ et
    // d'arrivée
    public void showVector(Graphics g, Vector start, Vector end) {
        int startX = (int) start.getX();
        int startY = (int) start.getY();
        int endX = (int) end.getX();
        int endY = (int) end.getY();

        g.drawLine(startX, startY, endX, endY);
        showArrowHead(g, startX, startY, endX, endY);

        int arrowSize = 10;
        double angle = Math.atan2(endX - startX, endY - startY);
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];

        xPoints[0] = endX - arrowSize / 2 * 3;
        yPoints[0] = endY - arrowSize / 2 * 3;
        xPoints[1] = (int) (endX - arrowSize * Math.cos(angle - Math.PI / 6));
        yPoints[1] = (int) (endY - arrowSize * Math.sin(angle - Math.PI / 6));
        xPoints[2] = (int) (endX - arrowSize * Math.cos(angle + Math.PI / 6));
        yPoints[2] = (int) (endY - arrowSize * Math.sin(angle + Math.PI / 6));
        g.fillPolygon(xPoints, yPoints, 3);
    }

    public void showPolygon(Graphics g, Polygon poly) {
        Point[] vecs = poly.getLPts();
        for (int i = 0; i < vecs.length - 1; i++) {
            g.drawLine((int) vecs[i].getX(), (int) vecs[i].getY(), (int) vecs[i + 1].getX(), (int) vecs[i + 1].getY());
        }
    }

    public void showArrowHead(Graphics g, int startX, int startY, int endX, int endY) {
        int arrowSize = 10;
        double angle = Math.atan2(endX - startX, endY - startY);
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];

        yPoints[0] = endY;

        float leftSide = (float) (angle - Math.PI / 2);
        xPoints[1] = (int) (endX + new Vector((int) Math.cos(leftSide), 0).getX());
        yPoints[1] = (int) (endY + new Vector((int) Math.cos(leftSide), 0).getY());

        float rightSide = (float) (angle + Math.PI / 2);
        xPoints[2] = (int) (endX + new Vector((int) Math.cos(rightSide), 0).getX());
        yPoints[2] = (int) (endY + new Vector((int) Math.cos(rightSide), 0).getY());

        g.fillPolygon(xPoints, yPoints, 3);
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
