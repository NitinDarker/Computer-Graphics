import javax.swing.*;
import java.awt.*;

public class ClippingMidPoint extends JPanel {

    // Clipping window
    int xmin = 100, ymin = 100;
    int xmax = 300, ymax = 250;

    // Original line endpoints
    double x1 = 50, y1 = 50;
    double x2 = 350, y2 = 300;

    // Clipped endpoints (found by algorithm)
    Double cx1 = null, cy1 = null;
    Double cx2 = null, cy2 = null;

    // Check if point lies inside clipping region
    boolean inside(double x, double y) {
        return (x >= xmin && x <= xmax && y >= ymin && y <= ymax);
    }

    // Midpoint subdivision clipping
    void clip(double x1, double y1, double x2, double y2) {

        // If both inside → this segment is valid clipped line part
        if (inside(x1, y1) && inside(x2, y2)) {

            // Save first valid segment endpoint
            if (cx1 == null) {
                cx1 = x1;
                cy1 = y1;
            }

            // Update last endpoint
            cx2 = x2;
            cy2 = y2;
            return;
        }

        // If segment too small to process, stop
        if (Math.abs(x1 - x2) < 0.5 && Math.abs(y1 - y2) < 0.5)
            return;

        // Midpoint
        double xm = (x1 + x2) / 2;
        double ym = (y1 + y2) / 2;

        // Recursively divide the two halves
        clip(x1, y1, xm, ym);
        clip(xm, ym, x2, y2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw clipping rectangle
        g.setColor(Color.BLACK);
        g.drawRect(xmin, ymin, xmax - xmin, ymax - ymin);

        // Draw original line
        g.setColor(Color.RED);
        g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);

        // Reset clipped endpoints
        cx1 = cy1 = cx2 = cy2 = null;

        // Run clipping
        clip(x1, y1, x2, y2);

        // Draw clipped line
        if (cx1 != null && cx2 != null) {
            g.setColor(Color.GREEN);
            g.drawLine(cx1.intValue(), cy1.intValue(), cx2.intValue(), cy2.intValue());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Midpoint Line Clipping");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.add(new ClippingMidPoint());
        frame.setVisible(true);
    }
}