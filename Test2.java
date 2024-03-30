import java.awt.*;

public class Test2 extends Frame {

  public Test2() {
    setSize(400, 400);
    setTitle("Arrow Drawing Example");
    setVisible(true);
  }

  public void paint(Graphics g) {
    g.setColor(Color.BLACK);

    int startX = 50;
    int startY = 200;
    int endX = 300;
    int endY = 200;

    // arrow body
    g.drawLine(startX, startY, endX, endY);

    // arrowhead
    int arrowSize = 10;
    double angle = Math.atan2(endY - startY, endX - startX);
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

  public static void main(String[] args) {
    new Test2();
  }
}
