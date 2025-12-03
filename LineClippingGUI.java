import java.awt.*;
import javax.swing.*;

public class LineClippingGUI extends JPanel {

    // Clipping window
    int xmin = 100, ymin = 100;
    int xmax = 300, ymax = 250;

    // Line endpoints
    double x1 = 50, y1 = 50;
    double x2 = 350, y2 = 300;

    // Region codes
    final int INSIDE = 0;
    final int LEFT = 1;
    final int RIGHT = 2;
    final int BOTTOM = 4;
    final int TOP = 8;

    // Compute region code
    int computeCode(double x, double y) {
        int code = INSIDE;

        if (x < xmin)
            code |= LEFT;
        else if (x > xmax)
            code |= RIGHT;

        if (y < ymin)
            code |= BOTTOM;
        else if (y > ymax)
            code |= TOP;

        return code;
    }

    // Cohen-Sutherland clipping
    boolean clipLine() {
        double x1c = x1, y1c = y1;
        double x2c = x2, y2c = y2;

        int code1 = computeCode(x1c, y1c);
        int code2 = computeCode(x2c, y2c);

        boolean accept = false;

        while (true) {
            if ((code1 | code2) == 0) {
                accept = true;
                break;
            } else if ((code1 & code2) != 0) {
                break;
            } else {
                int codeOut;
                double x = 0, y = 0;

                if (code1 != 0)
                    codeOut = code1;
                else
                    codeOut = code2;

                if ((codeOut & TOP) != 0) {
                    x = x1c + (x2c - x1c) * (ymax - y1c) / (y2c - y1c);
                    y = ymax;
                } else if ((codeOut & BOTTOM) != 0) {
                    x = x1c + (x2c - x1c) * (ymin - y1c) / (y2c - y1c);
                    y = ymin;
                } else if ((codeOut & RIGHT) != 0) {
                    y = y1c + (y2c - y1c) * (xmax - x1c) / (x2c - x1c);
                    x = xmax;
                } else if ((codeOut & LEFT) != 0) {
                    y = y1c + (y2c - y1c) * (xmin - x1c) / (x2c - x1c);
                    x = xmin;
                }

                if (codeOut == code1) {
                    x1c = x;
                    y1c = y;
                    code1 = computeCode(x1c, y1c);
                } else {
                    x2c = x;
                    y2c = y;
                    code2 = computeCode(x2c, y2c);
                }
            }
        }

        if (accept) {
            x1 = x1c;
            y1 = y1c;
            x2 = x2c;
            y2 = y2c;
        }

        return accept;
    }

    // Paint everything
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw clipping rectangle
        g.setColor(Color.BLACK);
        g.drawRect(xmin, ymin, xmax - xmin, ymax - ymin);

        // Draw original line
        g.setColor(Color.RED);
        g.drawLine(50, 50, 350, 300);

        // Clip and draw clipped line
        if (clipLine()) {
            g.setColor(Color.GREEN);
            g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        }
    }

    // Main function
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cohen-Sutherland Line Clipping");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.add(new LineClippingGUI());
        frame.setVisible(true);
    }
}