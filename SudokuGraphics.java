package Sudoku;

import javax.swing.*;
import java.awt.*;

//implements ComponentListener

public class SudokuGraphics extends JComponent {
    private final int boardLength;
    private JFrame sudokuWindow;
    private Grid newGrid;

    public SudokuGraphics(int boardLength, JFrame sudokuWindow, Point mouseLoc) {
        this.boardLength = boardLength;
        this.sudokuWindow = sudokuWindow;
        newGrid = new Grid(this.sudokuWindow, this.boardLength, mouseLoc);
    }

    public Grid getNewGrid() {
        return newGrid;
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
