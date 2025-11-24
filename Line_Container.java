import java.awt.*;
import javax.swing.*;

class Line extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        line_drawing(g);
    }

    void line_drawing(Graphics g) {
        g.drawLine(50, 50, 50, 200);
        g.drawLine(50, 50, 200, 50);
        g.drawLine(50, 200, 200, 200);
        g.drawLine(200, 50, 200, 200);

    }
}

public class Line_Container{
    public static void main(String args[])
    {
        JFrame frame = new JFrame("Line drawing");
        Line l1 = new Line();
        frame.add(l1);


        frame.setSize(500,500);
             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true);

    }
}
