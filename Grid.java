package Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Grid extends JPanel implements MouseListener, KeyListener {

    private JFrame sudokuWindow;
    private int boardLength;
    private Point mouseLoc;
    private int frameHeight;
    private int frameWidth;
    private int[][] unfinishedBoard;
    private JLabel[][] unansweredNum;
    private static int mouseClickX;
    private static int mouseClickY;
    private boolean validBox = true;
    private Color lineColor;


    public Grid(int frameHeight, int frameWidth, int boardLength, int[][] unfinishedBoard, JFrame sudokuWindow, JLabel[][] unansweredNum, Sudoku currentGame){
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
        this.boardLength = boardLength;
        this.unfinishedBoard = unfinishedBoard;
        this.sudokuWindow = sudokuWindow;
        this.unansweredNum = unansweredNum;
        currentGame.addMouseListener(this);
        //Adding a new mouselistener for each Grid update creates a stackoverflow.
        currentGame.addKeyListener(this);
    }

    public void genGridArr(Graphics2D graph2D){
        mouseLoc = MouseInfo.getPointerInfo().getLocation();
        int mouseX = mouseLoc.x;
        int mouseY = mouseLoc.y;
        double squareLength = ((0.32 * this.frameHeight)/(2 * Math.pow(this.boardLength, 0.5) - 1));

        graph2D.drawRect((int) (0.15 * this.frameWidth), (int) (0.25 * this.frameHeight), (int) (0.6 * this.frameHeight), (int) (0.6 * this.frameHeight));
        graph2D.setColor(Color.black);
        graph2D.fillRect((int) (0.15 * this.frameWidth), (int) (0.25 * this.frameHeight), (int) (0.6 * this.frameHeight), (int) (0.6 * this.frameHeight));

        int translateX = 0;
        int translateY = 0;
        for(int rowIndex = 0; rowIndex < this.boardLength; rowIndex++) {
            for (int columnIndex = 0; columnIndex < this.boardLength; columnIndex++) {

                if (columnIndex % (Math.pow(this.boardLength, (0.5))) == 0 && columnIndex > 1) {
                    translateX += squareLength * 0.05;
                }
                else if (rowIndex % (Math.pow(this.boardLength, (0.5))) == 0 && rowIndex > 1 && columnIndex == 0) {
                    translateY += squareLength * 0.05;
                }
                int xCoord = (int) (0.15 * this.frameWidth + 0.01 * this.frameHeight + columnIndex * squareLength + translateX);
                int yCoord = (int) (0.25 * this.frameHeight + 0.01 * this.frameHeight + rowIndex * squareLength + translateY);
                int dimOfBox = (int) (((0.32 * this.frameHeight) / (2 * Math.pow(this.boardLength, 0.5) - 1)) * 0.95);
                GridSquare gS = new GridSquare();

                boolean isWithinGridSquare = mouseX > xCoord && mouseX < xCoord + dimOfBox && mouseY > yCoord + 50 && mouseY < yCoord + dimOfBox + 50;

                if (isWithinGridSquare) {
                    gS.createRect(graph2D, Color.gray, xCoord, yCoord, dimOfBox, dimOfBox);
                }
                else {
                    gS.createRect(graph2D, Color.white, xCoord, yCoord, dimOfBox, dimOfBox);
                }
                //Remove the else above to basically get a dark mode sudoku!!!


                if(underLinedSquare(xCoord, yCoord, dimOfBox, gS, graph2D, rowIndex, columnIndex, isWithinGridSquare)){

                }
                graph2D.setColor(Color.white);


                if (this.unfinishedBoard[rowIndex][columnIndex] != 0) {
                    unansweredNum[rowIndex][columnIndex].setText(String.valueOf(this.unfinishedBoard[rowIndex][columnIndex]));
                    unansweredNum[rowIndex][columnIndex].setBounds(xCoord + 15, yCoord + 5, 30, 30);
                    unansweredNum[rowIndex][columnIndex].setFont(new Font("Calibri", Font.BOLD, (int) (0.04 * frameHeight)));
                }
                else {
                    unansweredNum[rowIndex][columnIndex] = null;
                }

            }
            translateX = 0;
        }
    }

    public JLabel[][] getUnansweredNum() {
        return unansweredNum;
    }

    private boolean underLinedSquare(int xCoord, int yCoord, int dimOfBox, GridSquare gS, Graphics2D graph2D, int rowIndex, int columnIndex, boolean isWithinGridSquare){
        if (mouseClickX > xCoord && mouseClickX < xCoord + dimOfBox && mouseClickY> yCoord + 50 && mouseClickY < yCoord + dimOfBox + 50 && validBox){
            //To generalize this more, I could write as long as the mouseClick exists within a multiple of the dimension box in x and y.
            if(this.unansweredNum[rowIndex][columnIndex] == null) {
                if((int) (System.nanoTime()/1000000000 % 60) % 2 == 0){
                    lineColor = Color.BLACK;
                }
                else{
                    if (isWithinGridSquare) {
                        lineColor = Color.gray;
                    }
                    else{
                        lineColor = Color.WHITE;
                    }
                }
            }
            gS.createRect(graph2D, lineColor, xCoord + 10, yCoord + dimOfBox - 10, (int) (dimOfBox * 0.5), (int) (dimOfBox * 0.05));
            return true;
        }
        return false;
    }

    private void allowWriteNumber(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.mouseClickX = mouseLoc.x;
        this.mouseClickY = mouseLoc.y;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}


//It might be better to split the frame up into bigger "pixels" and that way make up the grid. Maybe make the screen size constant
//Figure out the scaling and stuff tomorrow
//If I wanted this optimized, I would have to just change the color instead of adding new rectangles each time.