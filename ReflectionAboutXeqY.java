import java.awt.*;
import javax.swing.*;

class rectxy extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int arrx[] = { 50, 150, 150 };
        int arry[] = { 50, 50, 150 };

        g.setColor(Color.red);
        g.fillPolygon(arrx, arry, 3);

        int[] x = new int[3];
        int[] y = new int[3];
        for (int i = 0; i < 3; i++) {
            x[i] = arry[i];
            y[i] = arrx[i];
        }

        g.setColor(Color.pink);
        g.fillPolygon(x, y, 3);

    }
}

public class ReflectionAboutXeqY {
    public static void main(String arg[]) {
        JFrame frame = new JFrame();
        rectxy r = new rectxy();
        frame.add(r);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
