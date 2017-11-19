import java.awt.*;
import java.util.*;
import java.time.*;
import java.util.List;
import java.util.function.BiConsumer;

import bos.GameBoard;
import bos.RelativeMove;

public class Stage {
    protected Grid grid;
    protected Character sheep;
    protected Character shepherd;
    protected Character wolf;
    protected Player player;
    private ArrayList<BiConsumer<java.lang.Character, GameBoard<Cell>>> observers;

    public Stage() {
        grid = new Grid(10, 10);
        shepherd = new Shepherd(grid.cellAt(0,0), new StandStill());
        sheep = new Sheep(grid.cellAt(19, 0), new MoveTowards(shepherd));
        wolf = new Wolf(grid.cellAt(19, 19), new MoveTowards(sheep));

        observers = new ArrayList();
        player = new Player(grid.getRandomCell());
        observers.add(player::notify);


    }

    public void update() {
        checkForGameOver();
        // pick up any environmental effects
        if (!player.inMove()) {
            for (Character c: Arrays.asList(sheep, shepherd, wolf)){
                if (c.getLocation().grassy){
                    c.addGunk(new GrassyDecorator());
                } else if (c.getLocation().muddy){
                    c.addGunk(new MuddyDecorator());
                } else if (c.getLocation().cleaner){
                    c.removeGunk();
                }
            }

            if (sheep.getLocation().x == sheep.getLocation().y) {
                sheep.setBehaviour(new StandStill());
                shepherd.setBehaviour(new MoveTowards(sheep));
            }
            Arrays.asList(sheep, shepherd, wolf).forEach(c -> {
                if (c.getMovesLeft() > 0) {
                    c.aiMove(this).perform();
                    c.setMovesLeft(c.getMovesLeft() - 1);
                }
            });
            boolean stillWaiting = false;
            for (Character c : Arrays.asList(sheep, shepherd, wolf)) {
                if (c.getMovesLeft() > 0) {
                    stillWaiting = true;
                }
            }
            if (!stillWaiting) {
                player.startMove();
                Arrays.asList(sheep, shepherd, wolf).forEach(c -> c.setMovesLeft(c.getMovement()));
            }
        }
    }

    public void checkForGameOver() {
        if (sheep.getLocation() == shepherd.getLocation()) {
            System.out.println("The sheep is safe :)");
            System.exit(0);
        } else if (sheep.getLocation() == wolf.getLocation()) {
            System.out.println("The sheep is dead :(");
            System.exit(1);
        }
    }

    public void paint(Graphics g, Point mouseLocation) {
        grid.paint(g, mouseLocation);
        sheep.paint(g);
        shepherd.paint(g);
        wolf.paint(g);
        player.paint(g);
    }

    public void notifyAll(char c) {
        for (BiConsumer bc : observers) {
            bc.accept(new java.lang.Character(c), grid);
        }
    }
}