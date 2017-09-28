import java.awt.*;
import java.util.Map;
import java.util.Optional;
import java.util.List;

import bos.GamePiece;
import bos.RelativeMove;
import java.awt.*;
import java.util.Optional;

public abstract class Character implements GamePiece<Cell> {
    Optional<Image> display;
    Cell location;
    Behaviour behaviour;

    public Character(Cell location, Behaviour behaviour){
        this.location = location;
        this.display = Optional.empty();
        this.behaviour = behaviour;
    }

    public  void paint(Graphics g){
        if(display.isPresent()) {
            g.drawImage(display.get(), location.x+2, location.y+2, 31, 31, null, null);
        }
    }

    public void setLocationOf(Cell loc){
        this.location = loc;
    }

    public Cell getLocationOf(){
        return this.location;
    }

    public void setBehaviour(Behaviour behaviour){
        this.behaviour = behaviour;
    }

    public RelativeMove aiMove(Stage stage){
        return behaviour.chooseMove(stage, this);
    }
}
