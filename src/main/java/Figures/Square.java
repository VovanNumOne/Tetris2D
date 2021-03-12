package Figures;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Square extends Figure{

    public Square(JFrame frame) {
        super(frame);
        setColor();
        figure.add(new Cells(0,0, color));
        figure.add(new Cells(1,0, color));
        figure.add(new Cells(0,1, color));
        figure.add(new Cells(1,1, color));
    }

    @Override
    public void setColor() {
        this.color = Color.YELLOW;
    }


    @Override
    public void rotateLeftSide() {

    }

    @Override
    public void rotateRightSide() {

    }

    @Override
    public void rotateFigure() {

    }
}
