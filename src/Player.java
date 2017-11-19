import bos.GameBoard;

import java.awt.*;

public class Player implements Runnable {

    public Cell location;
    private Boolean inMove;
    private Graphics graphics;
    private Color color = Color.ORANGE;
    private char c;
    private GameBoard<Cell> gb;
    private Thread thread;
    private int threadSleepTime = 2000;

    public Player(Cell location){
        this.location = location;
        inMove = false;
    }

    public void paint(Graphics g){
        g.setColor(color);
        g.fillOval(location.x + location.width / 4, location.y + location.height / 4, location.width / 2, location.height / 2);
        graphics = g;
    }

    public void startMove(){
        inMove = true;
    }

    public Boolean inMove(){
        return inMove;
    }

    public void notify(char c, GameBoard<Cell> gb) {
        this.c = c;
        this.gb = gb;
        thread = new Thread(this);
        thread.start();
    }

    private void movePlayer(char c, GameBoard<Cell> gb) {
        try {
            if (inMove) {
                if (c == 'a') {
                    location = gb.leftOf(location).orElse(location);
                    inMove = false;
                } else if (c == 'd') {
                    location = gb.rightOf(location).orElse(location);
                    inMove = false;
                } else if (c == 'w') {
                    location = this.gb.above(location).orElse(location);
                    inMove = false;
                } else if (c == 's') {
                    location = gb.below(location).orElse(location);
                    inMove = false;
                }
                updatePaint(Color.RED);
                thread.sleep(threadSleepTime);
                updatePaint(Color.ORANGE);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        movePlayer(c, gb);
    }

    void updatePaint(Color color) {
        if (graphics != null) {
            this.color = color;
            paint(graphics);
        }
    }
}