package Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

public class Grid extends JComponent implements ActionListener {

    private JFrame sudokuWindow;
    private Rectangle2D.Double[][] gridRow;
    private JButton[] buttonArr;
    private int boardLength;
    private Point mouseLoc;

    public Grid(JFrame sudokuWindow, int boardLength, Point mouseLoc){
        this.sudokuWindow = sudokuWindow;
        this.boardLength = boardLength;
        buttonArr = new JButton[boardLength * boardLength];
        this.mouseLoc = mouseLoc;
    }

    public void genGridArr(Graphics2D graph2D){
        int mouseX = mouseLoc.x;
        int mouseY = mouseLoc.y;
        Rectangle2D.Double backSquare = new Rectangle2D.Double(0.15 * sudokuWindow.getWidth(), 0.25 * sudokuWindow.getHeight(), 0.6 * sudokuWindow.getHeight(), 0.6 * sudokuWindow.getHeight());
        graph2D.setColor(Color.black);
        graph2D.fill(backSquare);
        gridRow = new Rectangle2D.Double[this.boardLength][this.boardLength];
        int translater = 0;
        int rowIndex = 0;
        for (int columnIndex = 0; columnIndex < gridRow.length; columnIndex++) {
            if(columnIndex % (Math.pow(this.boardLength, (0.5))) == 0 && columnIndex != 1){
                translater += ((0.32 * this.sudokuWindow.getHeight())/(2 * Math.pow(this.boardLength, 0.5) - 1)) * 0.05;
            }
            double xCoord = 0.15 * sudokuWindow.getWidth() + 0.01 * sudokuWindow.getHeight() + columnIndex * ((0.32 * this.sudokuWindow.getHeight())/(2 * Math.pow(this.boardLength, 0.5) - 1))  + translater;
            double yCoord = 0.25 * sudokuWindow.getHeight() + + 0.01 * sudokuWindow.getHeight() + rowIndex * ((0.32 * this.sudokuWindow.getHeight())/(2 * Math.pow(this.boardLength, 0.5) - 1)) * 0.05;
            double dimOfBox = ((0.32 * this.sudokuWindow.getHeight())/(2 * Math.pow(this.boardLength, 0.5) - 1)) * 0.95;

            gridRow[rowIndex][columnIndex] = new Rectangle2D.Double(xCoord, yCoord, dimOfBox, dimOfBox);
            buttonArr[rowIndex + columnIndex] = new JButton();
            buttonArr[rowIndex + columnIndex].setBounds((int)xCoord, (int)yCoord, (int)dimOfBox, (int)dimOfBox);
            if(mouseX > xCoord && mouseX < xCoord + dimOfBox && mouseY > yCoord && mouseY < yCoord + dimOfBox){
                graph2D.setColor(Color.gray);
            }
            else {
                graph2D.setColor(Color.white);
            }
            graph2D.fill(gridRow[rowIndex][columnIndex]);

            if(columnIndex == gridRow.length - 1 && rowIndex < gridRow.length-1){
                rowIndex++;
                columnIndex = 0;
            }

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
