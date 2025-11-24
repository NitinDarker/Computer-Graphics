import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Reflection extends JPanel {

    private void putPixel(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.drawLine(x, y, x, y);
    }

    // Reflect a pixel (x, y) about chosen line/axis
    private void reflectAndPut(Graphics g, int x, int y, int xc, int yc, int mode, Color c) {
        // Translate relative to center
        int dx = x - xc;
        int dy = y - yc;

        int rx = 0, ry = 0;

        switch (mode) {
            case 1: // reflect about X-axis
                rx = dx;
                ry = -dy;
                break;
            case 2: // reflect about Y-axis
                rx = -dx;
                ry = dy;
                break;
            case 3: // reflect about line y = x
                rx = dy;
                ry = dx;
                break;
            case 4: // reflect about line y = -x
                rx = -dy;
                ry = -dx;
                break;
        }

        putPixel(g, xc + rx, yc + ry, c);
    }

    private void drawCircle(Graphics g, int r, int xc, int yc, Color c) {
        int x = 0, y = r;
        int pk = 1 - r;

        while (x <= y) {

            putPixel(g, xc + x, yc + y, c);
            putPixel(g, xc - x, yc + y, c);
            putPixel(g, xc + x, yc - y, c);
            putPixel(g, xc - x, yc - y, c);

            putPixel(g, xc + y, yc + x, c);
            putPixel(g, xc - y, yc + x, c);
            putPixel(g, xc + y, yc - x, c);
            putPixel(g, xc - y, yc - x, c);

            x++;
            if (pk < 0) {
                pk += 2 * x + 1;
            } else {
                y--;
                pk += 2 * x - 2 * y + 1;
            }
        }
    }

    private void drawReflectedCircle(Graphics g, int r, int xc, int yc, int mode, Color c) {
        int x = 0, y = r;
        int pk = 1 - r;

        while (x <= y) {

            reflectAndPut(g, xc + x, yc + y, xc, yc, mode, c);
            reflectAndPut(g, xc - x, yc + y, xc, yc, mode, c);
            reflectAndPut(g, xc + x, yc - y, xc, yc, mode, c);
            reflectAndPut(g, xc - x, yc - y, xc, yc, mode, c);

            reflectAndPut(g, xc + y, yc + x, xc, yc, mode, c);
            reflectAndPut(g, xc - y, yc + x, xc, yc, mode, c);
            reflectAndPut(g, xc + y, yc - x, xc, yc, mode, c);
            reflectAndPut(g, xc - y, yc - x, xc, yc, mode, c);

            x++;
            if (pk < 0) {
                pk += 2 * x + 1;
            } else {
                y--;
                pk += 2 * x - 2 * y + 1;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int r = 100;
        int xc = 300, yc = 300;

        // original
        drawCircle(g, r, xc, yc, Color.BLACK);

        // 1. reflection about X-axis
        drawReflectedCircle(g, r, xc, yc, 1, Color.RED);

        // 2. reflection about Y-axis
        drawReflectedCircle(g, r, xc, yc, 2, Color.BLUE);

        // 3. reflection about y = x
        drawReflectedCircle(g, r, xc, yc, 3, Color.GREEN);

        // 4. reflection about y = -x
        drawReflectedCircle(g, r, xc, yc, 4, Color.MAGENTA);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reflection Transform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Translate());
        frame.setSize(940, 560);
        frame.setVisible(true);
    }
}
