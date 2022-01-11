package Sudoku;

import javax.swing.*;
import java.awt.*;

//implements ComponentListener

public class SudokuGraphics extends JPanel {
    //private JButton[] buttonArr;
    private Grid sudokuGrid;
    public SudokuGraphics(int frameHeight, int frameWidth, int boardLength, Graphics2D graphics2D, JLabel timer, double timePlayed, int[][] unfinishedBoard, JFrame sudokuWindow, JLabel[][] unansweredNum, Sudoku currentGame) {
        genWholeBoard(frameHeight, frameWidth, boardLength, graphics2D, timer, timePlayed, unfinishedBoard, sudokuWindow, unansweredNum, currentGame);
    }

    public void genWholeBoard(int frameHeight, int frameWidth, int boardLength, Graphics2D graphics2D, JLabel timer, double timePlayed, int[][] unfinishedBoard, JFrame sudokuWindow, JLabel[][] unansweredNum, Sudoku currentGame){
        sudokuGrid = new Grid(frameHeight, frameWidth, boardLength, unfinishedBoard, sudokuWindow, unansweredNum, currentGame);
        sudokuGrid.genGridArr(graphics2D);
        unansweredNum = sudokuGrid.getUnansweredNum();
        timer.setText("Time: " + genTimer(timePlayed));
        timer.setBounds((int) (0.15 * frameWidth), (int) (0.22 * frameHeight), frameWidth, 20);
        timer.setFont(new Font("Calibri", Font.BOLD, (int) (0.02 * frameHeight)));
    }

    public String genTimer(double timePlayed){
        int minutes = (int) (timePlayed / 60);
        int seconds = (int) (timePlayed % 60);
        String mins = String.valueOf(minutes);
        String secs = String.valueOf(seconds);

        if(minutes < 10){
            mins = "0" + minutes;
        }
        if(seconds < 10){
            secs = "0" + seconds;
        }

        return  mins + ":" + secs;
    }

    public Grid getSudokuGrid() {
        return sudokuGrid;
    }

    //Make the grid
    //Add the timer maybe
    //Add other cool things

}

//I want to display the correct answers by making the number green and incorrect by having the number turn red. I could
//also make it so that it doesn't show if the answers are correct or not until it is filled in.
//Make the board change based on the window size
//Use the cursor to highlight squares (change them to grey)

//Use JButton for a button on a panel!! Looks slick
