package Figures;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
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
        Cells bot = figure.stream().max(Comparator.comparing(Cells::getY)).orElse(null);
        return bot.getY()+Config.SIZE;

    }
    public int getLeft(){
        Cells left= figure.stream().min(Comparator.comparing(Cells::getX)).orElse(null);
        return left.getX();
    }
    public int getRight(){
        Cells right = figure.stream().max(Comparator.comparing(Cells::getX)).orElse(null);
        return right.getX()+Config.SIZE;
    }

    public abstract void rotateLeftSide();
    public abstract void rotateRightSide();
    public abstract void rotateFigure();

}
