import java.awt.*;
import javax.swing.*;

class rectx extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int arrx[] = { 50, 150, 150, 50 };
        int arry[] = { 50, 50, 150, 150 };

        g.setColor(Color.red);
        g.fillPolygon(arrx, arry, 4);

        int[] y = new int[4];
        for (int i = 0; i < 4; i++) {
            y[i] = 300 - arry[i];
        }

        g.setColor(Color.pink);
        g.fillPolygon(arrx, y, 4);

    }
}

public class ReflectionAboutX {
    public static void main(String arg[]) {
        JFrame frame = new JFrame();
        rectx r = new rectx();
        frame.add(r);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}