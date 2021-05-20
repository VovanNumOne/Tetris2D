package Figures;

import javax.swing.*;
import java.awt.*;

public class LForm extends Figure{
    private int state = 0;
    public LForm(JFrame frame){
        super(frame);
        this.color = Color.red;
        figure.add(new Cells(0,0, color));
        figure.add(new Cells(0,1, color));
        figure.add(new Cells(0,2, color));
        figure.add(new Cells(1,2, color));
    }

    @Override
    public void rotateLeftSide() {
        if(state == 2){
            this.moveRight();
        }
        this.rotateFigure();
    }

    @Override
    public void rotateRightSide() {
        if(state == 0){
            this.moveLeft();
        }
        this.rotateFigure();
    }

    @Override
    public void rotateFigure() {
        this.hideFigure();

        switch (state) {
            case 0 -> {
                figure.get(0).setX(figure.get(0).getX() + 2 * Config.SIZE);
                figure.get(0).setY(figure.get(0).getY());
                figure.get(1).setX(figure.get(1).getX() + Config.SIZE);
                figure.get(1).setY(figure.get(1).getY() - Config.SIZE);
                figure.get(2).setX(figure.get(2).getX());
                figure.get(2).setY(figure.get(2).getY() - 2 * Config.SIZE);
                figure.get(3).setX(figure.get(3).getX() - Config.SIZE);
                figure.get(3).setY(figure.get(3).getY() - Config.SIZE);
            }
            case 1 -> {
                figure.get(0).setX(figure.get(0).getX());
                figure.get(0).setY(figure.get(0).getY() + 2 * Config.SIZE);
                figure.get(1).setX(figure.get(1).getX() + Config.SIZE);
                figure.get(1).setY(figure.get(1).getY() + Config.SIZE);
                figure.get(2).setX(figure.get(2).getX() + 2 * Config.SIZE);
                figure.get(2).setY(figure.get(2).getY());
                figure.get(3).setX(figure.get(3).getX() + Config.SIZE);
                figure.get(3).setY(figure.get(3).getY() - Config.SIZE);
            }
            case 2 -> {
                figure.get(0).setX(figure.get(0).getX() - 2 * Config.SIZE);
                figure.get(0).setY(figure.get(0).getY());
                figure.get(1).setX(figure.get(1).getX() - Config.SIZE);
                figure.get(1).setY(figure.get(1).getY() + Config.SIZE);
                figure.get(2).setX(figure.get(2).getX());
                figure.get(2).setY(figure.get(2).getY() + 2 * Config.SIZE);
                figure.get(3).setX(figure.get(3).getX() + Config.SIZE);
                figure.get(3).setY(figure.get(3).getY() + Config.SIZE);
            }
            case 3 -> {
                figure.get(0).setX(figure.get(0).getX());
                figure.get(0).setY(figure.get(0).getY() - 2 * Config.SIZE);
                figure.get(1).setX(figure.get(1).getX() - Config.SIZE);
                figure.get(1).setY(figure.get(1).getY() - Config.SIZE);
                figure.get(2).setX(figure.get(2).getX() - 2 * Config.SIZE);
                figure.get(2).setY(figure.get(2).getY());
                figure.get(3).setX(figure.get(3).getX() - Config.SIZE);
                figure.get(3).setY(figure.get(3).getY() + Config.SIZE);
                state = -1;
            }
        }

        state = state+1;
        this.showFigure();

    }
}
