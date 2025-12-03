import java.awt.*;
import javax.swing.*;

class Solar3DPanel extends JPanel {

    private double angle = 0;
    private final double orbitRadiusX = 200; // horizontal radius
    private final double orbitRadiusY = 120; // vertical radius

    public Solar3DPanel() {
        Timer timer = new Timer(20, e -> {
            angle += 0.02;
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        // Draw orbital ellipse
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawOval(cx - (int) orbitRadiusX, cy - (int) orbitRadiusY,
                (int) orbitRadiusX * 2, (int) orbitRadiusY * 2);

        // Draw Sun
        int sunRadius = 30;
        g2.setColor(Color.ORANGE);
        g2.fillOval(cx - sunRadius, cy - sunRadius, sunRadius * 2, sunRadius * 2);

        // Compute Earth position along elliptical orbit
        double ex = cx + orbitRadiusX * Math.cos(angle);
        double ey = cy + orbitRadiusY * Math.sin(angle);

        // Fake 3D depth by size + shading change
        double depthFactor = (Math.sin(angle) + 1) / 2; // 0 → 1

        int earthRadius = (int) (10 + depthFactor * 8);

        Color frontBlue = new Color(50, 120, 255);
        Color backBlue = new Color(20, 60, 120);

        g2.setColor(depthFactor > 0.5 ? frontBlue : backBlue);

        g2.fillOval((int) ex - earthRadius, (int) ey - earthRadius,
                earthRadius * 2, earthRadius * 2);
    }
}

public class SolarSystem3d {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Solar3DPanel panel = new Solar3DPanel();
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setTitle("3D Orbit Simulation - Nitin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
