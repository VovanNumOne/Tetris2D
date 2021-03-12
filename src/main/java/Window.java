import Figures.*;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Window implements ActionListener{

    /* Поля для хранения графических элементов игры. */
    private JFrame frame = new JFrame("Tetris");
    private JPanel firstPauseBar = new JPanel();
    private JPanel secondPauseBar = new JPanel();
    private JPanel additionalSpace = new JPanel();
    private JPanel infoBar = new JPanel();
    private JLabel scoreLabel= new JLabel();
    private JLabel infoLabel= new JLabel("Press Enter to pause");
    private JLabel rotateInfo = new JLabel("Press Space to rotate");
    private JLabel nextFLabel = new JLabel("Next Figure:");
    private JLabel endOfGame = new JLabel();

    /* Поля для хранения информации о фигуре, следующей фигуре и списка заполненных
    * клеток игрофого поля. */
    private Figure figure;
    private Figure figureForInfo;
    private List<Cells> cellsList = new ArrayList<>();
    private int nextFigure;
    private int randomFigure = -1;

    private Timer timer;

    /* Поля для хранения информации о возможности передвижения или поворота фигуры.*/
    private boolean DOWN_ALLOW = true;
    private boolean LEFT_ALLOW = true;
    private boolean RIGHT_ALLOW = true;
    private boolean ROTATE_ALLOW = true;
    private boolean ROTATE_LEFT_SIDE = false;
    private boolean ROTATE_RIGHT_SIDE = false;

    /* Поле для реализации функции паузы. */
    private boolean gameInProcess = true;

    /* Поле для хиранения информации о заработанных очках. */
    private int points = 0;

    // Конструктор формирует игровое поле и инициализирует игру
    Window(){
        formGameWindow();
        initGame();
    }

    /* Создается окно с игровым полем и информационной панелью, на которой
    * показывается счет, информация о следующей фигуре и инструкции к игре */
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
        
        Font font = new Font("Arial",Font.BOLD,25);
        scoreLabel.setFont(font);
        scoreLabel.setLocation(0,100);
        scoreLabel.setText("Score:"+points);
        infoLabel.setFont(new Font("Arial",Font.BOLD,18));
        rotateInfo.setFont(new Font("Arial",Font.BOLD,18));
        nextFLabel.setFont(new Font("Arial",Font.BOLD,18));

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

    /* Создается и запускается таймер, который будет слушать основное окно и
    * на каждый тик таймера будет выполнятся метод actionPerformed(ActionEvent e).
    * Также окну добавляется слушатель кнопок и создается первая фигура на игровом поле */
    public void initGame(){
        timer = new Timer(500,this);
        timer.start();

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE && ROTATE_ALLOW){
                    if(ROTATE_LEFT_SIDE){
                        figure.rotateLeftSide();
                    }else if(ROTATE_RIGHT_SIDE){
                        figure.rotateRightSide();
                    }else{
                        figure.rotateFigure();
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT && LEFT_ALLOW)
                    figure.moveLeft();
                if(e.getKeyCode() == KeyEvent.VK_RIGHT && RIGHT_ALLOW)
                    figure.moveRight();
                if(e.getKeyCode() == KeyEvent.VK_DOWN && DOWN_ALLOW)
                    figure.moveDown();
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    pauseGame();
                }
            }
        });

        addFigure();
    }

    /* Нажатие кнопки Enter инициализирует выполнение данного метода.
    * Если нажата кнопка и поле gameInProcess = true, то таймер останавливается,
    * блокируются все передвижения фигуры, на экран выводится символ паузы.
    * Если нажата книпка и поле gameInProcess = false, то запускается таймер,
    * снимаются ограничения на передвижение фигуры и скрывается символ паузы.
    * После каждого нажатия на кнопку поле gameInProcess меняет свое значение. */
    public void pauseGame(){
        if(gameInProcess){
            timer.stop();
            DOWN_ALLOW = false;
            LEFT_ALLOW = false;
            RIGHT_ALLOW = false;
            ROTATE_ALLOW = false;
            ROTATE_LEFT_SIDE = false;
            ROTATE_RIGHT_SIDE = false;
            firstPauseBar.setOpaque(true);
            secondPauseBar.setOpaque(true);
            infoLabel.setText("Press Enter to unpause");
        }else{
            timer.start();
            DOWN_ALLOW=true;
            RIGHT_ALLOW=true;
            LEFT_ALLOW=true;
            ROTATE_ALLOW=true;
            firstPauseBar.setOpaque(false);
            secondPauseBar.setOpaque(false);
            infoLabel.setText("Press Enter to pause");
        }
        frame.repaint();
        gameInProcess = !gameInProcess;
    }

    /* Преобразует объект Figure в список объектов Cells, для удобной работы.*/
    public void addFigureToList(Figure figure){
        for (Cells c: figure.getFigureCells())
            cellsList.add(c);
    }

    /* Проверяет заполненность линий начиная с самой нижней и, в случае если
    * линия заполненна объектами Cells, то номер линии добавляется в Stack, который после
    * выполнения данного метода передается в метод deleteLine для удаления лини.*/
    public synchronized void checkLines(){
        int sizeOfStack = 0;
        Stack<Integer> linesForDelete= new Stack<>();
        for(int level = Config.HEIGHT; level>=0; level--){
            int line = level*Config.SIZE;
            int cellsInLine = 0;
            for (Cells c: cellsList) {
                if(c.getY() == line){
                    cellsInLine++;
                    if(cellsInLine == Config.WEIGHT){
                        linesForDelete.push(line);
                        sizeOfStack++;
                        break;
                    }
                }
            }
        }
        if(!linesForDelete.empty()){
            deleteLine(linesForDelete, sizeOfStack);
        }
    }

    /* Удаляет заполненные линии начиная с верхней.
    * Зачисляет очки за удаленные линии.
    * Ускоряет падение фигур за каждые 2 удаленных линии. */
    public void deleteLine(Stack<Integer> linesForDelete, int sizeOfStack){
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
            points = points+9;
        }
        int i = points/18;
        timer.setDelay(500-50*i);
        scoreLabel.setText("Score:"+points);
        frame.repaint();
    }

    /* Добавляет фигуру предварительно проверив линии на заполненность.
    * Фигура выбирается случайно.
    * Так же определяет следующую фигуру и передает информацию о ней в
    * метод informNextFigure(int nextFigure).*/
    public void addFigure(){
        checkLines();
        DOWN_ALLOW = true;
        ROTATE_ALLOW = true;
        if(randomFigure == -1){
            randomFigure = (int) (Math.random()*7);
        }else{
            randomFigure = nextFigure;
            figureForInfo.deleteFigure();
        }
        nextFigure = (int) (Math.random()*7);

        switch (randomFigure){
            case 0:
                figure = new Square(frame);
                break;
            case 1:
                figure = new LongForm(frame);
                break;
            case 2:
                figure = new SForm(frame);
                break;
            case 3:
                figure = new ZForm(frame);
                break;
            case 4:
                figure = new TForm(frame);
                break;
            case 5:
                figure = new JForm(frame);
                break;
            case 6:
                figure = new LForm(frame);
                break;
        }
        informNextFigure(nextFigure);
        figure.showFigure();
    }

    /* Создает следующую фигуру и отображает ее на информационной панели.*/
    public void informNextFigure(int nextFigure){
        switch (nextFigure){
            case 0:
                figureForInfo = new Square(frame);
                break;
            case 1:
                figureForInfo = new LongForm(frame);
                break;
            case 2:
                figureForInfo = new SForm(frame);
                break;
            case 3:
                figureForInfo = new ZForm(frame);
                break;
            case 4:
                figureForInfo = new TForm(frame);
                break;
            case 5:
                figureForInfo = new JForm(frame);
                break;
            case 6:
                figureForInfo = new LForm(frame);
                break;
        }
        figureForInfo.displayNextFigureInfo(infoBar);
        infoBar.repaint();
    }

    /* Проверяет достигла ли фигура низа игрового поля или другой фигуры и уперлась ли она
    * в правый или левый край игрового поля или в другую фигуру.
    * В соответствии с конкретным случаем фигуре запрещаются определенные действия. */
    public void checkCollision(){

         /*Если фигура в крайнейм левом положении или уперлась в другую фигуру
         то дальнейшее движение влево запретить, а поворот осуществлять
         по алгоритму поворота для левой стороны*/
        if((figure.getLeft() <=0) || checkCollisionWithFigures()[0]) {
            LEFT_ALLOW = false;
            RIGHT_ALLOW = true;
            ROTATE_LEFT_SIDE = true;
        }else{
            LEFT_ALLOW = true;
            ROTATE_LEFT_SIDE = false;
        }

        /*Если фигура в крайнейм правом положении или уперлась в другую фигуру
        то дальнейшее движение вправо запретить, а поворот осуществлять
        по алгоритму поворота для правой стороны*/
        if((figure.getRight() >= Config.SIZE*Config.WEIGHT) || checkCollisionWithFigures()[1]) {
            RIGHT_ALLOW = false;
            LEFT_ALLOW = true;
            ROTATE_RIGHT_SIDE = true;
        }else{
            RIGHT_ALLOW = true;
            ROTATE_RIGHT_SIDE = false;
        }

         /*Если фигура достигла самой нижней точки или другой фигуры, то запретить все
         действия с ней*/
        if((figure.getBottom()+20 >= Config.SIZE*Config.HEIGHT) || checkCollisionWithFigures()[2]) {
            DOWN_ALLOW = false;
            LEFT_ALLOW = false;
            RIGHT_ALLOW = false;
            ROTATE_ALLOW = false;
        }
    }

    /* Вынесенный метод для проверки контакта фигуры с другими фигурами. */
    public boolean[] checkCollisionWithFigures(){
        boolean[] LRD = {false, false, false};
        for (Cells c: figure.getFigureCells()) {
            for (Cells filledCell: cellsList){

               // Если слева есть фигура, то блокируем движение влево
               if ((c.getX() - Config.SIZE) <= filledCell.getX() && c.getY() == filledCell.getY())
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

    /* Проверяет достиг ли уровень фигур верхней границы игрового поля и если достиг,
    * то вызывает метод endGame()*/
    public void checkEndGame(){
        for (Cells c: cellsList) {
            if(c.getY() == 0){
                endGame();
            }
        }
    }

    /* Останавливает таймер, блокирует передвижение фигуры и выводит надпись
    * Game Over!*/
    public void endGame(){
        timer.stop();
        DOWN_ALLOW = false;
        LEFT_ALLOW = false;
        RIGHT_ALLOW = false;
        ROTATE_ALLOW = false;
        ROTATE_LEFT_SIDE = false;
        ROTATE_RIGHT_SIDE = false;
        endOfGame.setText("Game Over!");
        frame.repaint();
    }

    /* Метод выполняется на каждый тик таймера, который является событием передаваемым в качестве
    * аргумента этого метода.
    * На каждый тик последовательно выполняются операции проверки окончания игры,
    * проверки колизий, перемещения фигуры вниз и, в случае, если фигура остановленна,
    * создание следующей фигуры. */
    @Override
    public void actionPerformed(ActionEvent e) {
        checkEndGame();
        checkCollision();
        if(DOWN_ALLOW) {
            figure.moveDown();
        }
        if(DOWN_ALLOW == false) {
            addFigureToList(figure);
            addFigure();
        }
    }

}
