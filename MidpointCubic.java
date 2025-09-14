import java.awt.*;
import javax.swing.*;

public class MidpointCubic extends JPanel {
    private final int xc, yc;
    private final int xScale, yScale;

    public MidpointCubic(int width, int height, int xScale, int yScale) {
        this.xc = width / 2;
        this.yc = height / 2;
        this.xScale = xScale;
        this.yScale = yScale;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        drawMidpointCubic(g, xc, yc, xScale, yScale);
    }

    private void putPixel(Graphics g, int x, int y) {
        g.fillRect(x, y, 2, 2);
    }

    // Implicit function: F(x,y) = y - (x^3)/12
    private double F(double x, double y) {
        return y - (x * x * x) / 12.0;
    }

    private void drawMidpointCubic(Graphics g, int xc, int yc, int xScale, int yScale) {
        int x = -10;
        int y = (int) Math.round((x * x * x) / 12.0);

        while (x <= 10) {
            int px = xc + x * xScale;
            int py = yc - y * yScale;
            putPixel(g, px, py);

            // Midpoint decisions
            double fMid = Math.abs(F(x + 1, y));
            double fUp = Math.abs(F(x + 1, y + 1));
            double fDown = Math.abs(F(x + 1, y - 1));

            if (fMid <= fUp && fMid <= fDown) {
                x++;
            } else if (fUp < fDown) {
                x++;
                y++;
            } else {
                x++;
                y--;
            }
        }
    }

    public static void main(String[] args) {
        final int width = 800;
        final int height = 600;
        final int xScale = 30; 
        final int yScale = 2; // vertical compression

        JFrame frame = new JFrame("Midpoint Cubic y = x^3 / 12");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new MidpointCubic(width, height, xScale, yScale));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
