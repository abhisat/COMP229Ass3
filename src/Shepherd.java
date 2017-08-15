import java.awt.*;
import java.util.Optional;

public class Shepherd extends Character {

    public Shepherd(Cell location) {
        super(location);
        display = Optional.of(Color.GREEN);
    }

}