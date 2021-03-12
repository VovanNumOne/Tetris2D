package Figures;

import javax.swing.*;
import java.awt.*;

public class JForm extends Figure {
    private int state = 0;
    public JForm(JFrame frame){
        super(frame);
        setColor();
        figure.add(new Cells(0,2,color));
        figure.add(new Cells(1,2,color));
        figure.add(new Cells(1,1,color));
        figure.add(new Cells(1,0,color));
    }

    @Override
    public void setColor() {
        color = Color.BLUE;
    }

    @Override
    public void rotateLeftSide() {
        this.rotateFigure();
    }

    @Override
    public void rotateRightSide() {
        this.rotateFigure();
    }

    @Override
    public void rotateFigure() {
        this.hideFigure();

        if(state == 0){
            if(this.getRight()+20 >= Config.SIZE*Config.WEIGHT){
                figure.get(0).setX(figure.get(0).getX()-Config.SIZE);
                figure.get(0).setY(figure.get(0).getY()-Config.SIZE);

                figure.get(1).setX(figure.get(1).getX()-2*Config.SIZE);
                figure.get(1).setY(figure.get(1).getY());

                figure.get(2).setX(figure.get(2).getX()-Config.SIZE);
                figure.get(2).setY(figure.get(2).getY()+Config.SIZE);

                figure.get(3).setX(figure.get(3).getX());
                figure.get(3).setY(figure.get(3).getY()+2*Config.SIZE);
            }else {
                figure.get(0).setX(figure.get(0).getX());
                figure.get(0).setY(figure.get(0).getY() - Config.SIZE);

                figure.get(1).setX(figure.get(1).getX() - Config.SIZE);
                figure.get(1).setY(figure.get(1).getY());

                figure.get(2).setX(figure.get(2).getX());
                figure.get(2).setY(figure.get(2).getY() + Config.SIZE);

                figure.get(3).setX(figure.get(3).getX() + Config.SIZE);
                figure.get(3).setY(figure.get(3).getY() + 2 * Config.SIZE);
            }

        }else if(state == 1){
            figure.get(0).setX(figure.get(0).getX()+Config.SIZE);
            figure.get(0).setY(figure.get(0).getY()-Config.SIZE);

            figure.get(1).setX(figure.get(1).getX());
            figure.get(1).setY(figure.get(1).getY()-2*Config.SIZE);

            figure.get(2).setX(figure.get(2).getX()-Config.SIZE);
            figure.get(2).setY(figure.get(2).getY()-Config.SIZE);

            figure.get(3).setX(figure.get(3).getX()-2*Config.SIZE);
            figure.get(3).setY(figure.get(3).getY());

        }else if(state == 2){
            if(this.getRight()+20 >= Config.SIZE*Config.WEIGHT){
                figure.get(0).setX(figure.get(0).getX());
                figure.get(0).setY(figure.get(0).getY()+Config.SIZE);

                figure.get(1).setX(figure.get(1).getX()+Config.SIZE);
                figure.get(1).setY(figure.get(1).getY());

                figure.get(2).setX(figure.get(2).getX());
                figure.get(2).setY(figure.get(2).getY()-Config.SIZE);

                figure.get(3).setX(figure.get(3).getX()-Config.SIZE);
                figure.get(3).setY(figure.get(3).getY()-2*Config.SIZE);
            }else {
                figure.get(0).setX(figure.get(0).getX() + Config.SIZE);
                figure.get(0).setY(figure.get(0).getY() + Config.SIZE);

                figure.get(1).setX(figure.get(1).getX() + 2 * Config.SIZE);
                figure.get(1).setY(figure.get(1).getY());

                figure.get(2).setX(figure.get(2).getX() + Config.SIZE);
                figure.get(2).setY(figure.get(2).getY() - Config.SIZE);

                figure.get(3).setX(figure.get(3).getX());
                figure.get(3).setY(figure.get(3).getY() - 2 * Config.SIZE);
            }

        }else if(state == 3){
            figure.get(0).setX(figure.get(0).getX()-2*Config.SIZE);
            figure.get(0).setY(figure.get(0).getY()+Config.SIZE);

            figure.get(1).setX(figure.get(1).getX()-Config.SIZE);
            figure.get(1).setY(figure.get(1).getY()+2*Config.SIZE);

            figure.get(2).setX(figure.get(2).getX());
            figure.get(2).setY(figure.get(2).getY()+Config.SIZE);

            figure.get(3).setX(figure.get(3).getX()+Config.SIZE);
            figure.get(3).setY(figure.get(3).getY());

            state = -1;
        }

        state = state+1;
        this.showFigure();
    }
}
