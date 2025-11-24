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
    
    private void drawMidpointCubic(Graphics g, int xc, int yc, int xScale, int yScale) {
        double t = -10;
        while (t <= 10) {
            double x = t;
            double y = (t * t * t) / 12.0;

            int px = xc + (int) (x * xScale);
            int py = yc - (int) (y * yScale);

            putPixel(g, px, py);

            t += 0.01; // smoother step
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
