import java.awt.*;

public class Grass extends Cell {

    public Grass(int x, int y) {
        super(x, y);
    }

    @Override
    public void paint(Graphics g, Boolean highlighted) {
        g.setColor(new Color(102, 153, 0));
        super.paint(g, highlighted);
    }
}