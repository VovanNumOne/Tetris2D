package Figures;

import javax.swing.*;
import java.awt.*;

public class SForm extends Figure{
    private boolean state = true;
    public SForm(JFrame frame){
        super(frame);
        this.color = Color.CYAN;
        figure.add(new Cells(0,1,color));
        figure.add(new Cells(1,1,color));
        figure.add(new Cells(1,0,color));
        figure.add(new Cells(2,0,color));
    }

    @Override
    public void rotateLeftSide() {
        this.rotateFigure();
    }

    @Override
    public void rotateRightSide() {
        if(state == false){
            this.moveLeft();
        }
        this.rotateFigure();
    }

    @Override
    public void rotateFigure() {
        this.hideFigure();

        if(state){
            figure.get(0).setX(figure.get(0).getX());
            figure.get(0).setY(figure.get(0).getY()-Config.SIZE);

            figure.get(1).setX(figure.get(1).getX()-Config.SIZE);
            figure.get(1).setY(figure.get(1).getY());

            figure.get(2).setX(figure.get(2).getX());
            figure.get(2).setY(figure.get(2).getY()+Config.SIZE);

            figure.get(3).setX(figure.get(3).getX()-Config.SIZE);
            figure.get(3).setY(figure.get(3).getY()+2*Config.SIZE);
        }else{

            figure.get(0).setX(figure.get(0).getX());
            figure.get(0).setY(figure.get(0).getY() + Config.SIZE);

            figure.get(1).setX(figure.get(1).getX() + Config.SIZE);
            figure.get(1).setY(figure.get(1).getY());

            figure.get(2).setX(figure.get(2).getX());
            figure.get(2).setY(figure.get(2).getY() - Config.SIZE);

            figure.get(3).setX(figure.get(3).getX() + Config.SIZE);
            figure.get(3).setY(figure.get(3).getY() - 2 * Config.SIZE);
        }

        state = !state;
        this.showFigure();

    }
}
