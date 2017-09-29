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
    private List<Character> allCharacters;
    private ArrayList<BiConsumer<java.lang.Character, GameBoard<Cell>>> observers;

    private Instant timeOfLastMove = Instant.now();

    public Stage() {
        grid = new Grid(10, 10);
        shepherd = new Shepherd(grid.cellAt(0,0), new StandStill());
        sheep = new Sheep(grid.cellAt(19,0), new MoveTowards(shepherd));
        wolf = new Wolf(grid.cellAt(19,19), new MoveTowards(sheep));

        observers = new ArrayList();

        player = new Player(grid.getRandomCell());
        observers.add(player::notify);



    }

    public void update() {
        if (!player.inMove()) {
            if (sheep.location == shepherd.location) {
                System.out.println("The sheep is safe :)");
                System.exit(0);
            } else if (sheep.location == wolf.location) {
                System.out.println("The sheep is dead :(");
                System.exit(1);
            } else {
                if (sheep.location.x == sheep.location.y) {
                    sheep.setBehaviour(new StandStill());
                    shepherd.setBehaviour(new MoveTowards(sheep));
                }
                Arrays.asList(sheep, shepherd, wolf).forEach(c -> {
                    if (c.movesLeft > 0) {
                        c.aiMove(this).perform();
                        c.movesLeft--;
                    }
                });
                if (sheep.movesLeft == 0 && wolf.movesLeft == 0 && shepherd.movesLeft ==0) {
                    player.startMove();
                    timeOfLastMove = Instant.now();
                    sheep.movesLeft = sheep.movement;
                    wolf.movesLeft = wolf.movement;
                    shepherd.movesLeft = shepherd.movement;
                }
            }
        }
    }

    public void paint(Graphics g, Point mouseLocation) {
        grid.paint(g, mouseLocation);
        sheep.paint(g);
        shepherd.paint(g);
        wolf.paint(g);
        player.paint(g);
    }

    public void notifyAll(char c){
        for(BiConsumer bc : observers) {
            bc.accept(new java.lang.Character(c), grid);
        }
    }
}