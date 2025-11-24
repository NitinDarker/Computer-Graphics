import java.awt.*;

class Ellipse1 extends Canvas {
    public void drawEllipse(Graphics g, int rx, int ry, int xc, int yc) {
        int x0, y0;
        x0 = 0;
        y0 = ry;
        double p0 = ry * ry - rx * rx * ry + rx * rx / 4;
        Ellipse1 e1 = new Ellipse1();

        while (ry * ry * x0 < rx * rx * y0) {
            e1.putpixel(g, x0, y0, xc, yc);
            if (p0 < 0) {
                x0 = x0 + 1;
                y0 = y0;
                p0 = p0 + 2 * ry * ry * x0 + ry * ry;
            } else {
                x0 = x0 + 1;
                y0 = y0 - 1;
                p0 = p0 + 2 * ry * ry * x0 - 2 * rx * rx * y0 + ry * ry;
            }
        }
        double p02;
        while (y0 != 0) {
            e1.putpixel(g, x0, y0, xc, yc);
            p02 = ry * ry * (x0 + 0.5) * (x0 + 0.5) + rx * rx * (y0 - 1) * (y0 - 1) - rx * rx * ry * ry;
            if (p02 < 0) {
                x0 = x0 + 1;
                y0 = y0 - 1;
                p02 = p02 * p02 + 2 * ry * ry * x0 - 2 * rx * rx * y0 + rx * rx - 3 * ry * ry;
            } else {
                x0 = x0;
                y0 = y0 - 1;
                p02 = p02 * p02 - 2 * rx * rx * y0 + rx * rx;
            }
        }
    }

    public void putpixel(Graphics g, int x, int y, int xc, int yc) {
        g.setColor(Color.RED);
        g.drawLine(x + xc, y + yc, x + xc, y + yc);
        g.drawLine(-x + xc, y + yc, -x + xc, y + yc);
        g.drawLine(x + xc, -y + yc, x + xc, -y + yc);
        g.drawLine(-x + xc, -y + yc, -x + xc, -y + yc);

    }

    @Override
    public void paint(Graphics g) {
        Ellipse1 e1 = new Ellipse1();
        e1.drawEllipse(g, 150, 120, 180, 180);
    }
}

public class Ellipse {
    public static void main(String[] arg) {
        Frame f = new Frame("Drawing Frame");
        Ellipse1 e = new Ellipse1();
        e.setSize(700, 700);
        f.add(e);
        f.pack();
        f.setVisible(true);
    }
}