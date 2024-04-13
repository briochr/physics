import java.awt.*;
import java.awt.event.*;
import Vector.*;

public class Main extends Frame {
    private float fps = 0;
    private int frames = 0;
    private long time = System.currentTimeMillis();
    private long lastTime = 0;
    private long totalTime = 0;
    private int i;

    public static void main(String[] args) {
        System.out.println("helo there");
    	setSize(720, 480);
    	setVisible(true);
    }

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

    public void runAnimation(Graphics g){
        Vector v = new Vector(50, 50);

        while (true){
            // calculs des fps
            lastTime = time;
            time = System.currentTimeMillis();
            totalTime = time - lastTime;
            if (totalTime > 1000){
                totalTime -= 1000;
                fps = frames;
                frames = 0;
            }
            ++frames;

            // animation random
            v.multiply(i*1.1f);
            v.show(g);
            sleep(100);
        }
    }

}