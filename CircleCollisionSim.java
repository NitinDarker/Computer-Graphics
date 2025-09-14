import java.awt.*;
import java.util.*;
import javax.swing.*;

public class CircleCollisionSim extends JPanel {
    private final int width, height;
    private final ArrayList<Circle> circles = new ArrayList<>();
    private final javax.swing.Timer timer;

    static class Circle {
        double x, y, vx, vy;
        int r;
        Color color;

        Circle(double x, double y, double vx, double vy, int r, Color c) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.r = r;
            this.color = c;
        }
    }

    public CircleCollisionSim(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);

        Random rand = new Random();

        // Add some random circles
        for (int i = 0; i < 5; i++) {
            int r = 20 + rand.nextInt(50);
            double x = 100 + rand.nextInt(width - 200);
            double y = 100 + rand.nextInt(height - 200);
            double vx = -3 + rand.nextDouble() * 6;
            double vy = -3 + rand.nextDouble() * 6;
            circles.add(new Circle(x, y, vx, vy, r,
                    new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255))));
        }

        // Animation timer (60 FPS ~ 16ms)
        timer = new javax.swing.Timer(5, e -> update());
        timer.start();
    }

    private void update() {
        // Move circles
        for (Circle c : circles) {
            c.x += c.vx;
            c.y += c.vy;

            // Bounce off walls
            if (c.x - c.r < 0 || c.x + c.r > width)
                c.vx *= -1;
            if (c.y - c.r < 0 || c.y + c.r > height)
                c.vy *= -1;
        }

        // Check collisions between circles
        for (int i = 0; i < circles.size(); i++) {
            for (int j = i + 1; j < circles.size(); j++) {
                Circle c1 = circles.get(i);
                Circle c2 = circles.get(j);

                double dx = c1.x - c2.x;
                double dy = c1.y - c2.y;
                double dist = Math.sqrt(dx * dx + dy * dy);

                if (dist < c1.r + c2.r) {
                    // Normalize collision axis
                    double nx = dx / dist;
                    double ny = dy / dist;

                    // Project velocities along axis
                    double p1 = c1.vx * nx + c1.vy * ny;
                    double p2 = c2.vx * nx + c2.vy * ny;

                    // Swap components (elastic collision)
                    double tmp = p1;
                    p1 = p2;
                    p2 = tmp;

                    // Recompute velocities
                    c1.vx += (p1 - (c1.vx * nx + c1.vy * ny)) * nx;
                    c1.vy += (p1 - (c1.vx * nx + c1.vy * ny)) * ny;
                    c2.vx += (p2 - (c2.vx * nx + c2.vy * ny)) * nx;
                    c2.vy += (p2 - (c2.vx * nx + c2.vy * ny)) * ny;

                    // Push circles apart slightly (avoid sticking)
                    double overlap = (c1.r + c2.r - dist) / 2;
                    c1.x += nx * overlap;
                    c1.y += ny * overlap;
                    c2.x -= nx * overlap;
                    c2.y -= ny * overlap;

                    // Change color
                    Color temp = c1.color;
                    c1.color = c2.color;
                    c2.color = temp;
                }
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Circle c : circles) {
            g.setColor(c.color);
            g.fillOval((int) (c.x - c.r), (int) (c.y - c.r), 2 * c.r, 2 * c.r);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Circle Collision Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CircleCollisionSim sim = new CircleCollisionSim(800, 600);
        frame.add(sim);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
