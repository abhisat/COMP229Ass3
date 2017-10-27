import java.awt.*;

public class Dirt extends Cell {

    public Dirt(int x, int y) {
        super(x, y);
        muddy = true;
    }

    @Override
    public void paint(Graphics g, Boolean highlighted) {
        g.setColor(new Color(102, 51, 0));
        super.paint(g, highlighted);
    }
}