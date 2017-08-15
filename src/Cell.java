import java.awt.*;

public class Cell {

    int x;
    int y;
    private Color col;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;

        switch (new java.util.Random().nextInt(4)){
            case 0:
                col = new Color(102, 51, 0);    // DIRT
                break;
            case 1:
                col = new Color(102, 153, 0);   // GRASS
                break;
            case 2:
                col = new Color(0, 102, 0);     // TREES
                break;
            case 3:
                col = new Color(182, 182, 182); // ROCKS
                break;
        }   }

    public void paint(Graphics g, Boolean highlighted) {
        g.setColor(col);
        g.fillRect(x, y, 35, 35);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 35, 35);
        if (highlighted) {
            g.drawRect(x+1, y+1, 33, 33);
        }
    }

    public boolean contains(Point target){
        if (target == null)
            return false;
        return target.x > x && target.x < x + 35 && target.y > y && target.y < y +35;
    }
}
