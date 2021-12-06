package Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

//implements ComponentListener

public class SudokuGraphics extends JPanel {
    //private JButton[] buttonArr;

    public SudokuGraphics() {

    }

    public void genWholeBoard(int frameHeight, int frameWidth, int boardLength, Graphics2D graphics2D, JLabel timer, double timePlayed, int[][] unfinishedBoard, JFrame sudokuWindow){
        Grid sudukoGrid = new Grid(frameHeight, frameWidth, boardLength, unfinishedBoard, sudokuWindow);
        sudukoGrid.genGridArr(graphics2D);
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
            mins = "0" + String.valueOf(minutes);
        }
        if(seconds < 10){
            secs = "0" + String.valueOf(seconds);
        }

        return  mins + ":" + secs;
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
