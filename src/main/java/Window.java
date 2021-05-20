import Figures.*;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Window implements ActionListener{


    private final JFrame frame = new JFrame("Tetris");
    private final JPanel firstPauseBar = new JPanel();
    private final JPanel secondPauseBar = new JPanel();
    private final JPanel additionalSpace = new JPanel();
    private final JPanel infoBar = new JPanel();
    private final JLabel scoreLabel= new JLabel();
    private final JLabel infoLabel= new JLabel("Press Enter to pause");
    private final JLabel rotateInfo = new JLabel("Press Space to rotate");
    private final JLabel nextFLabel = new JLabel("Next Figure:");
    private final JLabel endOfGame = new JLabel();

    private Figure figure;
    private Figure figureForInfo;
    private List<Cells> cellsList = new ArrayList<>();
    private int nextFigure;
    private int randomFigure = -1;

    private Timer timer;

    private boolean canMove = true;

    private boolean gameInProcess = true;

    private int points = 0;

    Window(){
        formGameWindow();
        initGame();
    }
    public void formGameWindow(){
        frame.setSize(Config.SIZE*Config.WEIGHT+13+250, Config.SIZE*Config.HEIGHT+35);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(500,10);
        frame.setResizable(false);

        endOfGame.setFont(new Font("Arial",Font.BOLD,40));
        endOfGame.setBounds(Config.WEIGHT*Config.SIZE/2-100, Config.HEIGHT*Config.SIZE/2-300,300,300);
        frame.add(endOfGame);

        additionalSpace.setBounds(Config.SIZE*Config.WEIGHT,0,250,frame.getHeight());
        additionalSpace.setBackground(Color.BLACK);
        additionalSpace.setLayout(null);
        
        infoBar.setBackground(Color.WHITE);
        infoBar.setBounds(5,0,additionalSpace.getWidth(),additionalSpace.getHeight());


        scoreLabel.setFont(new Font("Arial",Font.BOLD,25));
        scoreLabel.setLocation(0,100);
        scoreLabel.setText("Score:"+points);
        Font font = new Font("Arial",Font.BOLD,18);
        infoLabel.setFont(font);
        rotateInfo.setFont(font);
        nextFLabel.setFont(font);

        infoBar.setLayout(null);
        scoreLabel.setBounds(50,0,250,100);
        infoLabel.setBounds(15,600,250,100);
        rotateInfo.setBounds(15,630,250,100);
        nextFLabel.setBounds(50,200,250,100);
        infoBar.add(scoreLabel);
        infoBar.add(infoLabel);
        infoBar.add(rotateInfo);
        infoBar.add(nextFLabel);

        additionalSpace.add(infoBar);
        frame.add(additionalSpace);

        firstPauseBar.setBackground(Color.BLACK);
        secondPauseBar.setBackground(Color.BLACK);
        firstPauseBar.setOpaque(false);
        secondPauseBar.setOpaque(false);
        firstPauseBar.setBounds(Config.WEIGHT*Config.SIZE/2 - 15, Config.HEIGHT*Config.SIZE/2 - 100, 15,50);
        secondPauseBar.setBounds(Config.WEIGHT*Config.SIZE/2 + 15, Config.HEIGHT*Config.SIZE/2 - 100, 15,50);
        frame.add(firstPauseBar);
        frame.add(secondPauseBar);

        frame.setVisible(true);
    }
    public void initGame(){
        timer = new Timer(500,this);
        timer.start();

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    pauseGame();
                }
                if(gameInProcess) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        if(shouldRightRotate() && shouldLeftRotate()){
                            return;
                        }else if (shouldLeftRotate()) {
                            figure.rotateLeftSide();
                        } else if (shouldRightRotate()) {
                            figure.rotateRightSide();
                        } else {
                            figure.rotateFigure();
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_LEFT && !shouldLeftRotate())
                        figure.moveLeft();
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT && !shouldRightRotate())
                        figure.moveRight();
                    if (e.getKeyCode() == KeyEvent.VK_DOWN && canMoveDown())
                        figure.moveDown();
                }
            }
        });

        addFigure();
    }
    public boolean shouldLeftRotate(){
        return (figure.getLeft() <= 0) || checkContactWithFigures()[0];
    }
    public boolean shouldRightRotate(){
        return (figure.getRight() >= Config.SIZE * Config.WEIGHT) || checkContactWithFigures()[1];
    }
    public boolean canMoveDown(){
        return !((figure.getBottom() + 20 >= Config.SIZE * Config.HEIGHT) || checkContactWithFigures()[2]);
    }
    public void pauseGame(){
        firstPauseBar.setOpaque(gameInProcess);
        secondPauseBar.setOpaque(gameInProcess);
        if(gameInProcess){
            timer.stop();
            infoLabel.setText("Press Enter to continue");
        }else{
            timer.start();
            infoLabel.setText("Press Enter to pause");
        }
        frame.repaint();
        gameInProcess = !gameInProcess;
    }
    public void addFigureToList(Figure figure){
        cellsList.addAll(figure.getFigureCells());
    }
    public void checkLines(){
        Stack<Integer> linesForDelete= new Stack<>();
        for(int level = Config.HEIGHT; level>=0; level--){
            int line = level*Config.SIZE;
            int cellsInLine = 0;
            for (Cells c: cellsList) {
                if(c.getY() == line){
                    cellsInLine++;
                    if(cellsInLine == Config.WEIGHT){
                        linesForDelete.push(line);
                        break;
                    }
                }
            }
        }
        if(!linesForDelete.empty()){
            deleteLine(linesForDelete);
        }
    }
    public void deleteLine(Stack<Integer> linesForDelete){
        int sizeOfStack = linesForDelete.size();
        for (int i = 0; i<sizeOfStack; i++) {
            int line = linesForDelete.pop();
            List<Cells> forRemove = new ArrayList<>();
            for (Cells c: cellsList) {
                if(c.getY() <= line){
                    if(c.getY() == line){
                        c.setOpaque(false);
                        forRemove.add(c);
                    }
                    c.setY(c.getY()+Config.SIZE);
                }
            }
            cellsList.removeAll(forRemove);
            addPoints();
        }
        frame.repaint();
    }
    public void addPoints(){
        points +=9;
        scoreLabel.setText("Score:"+points);
        speedUp();
    }
    public void speedUp(){
        timer.setDelay(500-points);
    }

    public void addFigure(){
        checkLines();
        canMove = true;
        if(randomFigure == -1){
            randomFigure = (int) (Math.random()*7);
        }else{
            randomFigure = nextFigure;
            figureForInfo.deleteFigure();
        }
        nextFigure = (int) (Math.random()*7);
        figure = getFigure(randomFigure);
        informNextFigure(nextFigure);

        figure.showFigure();
    }
    public Figure getFigure(int numberOfFigure){
        Figure f=null;
        switch (numberOfFigure) {
            case 0:
                f= new Square(frame);
                break;
            case 1:
                f=new LongForm(frame);
                break;
            case 2:
                f=new SForm(frame);
                break;
            case 3:
                f=new ZForm(frame);
                break;
            case 4:
                f=new TForm(frame);
                break;
            case 5:
                f=new JForm(frame);
                break;
            case 6:
                f=new LForm(frame);
                break;
        }
        return f;
    }

    public void informNextFigure(int nextFigure){
        figureForInfo = getFigure(nextFigure);
        figureForInfo.displayNextFigureInfo(infoBar);
        infoBar.repaint();
    }

    public void checkCollision(){

         /*Если фигура достигла самой нижней точки или другой фигуры, то запретить все
         действия с ней*/
        if((figure.getBottom()+20 >= Config.SIZE*Config.HEIGHT) || checkContactWithFigures()[2]) {
            canMove = false;
        }
    }

    public boolean[] checkContactWithFigures(){
        boolean[] LRD = {false, false, false};
        for (Cells c: figure.getFigureCells()) {
            for (Cells filledCell: cellsList){

               // Если слева есть фигура, то блокируем движение влево
               if ((c.getX() - Config.SIZE) == filledCell.getX() && c.getY() == filledCell.getY())
                   LRD[0] = true;

               // Если справа есть фигура, то блокируем движение вправо
               if ((c.getX() + Config.SIZE) == filledCell.getX() && c.getY() == filledCell.getY())
                   LRD[1] = true;

               // Если упали на фигуру, то блокируем движение
               if ((c.getX() == filledCell.getX() && (c.getY() + Config.SIZE) == filledCell.getY()))
                   LRD[2] = true;

            }

        }
        return LRD;
    }

    public void checkEndGame(){
        for (Cells c: cellsList) {
            if(c.getY() == 0){
                endGame();
            }
        }
    }

    public void endGame(){
        timer.stop();
        canMove = false;
        endOfGame.setText("Game Over!");
        frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkEndGame();
        checkCollision();
        if(canMove) {
            figure.moveDown();
        }
        if(canMove == false) {
            addFigureToList(figure);
            addFigure();
        }
    }

}
