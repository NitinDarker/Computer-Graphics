import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class CircleMidPoint extends JPanel {
    private final BufferedImage canvas;

    public CircleMidPoint(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = canvas.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();

        midPoint(300, 350, 350, Color.BLACK);
    }

    private void putPixel(int x, int y, Color color) {
        if (x >= 0 && x < canvas.getWidth() && y >= 0 && y < canvas.getHeight()) {
            canvas.setRGB(x, y, color.getRGB());
        }
    }

    private void midPoint(int r, int xc, int yc, Color color) {
        int x0 = 0, y0 = r;
        int pk = (5 / 4 - r);
        int x = x0, y = y0;
        System.out.println("Plotting...");
        System.out.println("(" + (x + xc) + ", " + (y + yc) + ")");
        putPixel(x + xc, y + yc, color);
        while (x <= y) {
            if (pk < 0) {
                x = x + 1;
                pk = pk + 2 * x + 1;
            } else {
                x = x + 1;
                y = y - 1;
                pk = pk + 2 * x - 2 * y + 1;
            }
            System.out.println("(" + (x + xc) + ", " + (y + yc) + ")");
            putPixel(x + xc, y + yc, color);
            putPixel(-x + xc, y + yc, color);
            putPixel(x + xc, -y + yc, color);
            putPixel(-x + xc, -y + yc, color);
            putPixel(y + xc, x + yc, color);
            putPixel(-y + xc, x + yc, color);
            putPixel(y + xc, -x + yc, color);
            putPixel(-y + xc, -x + yc, color);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(canvas, 0, 0, null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Nitin's Canvas");
        CircleMidPoint panel = new CircleMidPoint(800, 800);
        frame.add(panel);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}