package Figures;

import javax.swing.*;
import java.awt.*;

public class Square extends Figure{

    public Square(JFrame frame) {
        super(frame);
        this.color = Color.YELLOW;
        figure.add(new Cells(0,0, color));
        figure.add(new Cells(1,0, color));
        figure.add(new Cells(0,1, color));
        figure.add(new Cells(1,1, color));
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
