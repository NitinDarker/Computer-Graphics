import java.awt.*;
import javax.swing.*;

class Circle extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawOval(50, 50, 120, 120); // Circle
        g.drawOval(200, 200, 120, 80); // Oval
    }
}

public class Circle_Container {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Circle");
        Circle c1 = new Circle();
        frame.add(c1);

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes on exit
        frame.setVisible(true);
    }
}