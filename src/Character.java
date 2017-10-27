import java.awt.*;
import java.util.*;

import bos.GamePiece;
import bos.Pair;
import bos.RelativeMove;
import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Character implements GamePiece<Cell> {
    Optional<Image> display;
    Cell location;
    Behaviour behaviour;
    protected int movement;
    protected int movesLeft;

    List<BiConsumer<Graphics, Character>> paintModifiers;
    List<Function<Integer, Integer>> movementModifiers;

    public Character(Cell location, Behaviour behaviour){
        this.location = location;
        this.display = Optional.empty();
        this.behaviour = behaviour;
        this.movement = 1;
        this.movesLeft = 1;
        this.movementModifiers = new ArrayList();
        this.paintModifiers = new ArrayList();
    }

    public Character() {

    }

    public void paint(Graphics g){
        if(display.isPresent()) {
            g.drawImage(display.get(), getLocation().x+2, getLocation().y+2, 31, 31, null, null);
        }
        paintModifiers.forEach(c -> c.accept(g, this));
    }

    public void setLocationOf(Cell loc){this.location = loc;}

    public Cell getLocationOf(){
        return location;
    }

    public void setBehaviour(Behaviour behaviour){
        this.behaviour = behaviour;
    }
    public Behaviour getBehaviour(){return this.behaviour;}

    public RelativeMove aiMove(Stage stage){
        return getBehaviour().chooseMove(stage, this);
    }

    public int getMovementDistance(){
        return getMovement();
    }

    public Cell getLocation() {
        return location;
    }

    public int getMovement() {
        Integer m = movement;
        for(int i = 0; i < movementModifiers.size(); i++){
            m = movementModifiers.get(i).apply(m);
        }
        return m;
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }

    public void addGunk(Pair<Function<Integer, Integer>, BiConsumer<Graphics, Character>> p){
        movementModifiers.add(p.first);
        paintModifiers.add(p.second);
    }
    public void removeGunk(){
        if (movementModifiers.size() > 0 && paintModifiers.size() > 0) {
            movementModifiers.remove(movementModifiers.size() - 1);
            paintModifiers.remove(paintModifiers.size() - 1);
        }
    }
}
