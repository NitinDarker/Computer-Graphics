import java.awt.*;
import javax.swing.*;

class line extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawline(g, 50, 50, 300, 400);
    }

    void drawline(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int d = 2 * (dy) - dx;
        int x = x1;
        int y = y1;
    
        g.setColor(Color.pink);
        while (x <= x2 && y <= y2) {
            g.fillRect(x, y, 2, 2);
            if (d < 0) {
                x++;
                d += (2 * dy);
            } else {
                x++;
                y++;
                d += 2 * ((dy - dx));
            }
        }
    }
}

public class Bresenhem {
    public static void main(String arg[]) {
        JFrame frame = new JFrame();
        line l = new line();
        frame.add(l);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}