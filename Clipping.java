import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

// Polygon point class
class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class Clipping extends JLabel {

    int r = 250;

    public Clipping() {
        Timer timer = new Timer(500, e -> {
            r = (r == 250 ? 0 : 250);
            repaint();
        });
        timer.start();
    }

    // Sutherland-Hodgman polygon clipping
    List<Point> sutherlandHodgmanClip(
            List<Point> poly,
            double xMin, double yMin,
            double xMax, double yMax) {
        poly = clipLeft(poly, xMin);
        poly = clipRight(poly, xMax);
        poly = clipBottom(poly, yMin);
        poly = clipTop(poly, yMax);
        return poly;
    }

    List<Point> clipLeft(List<Point> poly, double xmin) {
        return clip(poly, xmin, 0);
    }

    List<Point> clipRight(List<Point> poly, double xmax) {
        return clip(poly, xmax, 1);
    }

    List<Point> clipBottom(List<Point> poly, double ymin) {
        return clip(poly, ymin, 2);
    }

    List<Point> clipTop(List<Point> poly, double ymax) {
        return clip(poly, ymax, 3);
    }

    List<Point> clip(List<Point> input, double bound, int side) {
        List<Point> output = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {

            Point A = input.get(i);
            Point B = input.get((i + 1) % input.size());

            boolean insideA = isInside(A, bound, side);
            boolean insideB = isInside(B, bound, side);

            if (insideA && insideB) { // both inside
                output.add(B);
            } else if (insideA && !insideB) { // leaving boundary
                output.add(intersection(A, B, bound, side));
            } else if (!insideA && insideB) { // entering boundary
                output.add(intersection(A, B, bound, side));
                output.add(B);
            }
        }

        return output;
    }

    boolean isInside(Point p, double bound, int side) {
        switch (side) {
            case 0:
                return p.x >= bound; // left
            case 1:
                return p.x <= bound; // right
            case 2:
                return p.y >= bound; // bottom
            case 3:
                return p.y <= bound; // top
        }
        return false;
    }

    Point intersection(Point A, Point B, double bound, int side) {
        double dx = B.x - A.x;
        double dy = B.y - A.y;

        if (side == 0 || side == 1) { // vertical clipping
            double x = bound;
            double y = A.y + dy * (bound - A.x) / dx;
            return new Point(x, y);
        } else { // horizontal clipping
            double y = bound;
            double x = A.x + dx * (bound - A.y) / dy;
            return new Point(x, y);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // define original polygon (triangle example)
        List<Point> poly = new ArrayList<>();
        poly.add(new Point(100, 300));
        poly.add(new Point(400, 100));
        poly.add(new Point(600, 350));

        // clipping window
        double xmin = 200, ymin = 150;
        double xmax = 500, ymax = 400;

        // apply clipping
        List<Point> clipped = sutherlandHodgmanClip(poly, xmin, ymin, xmax, ymax);

        // draw clipping window
        g.setColor(Color.BLACK);
        g.drawRect((int) xmin, (int) ymin, (int) (xmax - xmin), (int) (ymax - ymin));

        // draw clipped polygon
        g.setColor(Color.RED);

        for (int i = 0; i < clipped.size(); i++) {
            Point A = clipped.get(i);
            Point B = clipped.get((i + 1) % clipped.size());
            g.drawLine((int) A.x, (int) A.y, (int) B.x, (int) B.y);
        }

        // your blinking circle stays
        g.setColor(Color.BLUE);
        g.drawOval(350, 100, r, r);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Nitin's Canvas");
        frame.add(new Clipping());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 560);
        frame.setVisible(true);
    }
}
