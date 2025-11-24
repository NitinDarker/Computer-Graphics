import java.awt.*;
import javax.swing.*;

class recty extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int arrx[] = { 50, 150, 150 };
        int arry[] = { 50, 50, 150 };

        g.setColor(Color.red);
        g.fillPolygon(arrx, arry, 3);

        int[] x = new int[3];
        for (int i = 0; i < 3; i++) {
            x[i] = 600 - arrx[i];
        }

        g.setColor(Color.pink);
        g.fillPolygon(x, arry, 3);

    }
}

public class ReflectionAboutY {
    public static void main(String arg[]) {
        JFrame frame = new JFrame();
        recty r = new recty();
        frame.add(r);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
