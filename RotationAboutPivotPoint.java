import java.awt.*;
import javax.swing.*;

class pivot extends JPanel {

    int arrx[] = { 100, 50, 100 };
    int arry[] = { 50, 100, 150 };

    int angle = 30; // in degree

    int xp = 10; // pivot point
    int yp = 10;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.gray);
        g.fillPolygon(arrx, arry, 3);

        // shift the pivot to origin

        double rad = Math.toRadians(angle);

        int x[] = new int[3];
        int y[] = new int[3];

        for (int i = 0; i < 3; i++) {
            x[i] = arrx[i] - xp;
            y[i] = arry[i] - yp;
        }

        for (int i = 0; i < 3; i++) {
            x[i] = (int) (arrx[i] * Math.cos(rad) - arry[i] * Math.sin(rad));
            y[i] = (int) (arry[i] * Math.cos(rad) + arrx[i] * Math.sin(rad));
        }

        for (int i = 0; i < 3; i++) {
            x[i] += xp;
            y[i] += yp;
        }

        g.setColor(Color.pink);
        g.fillPolygon(x, y, 3);

    }
}

public class RotationAboutPivotPoint {
    public static void main(String arg[]) {
        JFrame frame = new JFrame();
        pivot p = new pivot();
        frame.add(p);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}