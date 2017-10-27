import java.awt.*;

public class Rocks extends Cell {

    public Rocks(int x, int y) {
        super(x, y);
        cleaner = true;
    }

    @Override
    public void paint(Graphics g, Boolean highlighted) {
        g.setColor(new Color(182, 182, 182));
        super.paint(g, highlighted);
    }
}