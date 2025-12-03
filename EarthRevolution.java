import java.awt.*;
import javax.swing.*;

class SolarSystemPanel extends JPanel {

    private double angle = 0; // current angle of Earth
    private final double radius = 150; // orbit radius

    public SolarSystemPanel() {
        // Simple animation timer
        Timer timer = new Timer(20, e -> {
            angle += 0.02; // speed of revolution
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Enable smoother circles
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int cx = getWidth() / 2; // center X (Sun position)
        int cy = getHeight() / 2; // center Y

        // Draw orbit path
        int orbitRadius = (int) radius;
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawOval(cx - orbitRadius, cy - orbitRadius, orbitRadius * 2, orbitRadius * 2);

        // Draw Sun
        int sunRadius = 30;
        g2.setColor(Color.ORANGE);
        g2.fillOval(cx - sunRadius, cy - sunRadius, sunRadius * 2, sunRadius * 2);

        // Compute Earth position
        int earthRadius = 12;
        int ex = (int) Math.round(cx + radius * Math.cos(angle));
        int ey = (int) Math.round(cy + radius * Math.sin(angle));

        // Draw Earth
        g2.setColor(Color.BLUE);
        g2.fillOval(ex - earthRadius, ey - earthRadius, earthRadius * 2, earthRadius * 2);
    }
}

public class EarthRevolution {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        SolarSystemPanel panel = new SolarSystemPanel();
        frame.add(panel);
        frame.setSize(720, 560);
        frame.setTitle("Earth Revolving Around Sun - Nitin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
