import java.awt.*;

public class Grid {

    private Cell[][] cells  = new Cell[20][20];;

    private int x;
    private int y;

    public Grid(int x, int y) {
        this.x = x;
        this.y = y;

        int currentTerrain = new java.util.Random().nextInt(4);
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                // a 1 in 8 chance we will switch to a new type of terrain
                if (new java.util.Random().nextInt(8) < 1){
                    currentTerrain = new java.util.Random().nextInt(4);
                }

                switch (currentTerrain) {
                    case 0:
                        cells[i][j] = new Dirt(x + j * 35, y + i * 35);
                        break;
                    case 1:
                        cells[i][j] = new Grass(x + j * 35, y + i * 35);
                        break;
                    case 2:
                        cells[i][j] = new Trees(x + j * 35, y + i * 35);
                        break;
                    case 3:
                        cells[i][j] = new Rocks(x + j * 35, y + i * 35);
                        break;
                }
            }
        }
    }

    public void paint(Graphics g, Point mousePosition) {
        for(int y = 0; y < 20; ++y) {
            for(int x = 0; x < 20; ++x) {
                Cell thisCell = cells[x][y];
                thisCell.paint(g, thisCell.contains(mousePosition));
            }
        }
    }}
