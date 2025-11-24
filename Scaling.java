import java.awt.*;
import javax.swing.*;

class ScalingTriangle extends JPanel {

    int arrx[] = { 100, 150, 200 };
    int arry[] = { 150, 100, 150 };

    double sx = 0.5; // if sx > 1 its mean the polygon size inlarge and if sx < 1 polygon size is
                     // decrease
    double sy = 0.5;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.red);
        g.fillPolygon(arrx, arry, 3);

        int x[] = new int[3];
        int y[] = new int[3];

        for (int i = 0; i < 3; i++) {
            x[i] = (int) (arrx[i] * sx);
            y[i] = (int) (arry[i] * sy);
        }

        g.setColor(Color.gray);
        g.fillPolygon(x, y, 3);

    }
}

public class Scaling {
    public static void main(String arg[]) {
        JFrame frame = new JFrame();
        ScalingTriangle t = new ScalingTriangle();
        frame.add(t);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}