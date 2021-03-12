package Figures;

import javax.swing.*;
import java.awt.*;

public class Cells extends JPanel {
    private int x;
    private int y;
    private Color color;

    public Cells(int x, int y, Color color) {
        this.x = Config.X_START_POSITION+(x*Config.SIZE);
        this.y = y*Config.SIZE;
        this.color = color;
        setCellColor(this.color);
    }
    public void setCellColor(Color color){
        this.setBackground(color);
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
