import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;

public class Main extends JFrame implements Runnable {

    private class Canvas extends JPanel {

        private static final int MARGIN = 10;
        private static final int WIDTH = 35;
        private static final int HEIGHT = 35;

        private Point[][] positions = new Point[20][20];

        public Canvas() {
            setPreferredSize(new Dimension(1280, 720));

            for (int x = 0; x < positions.length; x++) {
                for (int y = 0; y < positions[x].length; y++){
                    positions[x][y] = new Point(MARGIN + x * WIDTH, MARGIN + y * HEIGHT);
                }
            }
        }

        @Override
        public void paint(Graphics g) {
            for(int x = 0; x < positions.length; x++){
                for(int y = 0; y < positions[x].length; y++){
                    g.drawRect(positions[x][y].x, positions[x][y].y, WIDTH, HEIGHT);
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
