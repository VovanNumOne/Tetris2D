package Figures;

import javax.swing.*;
import java.awt.*;

public class TForm extends Figure{
    private int state = 0;
    public TForm(JFrame frame){
        super(frame);
        this.color = Color.GREEN;
        figure.add(new Cells(0,1,color));
        figure.add(new Cells(1,1,color));
        figure.add(new Cells(1,0,color));
        figure.add(new Cells(2,1,color));
    }


    @Override
    public void rotateLeftSide() {
        if(state == 1){
            this.moveRight();
        }
        this.rotateFigure();
    }

    @Override
    public void rotateRightSide() {
        if(state == 3){
            this.moveLeft();
        }
        this.rotateFigure();
    }

    @Override
    public void rotateFigure() {
        this.hideFigure();

        if(state == 0){
            figure.get(0).setX(figure.get(0).getX()+Config.SIZE);
            figure.get(0).setY(figure.get(0).getY()-Config.SIZE);

            figure.get(1).setX(figure.get(1).getX());
            figure.get(1).setY(figure.get(1).getY());

            figure.get(2).setX(figure.get(2).getX()+Config.SIZE);
            figure.get(2).setY(figure.get(2).getY()+Config.SIZE);

            figure.get(3).setX(figure.get(3).getX()-Config.SIZE);
            figure.get(3).setY(figure.get(3).getY()+Config.SIZE);

        }else if(state == 1){
            figure.get(0).setX(figure.get(0).getX()+Config.SIZE);
            figure.get(0).setY(figure.get(0).getY()+Config.SIZE);

            figure.get(1).setX(figure.get(1).getX());
            figure.get(1).setY(figure.get(1).getY());

            figure.get(2).setX(figure.get(2).getX()-Config.SIZE);
            figure.get(2).setY(figure.get(2).getY()+Config.SIZE);

            figure.get(3).setX(figure.get(3).getX()-Config.SIZE);
            figure.get(3).setY(figure.get(3).getY()-Config.SIZE);

        }else if(state == 2){
            figure.get(0).setX(figure.get(0).getX()-Config.SIZE);
            figure.get(0).setY(figure.get(0).getY()+Config.SIZE);

            figure.get(1).setX(figure.get(1).getX());
            figure.get(1).setY(figure.get(1).getY());

            figure.get(2).setX(figure.get(2).getX()-Config.SIZE);
            figure.get(2).setY(figure.get(2).getY()-Config.SIZE);

            figure.get(3).setX(figure.get(3).getX()+Config.SIZE);
            figure.get(3).setY(figure.get(3).getY()-Config.SIZE);

        }else if(state == 3){
            figure.get(0).setX(figure.get(0).getX()-Config.SIZE);
            figure.get(0).setY(figure.get(0).getY()-Config.SIZE);

            figure.get(1).setX(figure.get(1).getX());
            figure.get(1).setY(figure.get(1).getY());

            figure.get(2).setX(figure.get(2).getX()+Config.SIZE);
            figure.get(2).setY(figure.get(2).getY()-Config.SIZE);

            figure.get(3).setX(figure.get(3).getX()+Config.SIZE);
            figure.get(3).setY(figure.get(3).getY()+Config.SIZE);

            state = -1;
        }

        state = state+1;
        this.showFigure();

    }
}
