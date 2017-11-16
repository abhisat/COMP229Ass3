/**
 * Created by abhisheksatpathy on 17/11/17.
 */

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

public class CharacterDecorator extends Character{
    Character wrapped;

    public CharacterDecorator(Character wrapped){
        super(wrapped.getLocation(), wrapped.getBehaviour());
        this.wrapped = wrapped;
    }

    public void paint(Graphics g){
        wrapped.paint(g);
    }
    public void setLocationOf(Cell loc){
        wrapped.setLocationOf(loc);
    }
    public Cell getLocationOf(){
        return wrapped.getLocationOf();
    }
    public void setBehaviour(Behaviour behaviour){
        wrapped.setBehaviour(behaviour);
    }
    public Behaviour getBehaviour(){
        return wrapped.getBehaviour();
    }
    public RelativeMove aiMove(Stage stage){
        return wrapped.aiMove(stage);
    }
    public Cell getLocation(){
        return wrapped.getLocation();
    }
    public int getMovement(){
        return wrapped.getMovement();
    }
    public int getMovesLeft(){
        return wrapped.getMovesLeft();
    }
    public void setMovesLeft(int movesLeft){
        wrapped.setMovesLeft(movesLeft);
    }
    public Character unwrap(){
        return wrapped;
    }
}
