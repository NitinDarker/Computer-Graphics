import java.awt.*;
import javax.swing.*;

class Circle extends JPanel {

    javax.swing.Timer timer;
    int r = 100;
    int a = 0;

    public Circle() {
        timer = new javax.swing.Timer(100, e -> {
            a += 2;
            if (a == 250) {
                a = 0;
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int centerx = getWidth() / 2 - 250;
        int centery = getHeight() / 2;

        g.setColor(Color.RED);
        g.drawOval(centerx - r + a, centery - r, r * 2, r * 2);
        g.setColor(Color.YELLOW);
        g.fillOval(centerx - r + 500 - a, centery - r, r * 2, r * 2);

    }
}

public class CircleCollisionSim {

    public static void main(String args[]) {
        JFrame frame = new JFrame("Collision of Circle");
        Circle c1 = new Circle();
        frame.add(c1);
        frame.setSize(1500, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}