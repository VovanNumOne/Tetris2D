package Figures;

import javax.swing.*;
import java.awt.*;

public class LongForm extends Figure{
    private boolean state = true;
    public LongForm(JFrame frame){
        super(frame);
        this.color = Color.MAGENTA;
        figure.add(new Cells(0,0,color));
        figure.add(new Cells(1,0,color));
        figure.add(new Cells(2,0,color));
        figure.add(new Cells(3,0,color));
    }

    @Override
    public void rotateLeftSide() {
        if(!state){
            this.moveRight();
        }
        rotateFigure();
    }

    @Override
    public void rotateRightSide() {
        if(!state){
            this.moveLeft();
        }
        rotateFigure();
    }

    @Override
    public void rotateFigure() {
        if(state&&this.getBottom()+2*Config.SIZE >= Config.SIZE*Config.HEIGHT)
            return;
        this.hideFigure();

        if(state){
            figure.get(0).setX(figure.get(0).getX()+Config.SIZE);
            figure.get(0).setY(figure.get(0).getY()-Config.SIZE);

            figure.get(1).setX(figure.get(1).getX());
            figure.get(1).setY(figure.get(1).getY());

            figure.get(2).setX(figure.get(2).getX()-Config.SIZE);
            figure.get(2).setY(figure.get(2).getY()+Config.SIZE);

            figure.get(3).setX(figure.get(3).getX()-2*Config.SIZE);
            figure.get(3).setY(figure.get(3).getY()+2*Config.SIZE);
        }else {

            figure.get(0).setX(figure.get(0).getX() - Config.SIZE);
            figure.get(0).setY(figure.get(0).getY() + Config.SIZE);

            figure.get(1).setX(figure.get(1).getX());
            figure.get(1).setY(figure.get(1).getY());

            figure.get(2).setX(figure.get(2).getX() + Config.SIZE);
            figure.get(2).setY(figure.get(2).getY() - Config.SIZE);

            figure.get(3).setX(figure.get(3).getX() + 2 * Config.SIZE);
            figure.get(3).setY(figure.get(3).getY() - 2 * Config.SIZE);
        }

        state = !state;
        this.showFigure();
    }
}
