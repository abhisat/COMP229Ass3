import bos.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.Optional;

public class Shepherd extends Character {

    public Shepherd(Cell location, Behaviour behaviour) {
        super(location, behaviour);
        try{
            display = Optional.of(ImageIO.read(new File("shepherd.png")));
        } catch (Exception e){
            display = Optional.empty();
        }
        this.movement = 1;
    }

}