import java.awt.*;
import java.util.Optional;

public class Wolf extends Character {

    public Wolf(Cell location) {
        super(location);
        display = Optional.of(Color.RED);
    }

}