import java.awt.*;
import javax.swing.*;

class triangle extends JPanel {

    int arrx[] = { 100, 150, 200 };
    int arry[] = { 200, 100, 200 };
    int angle = 45; // degree

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.blue);
        g.fillPolygon(arrx, arry, 3);

        double rad = Math.toRadians(angle);

        int x[] = new int[3];
        int y[] = new int[3];

        for (int i = 0; i < 3; i++) {
            x[i] = (int) Math.round((arrx[i] * Math.cos(rad)) - (arry[i] * Math.sin(rad)));
            y[i] = (int) Math.round((arry[i] * Math.cos(rad)) + (arrx[i] * Math.sin(rad)));
        }

        g.setColor(Color.pink);
        g.fillPolygon(x, y, 3);

    }
}

public class Rotation {
    public static void main(String[] arg) {
        JFrame frame = new JFrame();
        triangle t = new triangle();
        frame.add(t);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
