import java.awt.*;
import javax.swing.*;

class shearxy extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x[] = { 50, 150, 150 };
        int y[] = { 50, 50, 150 };
        double shx = 0.5;
        double shy = 0.8;

        g.setColor(Color.red);
        g.drawPolygon(x, y, 3);

        int[] newx = new int[3];
        int[] newy = new int[3];
        for (int i = 0; i < 3; i++) {
            newx[i] = (int) Math.round(y[i] * shx) + x[i];
            newy[i] = (int) Math.round(x[i] * shy) + y[i];
        }

        g.setColor(Color.black);
        g.drawPolygon(newx, newy, 3);

    }
}

public class ShearingXY {
    public static void main(String arg[]) {
        JFrame frame = new JFrame();
        shearxy r = new shearxy();
        frame.add(r);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
