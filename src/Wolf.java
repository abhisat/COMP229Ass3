import bos.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.Optional;
import java.util.List;
import bos.Pair;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.Optional;

public class Wolf extends Character {

    public Wolf(Cell location, Behaviour behaviour) {
        super(location, behaviour);
        try{
            display = Optional.of(ImageIO.read(new File("wolf.png")));
        } catch (Exception e){
            this.display = Optional.empty();
        }
        movement = 4;
        movesLeft = 4;
    }

}