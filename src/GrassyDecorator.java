import bos.Pair;

import java.awt.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class GrassyDecorator extends Pair<Function<Integer, Integer>, BiConsumer<Graphics, Character>> {

    int xoffset;
    int yoffset;

    public GrassyDecorator() {
        super(null, null);
        xoffset = new java.util.Random().nextInt(20);
        yoffset = new java.util.Random().nextInt(20);
        this.first = i -> i -1;
        this.second = (g,c) -> {
            g.setColor(new Color(125, 244, 66));
            g.fillRect(c.getLocation().x + xoffset, c.getLocation().y + yoffset, 5,5);
        };
    }
}
