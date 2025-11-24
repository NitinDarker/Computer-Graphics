import java.awt.*;
import javax.swing.*;

class Triangle extends JPanel {

    int arrx[] = { 100, 150, 200 };
    int arry[] = { 200, 100, 200 };
    int tx = 10;
    int ty = 50;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.blue);
        g.fillPolygon(arrx, arry, 3);

        int x[] = new int[3];
        int y[] = new int[3];

        for (int i = 0; i < 3; i++) {
            x[i] = arrx[i] + tx;
            y[i] = arry[i] + ty;
        }

        g.setColor(Color.pink);
        g.fillPolygon(x, y, 3);

    }
}

public class Translation {
    public static void main(String arg[]) {
        JFrame frame = new JFrame();
        Triangle t = new Triangle();
        frame.add(t);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}