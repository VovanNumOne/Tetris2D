package Figures;

import javax.swing.*;
import java.awt.*;

public class ZForm extends Figure{
    private boolean state = true;
    public ZForm(JFrame frame){
        super(frame);
        this.color = Color.orange;
        figure.add(new Cells(0,0,color));
        figure.add(new Cells(1,0,color));
        figure.add(new Cells(1,1,color));
        figure.add(new Cells(2,1,color));
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
            figure.get(0).setX(figure.get(0).getX()+Config.SIZE);
            figure.get(0).setY(figure.get(0).getY());

            figure.get(1).setX(figure.get(1).getX());
            figure.get(1).setY(figure.get(1).getY()+Config.SIZE);

            figure.get(2).setX(figure.get(2).getX()-Config.SIZE);
            figure.get(2).setY(figure.get(2).getY());

            figure.get(3).setX(figure.get(3).getX()-2*Config.SIZE);
            figure.get(3).setY(figure.get(3).getY()+Config.SIZE);
        }else{
            figure.get(0).setX(figure.get(0).getX() - Config.SIZE);
            figure.get(0).setY(figure.get(0).getY());

            figure.get(1).setX(figure.get(1).getX());
            figure.get(1).setY(figure.get(1).getY() - Config.SIZE);

            figure.get(2).setX(figure.get(2).getX() + Config.SIZE);
            figure.get(2).setY(figure.get(2).getY());

            figure.get(3).setX(figure.get(3).getX() + 2 * Config.SIZE);
            figure.get(3).setY(figure.get(3).getY() - Config.SIZE);

        }

        state = !state;
        this.showFigure();


    }
}
