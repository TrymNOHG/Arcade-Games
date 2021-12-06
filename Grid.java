package Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

public class Grid extends JComponent implements ActionListener {

    private JFrame sudokuWindow;
    private JButton[] buttonArr;
    private int boardLength;
    private Point mouseLoc;
    private int frameHeight;
    private int frameWidth;
    private int[][] unfinishedBoard;
    private boolean selected;

    public Grid(int frameHeight, int frameWidth, int boardLength, int[][] unfinishedBoard, JFrame sudokuWindow){
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;
        this.boardLength = boardLength;
        this.unfinishedBoard = unfinishedBoard;
        this.sudokuWindow = sudokuWindow;
        this.selected = false;
    }

    public void genGridArr(Graphics2D graph2D){
        mouseLoc = MouseInfo.getPointerInfo().getLocation();
        int mouseX = mouseLoc.x;
        int mouseY = mouseLoc.y;
        double squareLength = ((0.32 * this.frameHeight)/(2 * Math.pow(this.boardLength, 0.5) - 1));
        //int squareLength =
        graph2D.drawRect((int) (0.15 * this.frameWidth), (int) (0.25 * this.frameHeight), (int) (0.6 * this.frameHeight), (int) (0.6 * this.frameHeight));
        graph2D.setColor(Color.black);
        graph2D.fillRect((int) (0.15 * this.frameWidth), (int) (0.25 * this.frameHeight), (int) (0.6 * this.frameHeight), (int) (0.6 * this.frameHeight));
        int translateX = 0;
        int translateY = 0;
        int rowIndex = 0;
        for (int columnIndex = 0; columnIndex < this.boardLength; columnIndex++) {
            if(columnIndex % (Math.pow(this.boardLength, (0.5))) == 0 && columnIndex > 1){
                translateX += squareLength * 0.05;
            }
            else if(rowIndex % (Math.pow(this.boardLength, (0.5))) == 0 && rowIndex > 1 && columnIndex == 0){
                translateY += squareLength * 0.05;
            }
            int xCoord = (int) (0.15 * this.frameWidth + 0.01 * this.frameHeight + columnIndex * squareLength + translateX);
            int yCoord = (int) (0.25 * this.frameHeight + 0.01 * this.frameHeight + rowIndex * squareLength + translateY);
            int dimOfBox = (int) (((0.32 * this.frameHeight)/(2 * Math.pow(this.boardLength, 0.5) - 1)) * 0.95);
            GridSquare gS = new GridSquare();
//            buttonArr[rowIndex + columnIndex] = new JButton();
//            buttonArr[rowIndex + columnIndex].setBounds((int)xCoord, (int)yCoord, (int)dimOfBox, (int)dimOfBox);
            if(mouseX > xCoord && mouseX < xCoord + dimOfBox && mouseY > yCoord + 50 && mouseY < yCoord + dimOfBox + 50){
                gS.createRect(graph2D, Color.gray, xCoord, yCoord, dimOfBox);
            }
            else {
                gS.createRect(graph2D, Color.white,xCoord, yCoord, dimOfBox);
            }
//
//            if(unfinishedBoard[rowIndex][columnIndex] != 0) {
//                JLabel num = new JLabel();
//                num.setText(String.valueOf(unfinishedBoard[rowIndex][columnIndex]));
//                num.setBounds(20 * columnIndex, 10 * rowIndex, 10, 10);
//                sudokuWindow.add(num);
//            }

            if(columnIndex == this.boardLength - 1 && rowIndex < this.boardLength-1){
                rowIndex++;
                columnIndex = -1;
                translateX = 0;
            }

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


//It might be better to split the frame up into bigger "pixels" and that way make up the grid. Maybe make the screen size constant
//Figure out the scaling and stuff tomorrow
//If I wanted this optimized, I would have to just change the color instead of adding new rectangles each time.