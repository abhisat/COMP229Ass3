import java.awt.*;

public class Player {

    public Cell location;
    private Boolean inMove;

    public Player(Cell location){
        this.location = location;
        inMove = false;
    }

    public void paint(Graphics g){
        g.setColor(Color.ORANGE);
        g.fillOval(location.x + location.width / 4, location.y + location.height / 4, location.width / 2, location.height / 2);
    }

    public void startMove(){
        inMove = true;
    }

    public Boolean inMove(){
        return inMove();
    }
}
