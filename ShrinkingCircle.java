import java.awt.*;
import javax.swing.*;

class Circle extends JPanel {
    private int r = 100;
    private final javax.swing.Timer timer;

    public Circle() {
        timer = new javax.swing.Timer(100, e -> {
            r -= 2;
            if (r <= 0) {
                r = 100;
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        // super.paintComponent(g);
        int a = 300;
        g.setColor(Color.RED);
        g.drawOval(a - r, a - r, r * 2, r * 2);

    }

}

public class ShrinkingCircle {
    public static void main(String args[]) {
        JFrame frame = new JFrame("Shrinking Circle");
        Circle c = new Circle();
        frame.add(c);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}