import java.awt.*;
import javax.swing.*;

class ScalingTriangle extends JPanel {
    int arrx[] = { 200, 300, 400 };
    int arry[] = { 300, 150, 300 };

    double sx = 0.5;
    double sy = 0.5;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.red);
        g.fillPolygon(arrx, arry, 3);

        double cx = (arrx[0] + arrx[1] + arrx[2]) / 3.0;
        double cy = (arry[0] + arry[1] + arry[2]) / 3.0;

        int x[] = new int[3];
        int y[] = new int[3];

        for (int i = 0; i < 3; i++) {
            x[i] = (int) ((arrx[i] - cx) * sx + cx);
            y[i] = (int) ((arry[i] - cy) * sy + cy);
        }

        g.setColor(Color.black);
        g.fillPolygon(x, y, 3);
    }
}

public class Scaling {
    public static void main(String arg[]) {
        JFrame frame = new JFrame();
        ScalingTriangle t = new ScalingTriangle();
        frame.add(t);
        frame.setSize(720, 560);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}