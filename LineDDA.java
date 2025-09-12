import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class LineDDA extends JPanel {
    private final BufferedImage canvas;

    public LineDDA(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = canvas.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();

        drawDDALine(100,100,100,300, Color.BLACK);
    }

    private void putPixel(int x, int y, Color color) {
        if (x >= 0 && x < canvas.getWidth() && y >= 0 && y < canvas.getHeight()) {
            canvas.setRGB(x, y, color.getRGB());
        }
    }

    private void drawDDALine(int x0, int y0, int x1, int y1, Color color) {
        int dx = x1 - x0;
        int dy = y1 - y0;
        float x = x0, y = y0;
        int steps;
        if (Math.abs(dx) > Math.abs(dy))
            steps = Math.abs(dx);
        else
            steps = Math.abs(dy);
        double xincr = dx / (double) steps;
        double yincr = dy / (double) steps;
        for (int i = 0; i < steps; i++) {
            putPixel(Math.round(x), Math.round(y), color);
            x += xincr;
            y += yincr;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(canvas, 0, 0, null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Nitin's Canvas");
        LineDDA panel = new LineDDA(800, 600);
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}