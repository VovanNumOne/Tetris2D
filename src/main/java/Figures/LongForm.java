package Figures;

import javax.swing.*;
import java.awt.*;

public class LongForm extends Figure{
    private boolean state = true;
    public LongForm(JFrame frame){
        super(frame);
        setColor();
        figure.add(new Cells(0,0,color));
        figure.add(new Cells(1,0,color));
        figure.add(new Cells(2,0,color));
        figure.add(new Cells(3,0,color));
    }

    @Override
    public void setColor() {
        this.color = Color.MAGENTA;
    }

    @Override
    public void rotateLeftSide() {
        this.hideFigure();

        if(state){
            figure.get(0).setX(figure.get(0).getX());
            figure.get(0).setY(figure.get(0).getY()-Config.SIZE);

            figure.get(1).setX(figure.get(1).getX()-Config.SIZE);
            figure.get(1).setY(figure.get(1).getY());

            figure.get(2).setX(figure.get(2).getX()-2*Config.SIZE);
            figure.get(2).setY(figure.get(2).getY()+Config.SIZE);

            figure.get(3).setX(figure.get(3).getX()-3*Config.SIZE);
            figure.get(3).setY(figure.get(3).getY()+2*Config.SIZE);
        }else{
            figure.get(0).setX(figure.get(0).getX());
            figure.get(0).setY(figure.get(0).getY()+Config.SIZE);

            figure.get(1).setX(figure.get(1).getX()+Config.SIZE);
            figure.get(1).setY(figure.get(1).getY());

            figure.get(2).setX(figure.get(2).getX()+2*Config.SIZE);
            figure.get(2).setY(figure.get(2).getY()-Config.SIZE);

            figure.get(3).setX(figure.get(3).getX()+3*Config.SIZE);
            figure.get(3).setY(figure.get(3).getY()-2*Config.SIZE);
        }

        state = !state;
        this.showFigure();
    }

    @Override
    public void rotateRightSide() {
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
        }else{

            figure.get(0).setX(figure.get(0).getX() - 3 * Config.SIZE);
            figure.get(0).setY(figure.get(0).getY() + Config.SIZE);

            figure.get(1).setX(figure.get(1).getX() - 2 * Config.SIZE);
            figure.get(1).setY(figure.get(1).getY());

            figure.get(2).setX(figure.get(2).getX() - Config.SIZE);
            figure.get(2).setY(figure.get(2).getY() - Config.SIZE);

            figure.get(3).setX(figure.get(3).getX());
            figure.get(3).setY(figure.get(3).getY() - 2 * Config.SIZE);
        }

        state = !state;
        this.showFigure();
    }

    @Override
    public void rotateFigure() {
        if(state&&this.getBottom()+2*Config.SIZE >= 500)
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
        }else{
            if(this.getRight()+Config.SIZE+20 >= Config.SIZE*Config.WEIGHT){
                figure.get(0).setX(figure.get(0).getX()-2*Config.SIZE);
                figure.get(0).setY(figure.get(0).getY()+Config.SIZE);

                figure.get(1).setX(figure.get(1).getX()-Config.SIZE);
                figure.get(1).setY(figure.get(1).getY());

                figure.get(2).setX(figure.get(2).getX());
                figure.get(2).setY(figure.get(2).getY()-Config.SIZE);

                figure.get(3).setX(figure.get(3).getX()+Config.SIZE);
                figure.get(3).setY(figure.get(3).getY()-2*Config.SIZE);

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
        }

        state = !state;
        this.showFigure();
    }
}
