import java.awt.*;


public class Display extends Vector {
    private float fps = 0;
    private int frames = 0;
    private long time = System.currentTimeMillis();
    private long lastTime = 0;
    private long totalTime = 0;
    private int i;

    public Display() {
        super();
    }

    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
