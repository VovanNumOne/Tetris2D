package Figures;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public class Cells extends JPanel {

    @Getter
    @Setter
    private int x;

    @Getter
    @Setter
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

}
