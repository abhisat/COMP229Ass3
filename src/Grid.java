import bos.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Grid implements GameBoard<Cell> {

    private Cell[][] cells = new Cell[20][20];

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
        doToEachCell((c) -> c.paint(g, c.contains(mousePosition)));
    }

    public Cell getRandomCell() {
        java.util.Random rand = new java.util.Random();
        return cells[rand.nextInt(20)][rand.nextInt(20)];
    }

    private bos.Pair<Integer, Integer> indexOfCell(Cell c) {
        for (int y = 0; y < 20; ++y) {
            for (int x = 0; x < 20; ++x) {
                if (cells[y][x] == c) {
                    return new bos.Pair(y, x);
                }
            }
        }
        return null;
    }

    public Pair<Integer, Integer> findAmongstCells(Predicate<Cell> predicate) {
        for (int y = 0; y < 20; ++y) {
            for (int x = 0; x < 20; ++x) {
                if (predicate.test(cells[y][x]))
                    return new Pair(y, x);
            }
        }
        return null;
    }

    public Optional<Pair<Integer, Integer>> safeFindAmongstCells(Predicate<Cell> predicate) {
        for (int y = 0; y < 20; ++y) {
            for (int x = 0; x < 20; ++x) {
                if (predicate.test(cells[y][x]))
                    return Optional.of(new Pair(y, x));
            }
        }
        return Optional.empty();

    }

    private void doToEachCell(Consumer<Cell> func) {
        for (int y = 0; y < 20; ++y) {
            for (int x = 0; x < 20; ++x) {
                func.accept(cells[y][x]);
            }
        }
    }

    @Override
    public Optional<Cell> below(Cell relativeTo) {
        return safeFindAmongstCells((c) -> c == relativeTo)
                .filter((pair) -> pair.first < 19)
                .map((pair) -> cells[pair.first + 1][pair.second]);
    }

    @Override
    public Optional<Cell> above(Cell relativeTo) {
        return safeFindAmongstCells((c) -> c == relativeTo)
                .filter((pair) -> pair.first > 0)
                .map((pair) -> cells[pair.first - 1][pair.second]);
    }

    @Override
    public Optional<Cell> rightOf(Cell relativeTo) {
        return safeFindAmongstCells((c) -> c == relativeTo)
                .filter((pair) -> pair.second < 19)
                .map((pair) -> cells[pair.first][pair.second + 1]);
    }

    @Override
    public Optional<Cell> leftOf(Cell relativeTo) {
        return safeFindAmongstCells((c) -> c == relativeTo)
                .filter((pair) -> pair.second > 0)
                .map((pair) -> cells[pair.first][pair.second - 1]);
    }

    @Override
    public List<RelativeMove> movesBetween(Cell from, Cell to, GamePiece<Cell> mover) {
        Pair<Integer, Integer> fromIndex = findAmongstCells((c) -> c == from);
        Pair<Integer, Integer> toIndex = findAmongstCells((c) -> c == to);

        List<RelativeMove> result = new ArrayList<RelativeMove>();

        // horizontal movement
        if (fromIndex.second <= toIndex.second) {
            for (int i = fromIndex.second; i < toIndex.second; i++) {
                result.add(new MoveRight(this, mover));
            }
        } else {
            for (int i = toIndex.second; i < fromIndex.second; i++) {
                result.add(new MoveLeft(this, mover));
            }
        }

        // vertical movement
        if (fromIndex.first <= toIndex.first) {
            for (int i = fromIndex.first; i < toIndex.first; i++) {
                result.add(new MoveDown(this, mover));
            }
        } else {
            for (int i = toIndex.first; i < fromIndex.first; i++) {
                result.add(new MoveUp(this, mover));
            }
        }
        return result;
    }
}
