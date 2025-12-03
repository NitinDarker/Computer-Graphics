import java.awt.*;
import javax.swing.*;

class SolarPanel extends JPanel {

    private double angleMercury = 0;
    private double angleVenus = 0;
    private double angleEarth = 0;
    private double angleMars = 0;
    private double angleMoon = 0;

    public SolarPanel() {
        Timer timer = new Timer(20, e -> {
            angleMercury += 0.05;
            angleVenus += 0.03;
            angleEarth += 0.02;
            angleMars += 0.015;
            angleMoon += 0.1;
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

        // Draw Sun
        int sunRadius = 30;
        g2.setColor(Color.ORANGE);
        g2.fillOval(cx - sunRadius, cy - sunRadius, sunRadius * 2, sunRadius * 2);

        // Orbit radii
        int rMercury = 60;
        int rVenus = 100;
        int rEarth = 150;
        int rMars = 200;

        // Draw orbits
        g2.setColor(Color.GRAY);
        g2.drawOval(cx - rMercury, cy - rMercury, rMercury * 2, rMercury * 2);
        g2.drawOval(cx - rVenus, cy - rVenus, rVenus * 2, rVenus * 2);
        g2.drawOval(cx - rEarth, cy - rEarth, rEarth * 2, rEarth * 2);
        g2.drawOval(cx - rMars, cy - rMars, rMars * 2, rMars * 2);

        // Planet coordinates
        int mercuryX = (int) (cx + rMercury * Math.cos(angleMercury));
        int mercuryY = (int) (cy + rMercury * Math.sin(angleMercury));

        int venusX = (int) (cx + rVenus * Math.cos(angleVenus));
        int venusY = (int) (cy + rVenus * Math.sin(angleVenus));

        int earthX = (int) (cx + rEarth * Math.cos(angleEarth));
        int earthY = (int) (cy + rEarth * Math.sin(angleEarth));

        int marsX = (int) (cx + rMars * Math.cos(angleMars));
        int marsY = (int) (cy + rMars * Math.sin(angleMars));

        // Draw planets
        g2.setColor(new Color(180, 180, 180)); // Mercury gray
        g2.fillOval(mercuryX - 6, mercuryY - 6, 12, 12);

        g2.setColor(new Color(200, 150, 50)); // Venus yellow-ish
        g2.fillOval(venusX - 10, venusY - 10, 20, 20);

        g2.setColor(Color.BLUE); // Earth
        g2.fillOval(earthX - 12, earthY - 12, 24, 24);

        g2.setColor(new Color(180, 60, 20)); // Mars red
        g2.fillOval(marsX - 9, marsY - 9, 18, 18);

        // Moon orbiting Earth
        int moonOrbit = 30;
        int moonX = (int) (earthX + moonOrbit * Math.cos(angleMoon));
        int moonY = (int) (earthY + moonOrbit * Math.sin(angleMoon));

        // Draw moon
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillOval(moonX - 4, moonY - 4, 8, 8);

        // Moon orbit path
        g2.setColor(new Color(120, 120, 120));
        g2.drawOval(earthX - moonOrbit, earthY - moonOrbit,
                moonOrbit * 2, moonOrbit * 2);
    }
}

public class FullSolarSystem {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        SolarPanel s = new SolarPanel();
        frame.add(s);
        
        frame.setSize(900, 700);
        frame.setTitle("Solar System with Moon - Nitin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
