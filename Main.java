package Main;

import Sudoku.Sudoku;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame gameScreen = new JFrame();
        gameScreen.setSize(680, 800);
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Sudoku game = new Sudoku(9, gameScreen);
        gameScreen.add(game);
        gameScreen.pack();

        gameScreen.setVisible(true);

        game.newGame(9);



    }

}

//Use a component Listener in order to get input on the change in the window size and accordingly change the
//size of the things within the window (scaling).


//Other games to make: Snake, Tetris, Shifting jigsaw, Connect 4 (with AI?), Chess (with AI?), Camera manipulation game