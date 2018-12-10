package blocks;

import tetris.Window;

import java.awt.*;

public abstract class Block {

    private int x = Window.krijgBreedte() / 2;
    private int y = 50;
    private Color color;


    public void moveLeft() {
        x -= Window.getCellSize();
    }

    public void moveRight() {
        x += Window.getCellSize();
    }

    public void moveDown() {
        y += Window.getCellSize();
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
