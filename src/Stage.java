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
        shepherd = new Shepherd(grid.getRandomCell(), new StandStill());
        sheep = new Sheep(grid.getRandomCell(), new MoveTowards(shepherd));
        wolf = new Wolf(grid.getRandomCell(), new MoveTowards(sheep));

        observers = new ArrayList();

        player = new Player(grid.getRandomCell());
        observers.add(player::notify);

        allCharacters = new ArrayList<Character>();
        allCharacters.add(sheep);
        allCharacters.add(shepherd);
        allCharacters.add(wolf);

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
                allCharacters.forEach((c) -> c.aiMove(this).perform());
                player.startMove();
                timeOfLastMove = Instant.now();
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