import java.awt.*;
import javax.swing.*;

class Triangle extends JPanel {
    int arrx[] = { 200, 300, 400 };
    int arry[] = { 300, 150, 300 };
    int angle = 60; 

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillPolygon(arrx, arry, 3);

        double rad = Math.toRadians(angle);

        double cx = (arrx[0] + arrx[1] + arrx[2]) / 3.0;
        double cy = (arry[0] + arry[1] + arry[2]) / 3.0;

        int x[] = new int[3];
        int y[] = new int[3];

        for (int i = 0; i < 3; i++) {
            double dx = arrx[i] - cx;
            double dy = arry[i] - cy;
            x[i] = (int) Math.round(cx + (dx * Math.cos(rad)) - (dy * Math.sin(rad)));
            y[i] = (int) Math.round(cy + (dy * Math.cos(rad)) + (dx * Math.sin(rad)));
        }
        g.setColor(Color.red);
        g.fillPolygon(x, y, 3);
    }
}

public class Rotation {
    public static void main(String[] arg) {
        JFrame frame = new JFrame();
        Triangle t = new Triangle();
        frame.add(t);
        frame.setSize(720, 560);
        frame.setTitle("Nitin's Rotation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
