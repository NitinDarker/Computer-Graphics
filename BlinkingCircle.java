import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class BlinkingCircle extends JLabel {
    int r = 250;
    public BlinkingCircle() {
        Timer timer = new Timer(500, e -> {
            if (r == 250) {
                r = 0;
            } else {
                r = 250;
            }
            repaint();
        });
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    
        g.setColor(Color.blue);
        g.drawOval(350, 100, r, r);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Nitin's Canvas");
        frame.add(new BlinkingCircle());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 560);
    }
}
