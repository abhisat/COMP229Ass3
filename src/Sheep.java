import java.awt.*;
import java.util.Optional;

public class Sheep extends Character {

    public Sheep(Cell location) {
        super(location);
        display = Optional.of(Color.WHITE);
    }

}