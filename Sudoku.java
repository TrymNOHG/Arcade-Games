package Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.Arrays;

public class Sudoku extends JPanel implements Runnable{
    //Have a save button
    private final int boardLength;
    private SudokuBoard sBoard;
    private final JFrame sudokuWindow;
    private final int FPS = 60;
    private int[][] unfinishedBoardArr;
    private int[][] boardArr;
    private final double timeStarted;
    JLabel timer = new JLabel();
    JLabel[][] unansweredNum;
    private int s = 0;
    Thread sudokuThread;

    public Sudoku(int boardLength, JFrame gameFrame) {
        this.setPreferredSize(new Dimension(680, 800));
        this.setDoubleBuffered(true);
        this.boardLength = boardLength;
        this.boardArr = new int[this.boardLength][this.boardLength];
        this.unfinishedBoardArr = new int[this.boardLength][this.boardLength];
        this.sudokuWindow = gameFrame;
        this.sudokuWindow.setTitle("Sudoku");
        this.timeStarted = System.nanoTime();
        unansweredNum = new JLabel[boardLength][boardLength];
        for(int i = 0; i < boardLength; i++){
            for(int j = 0; j < boardLength; j++){
                unansweredNum[i][j] = new JLabel();
            }
        }
    }

    public void newGame(int boardLength){
        this.sBoard = new SudokuBoard(boardLength);
        printBoards();
        sudokuThread = new Thread(this);
        sudokuThread.start();
    }

    public void printBoards(){
        for(int i = 0; i < sBoard.getBoardArr().length; i++){
            for(int j = 0; j < sBoard.getBoardArr().length; j++){
                System.out.print(sBoard.getBoardArr()[i][j] + " ");
            }
            System.out.println(" ");
        }

        System.out.println(" ");

        for(int i = 0; i < sBoard.getBoardArr().length; i++) {
            for (int j = 0; j < sBoard.getBoardArr().length; j++) {
                if(sBoard.getUnfinishedBoard()[i][j] != 0) {
                    System.out.print(sBoard.getUnfinishedBoard()[i][j] + " ");
                }
                else{
                    System.out.print("  ");
                }
            }
            System.out.println(" ");
        }
    }

    public void saveGame(int saveNumber) throws IOException {
        File sudokuSave = new File("Save " + saveNumber + ".txt");
        try{
            if(sudokuSave.createNewFile()){
                System.out.println("File was saved!");
            }
            else{
                System.out.println("Save Slot already taken!");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        FileWriter sudokuBoardsTxt = new FileWriter("Save " + saveNumber + ".txt");
        for (int numBoard = 0; numBoard < 2; numBoard++) {
            for(int rowIndex = 0; rowIndex < this.boardLength; rowIndex++) {
                for (int columnIndex = 0; columnIndex < this.boardLength; columnIndex++) {
                    if (numBoard == 0) {
                        sudokuBoardsTxt.write(sBoard.getBoardArr()[rowIndex][columnIndex] + " ");
                    } else {
                        sudokuBoardsTxt.write(sBoard.getUnfinishedBoard()[rowIndex][columnIndex] + " ");
                    }
                }
                sudokuBoardsTxt.write("\n");
            }
        }
        sudokuBoardsTxt.close();
    }

    public void loadGame(String fileName) throws IOException {
        BufferedReader sudokuReader = new BufferedReader(new FileReader(fileName));
        String bothBoards = sudokuReader.readLine();
        String[] boardNumbers = bothBoards.split(" ");
        for (int numBoard = 1; numBoard < 3; numBoard++) {
            for(int rowIndex = 0; rowIndex < this.boardLength; rowIndex++) {
                for (int columnIndex = 0; columnIndex < this.boardLength; columnIndex++) {
                    if (numBoard == 1) {
                        boardArr[rowIndex][columnIndex] = Integer.parseInt(boardNumbers[numBoard * (rowIndex * this.boardLength) + columnIndex]);
                    }
                    else {
                        unfinishedBoardArr[rowIndex][columnIndex] = Integer.parseInt(boardNumbers[numBoard * (rowIndex * this.boardLength) + columnIndex]);
                    }
                }
            }
        }

        sBoard = new SudokuBoard(this.boardArr, this.unfinishedBoardArr);
        printBoards();
        sudokuThread = new Thread(this);
        sudokuThread.start();

    }


    @Override
    protected void paintComponent(Graphics g){
        Graphics2D graphics2D = (Graphics2D) g;
        double timePlayed = (System.nanoTime()-timeStarted)/1000000000;
        if(this.sBoard != null) {
            SudokuGraphics sGame = new SudokuGraphics(this.sudokuWindow.getHeight(), this.sudokuWindow.getWidth(), this.boardLength, graphics2D, timer, timePlayed, this.sBoard.getUnfinishedBoard(), this.sudokuWindow, unansweredNum, this);
            for(int rowIndex = 0; rowIndex < this.boardLength; rowIndex++) {
                for (int columnIndex = 0; columnIndex < this.boardLength; columnIndex++) {
                    if (unansweredNum[rowIndex][columnIndex] != null && s < 1) {
                        this.add(unansweredNum[rowIndex][columnIndex]);
                    }
                }
            }
            s++;
        }
        this.add(timer);
        //graphics2D.dispose();
    }

    /**
     * In this run, I use the 'sleep' method for making the game have 60 FPS. This is done through calculating how many
     * nanoseconds there should be between updates. Thereafter, the next time the game should be updated can be found by
     * calculating the current time and then adding the interval time. Finally, if there is time remaining before the
     * thread should be updated, the thread is put to sleep.The other method of doing this is called "delta/accumulator"
     * method.
     */
    @Override
    public void run() {
        double updatingInterval = 1000000000/FPS;
        double nextUpdateTime = System.nanoTime() + updatingInterval;
        while(sudokuThread != null){
            repaint();
            try {
                double remainingTime = nextUpdateTime - System.nanoTime(); //This is in nanoseconds but sleep is calculated using milliseconds.
                remainingTime = remainingTime/1000000; //In order to find the number of milliseconds, we divide by 10^6.

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);

                nextUpdateTime += updatingInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

