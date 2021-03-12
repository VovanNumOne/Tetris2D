package Figures;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Figure {

    protected List<Cells> figure = new ArrayList<>();
    protected JFrame frame;
    protected Color color;

    public Figure(JFrame frame){
        this.frame = frame;
    }


    public void moveLeft() {
        this.hideFigure();

        for (Cells cell:figure) {
            cell.setX(cell.getX()-Config.SIZE);
        }

        this.showFigure();
    }
    public void moveRight() {
        this.hideFigure();

        for (Cells cell:figure) {
            cell.setX(cell.getX()+Config.SIZE);
        }

        this.showFigure();

    }
    public void moveDown() {
        this.hideFigure();

        for (Cells cell:figure) {
            cell.setY(cell.getY()+Config.SIZE);
        }

        this.showFigure();

    }

    public List<Cells> getFigureCells() {
        return this.figure;
    }

    public void showFigure() {
        for (Cells cell:figure) {
            cell.setBounds(cell.getX(),cell.getY(),Config.SIZE-1,Config.SIZE-1);
            cell.setBackground(this.color);

            frame.add(cell);
        }
    }
    public void hideFigure() {
        for (Cells cell:figure) {
            cell.setBounds(cell.getX(),cell.getY(),Config.SIZE,Config.SIZE);
            cell.setBackground(Color.WHITE);

            frame.add(cell);
        }
    }

    public void displayNextFigureInfo(JPanel panel){
        for (Cells c: figure) {
            c.setX(c.getX()-Config.SIZE-30);
            c.setY(c.getY()+(Config.HEIGHT-4)*Config.SIZE/2);
            c.setBounds(c.getX(),c.getY(),Config.SIZE-1,Config.SIZE-1);
            c.setBackground(this.color);

            panel.add(c);

        }
    }
    public void deleteFigure() {
        for (Cells cell:figure) {
            cell.setOpaque(false);
        }
    }

    public int getBottom(){
        int bottom = figure.get(0).getY()+Config.SIZE;
        for (int i = 0; i<figure.size();i++) {
            if(figure.get(i).getY()+Config.SIZE>=bottom){
                bottom = figure.get(i).getY()+Config.SIZE;
            }
        }
        return bottom;
    }
    public int getLeft(){
        int left = figure.get(0).getX();
        for(int i = 0; i<figure.size();i++){
            if(figure.get(i).getX() <=left){
                left = figure.get(i).getX();
            }
        }
        return left;
    }
    public int getRight(){
        int right = figure.get(0).getX()+Config.SIZE;
        for(int i = 0; i<figure.size();i++){
            if(figure.get(i).getX()+Config.SIZE >= right){
                right = figure.get(i).getX()+Config.SIZE;
            }
        }
        return right;
    }

    public abstract void setColor();
    public abstract void rotateLeftSide();
    public abstract void rotateRightSide();
    public abstract void rotateFigure();

}
