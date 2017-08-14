import java.awt.*;

public class Shepherd implements Character {

    private Cell location;

    public Shepherd(Cell location) {
        this.location = location;
    }

    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        g.fillOval(location.x + location.width/4, location.y+ location.height/4, location.width/2, location.height/2);
    }
}