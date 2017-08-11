import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;

public class Main extends JFrame implements Runnable {

    private class Canvas extends JPanel {

        public Canvas() {
            setPreferredSize(new Dimension(1280, 720));
        }

        @Override
        public void paint(Graphics g) {
            for (int x = 10; x < 700; x = x + 35) {
                for (int y = 10; y < 700; y = y + 35) {
                    g.drawRect(x, y, 35, 35);
                }
            }
        }
    }

    public static void main(String[] args) {
        Main window = new Main();
        window.run();
    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Canvas());
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void run() {
        this.repaint();
    }

}
