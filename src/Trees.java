import java.awt.*;

public class Trees extends Cell {

    public Trees(int x, int y) {
        super(x, y);
        cleaner = true;
    }

    @Override
    public void paint(Graphics g, Boolean highlighted) {
        g.setColor(new Color(0, 102, 0));
        super.paint(g, highlighted);
    }
}