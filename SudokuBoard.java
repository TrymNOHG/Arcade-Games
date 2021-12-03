package Sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class Description
 */
public class SudokuBoard {
    private int[][] boardArr;
    private int[][] unfinishedBoard;
    private int lengthOfBoard;
    private List<Boolean[]> oneToNine;


    /**
     * This is the constructor for a new game of sudoku and represents the board.
     * @param lengthOfBoard The amount of squares per row/column.
     */
    public SudokuBoard(int lengthOfBoard) {
        if(lengthOfBoard < 1 || Math.pow(lengthOfBoard, 0.5) % 1 != 0){
            throw new IllegalArgumentException("The value of is smaller than one, and therefore, a board of this size " +
                    "cannot be made!");
        }
        this.lengthOfBoard = lengthOfBoard;
        this.boardArr = generateBoard(lengthOfBoard);
        this.unfinishedBoard = genUnfinishedBoard();
        //Have it be instantly filled here!
    }

    /**
     * This constructor exists for loaded games, where a 2d board array has already been made.
     * @param boardArr The completed sudoku board that was saved.
     * @param unfinishedBoard The unfinished sudoku board that was saved.
     */
    public SudokuBoard(int[][] boardArr, int[][] unfinishedBoard) {
        this.boardArr = boardArr;
        this.unfinishedBoard = unfinishedBoard;
    }

    /**
     * This method generates a sudoku board, which is correct, and pseudorandom.
     * This is done through the use of 3 x the board length worth of boolean arrays with a length the same as the board.
     * 2 x the board length of these arrays are used to represent the nine different possible numbers that the number in a given slot can be.
     * Each index in the boolean array represents a number in the row or column and the boolean itself tells
     * whether that number exists in the table already or not. This is a method to circumvent using 4 for loops to
     * search through the whole board and then to compare. It has hopefully optimized the code more. The last board length worth
     * of arrays are used to represent each "box" that the numbers exist in. In the case of a 9 by 9, these boxes are
     * 3 by 3. This is one of the rules of Sudoku.Sudoku. In order to not use more for loops, the column and row indices were
     * converted to "coordinates". Where the first box has an index of 0 + 2 x board length and it increases one as you
     * go down the row and starts at the first column again when it reaches the end. This was done by a lot of modular manipulation.
     *
     * @param lengthOfBoard The length of the board
     * @return A filled sudoku board, represented as a 2D array.
     */
    public int[][] generateBoard(int lengthOfBoard){
        int[][] genBoard = new int[lengthOfBoard][lengthOfBoard];
        List<Boolean[]> availableNum = new ArrayList<>(fillBoolArr());
        Random rand = new Random();
        int randomNum;
        int rowIndex = 0;
        int columnIndex = 0;
        int counter = 0;
        //This could be optimized to pick a correct number quickly by removing
        while(rowIndex < lengthOfBoard-2 || columnIndex < lengthOfBoard){
            counter++;
            randomNum = rand.nextInt(lengthOfBoard) + 1;
            if(availableNum.get(rowIndex+lengthOfBoard)[randomNum - 1] == false && availableNum.get(columnIndex)[randomNum - 1] == false){
                int colGridIndex = (int) ((columnIndex - (columnIndex % Math.pow(lengthOfBoard, 0.5)))/Math.pow(lengthOfBoard, 0.5));
                int rowGridIndex = (int) (((((rowIndex + lengthOfBoard) - ((rowIndex + lengthOfBoard)) % Math.pow(lengthOfBoard, 0.5) ) / Math.pow(lengthOfBoard, 0.5)) % Math.pow(lengthOfBoard, 0.5)) * Math.pow(lengthOfBoard, 0.5));
                int squareGridIndex = colGridIndex + rowGridIndex + 2 * lengthOfBoard;
                if(availableNum.get(squareGridIndex)[randomNum - 1] == false){
                    genBoard[rowIndex][columnIndex] = randomNum;
                    availableNum.get(rowIndex+lengthOfBoard)[randomNum - 1] = true;
                    availableNum.get(columnIndex)[randomNum - 1] = true;
                    availableNum.get(squareGridIndex)[randomNum - 1] = true;
                    counter = 0;
                }
                else{
                    continue;
                }
            }
            else{
                if(counter > Math.pow(lengthOfBoard, 2)){
                    rowIndex = 0;
                    columnIndex = 0;
                    counter = 0;
                    genBoard = new int[lengthOfBoard][lengthOfBoard];
                    availableNum = new ArrayList<>(fillBoolArr());
                }
                continue;
            }
            columnIndex++;
            if(columnIndex == lengthOfBoard && rowIndex < lengthOfBoard-1){
                rowIndex++;
                columnIndex = 0;
            }

        }
        return genBoard;
    }

    /**
     * This method fills the arrayList with parameter type boolean array with arrays where all the indices of the array
     * have the boolean value of false.
     * @return ArrayList with type Boolean[], filled with false for each index in the arrays.
     */
    public List<Boolean[]> fillBoolArr(){
        oneToNine = new ArrayList<>();
        for(int i = 0; i < 3*lengthOfBoard; i++){
            oneToNine.add(new Boolean[lengthOfBoard]);
            Arrays.fill(oneToNine.get(i), Boolean.FALSE);
        }
        return oneToNine;
    }

    public int[][] genUnfinishedBoard(){
        int[][] genUnfishedBoard = new int[this.lengthOfBoard][this.lengthOfBoard];
        int rowIndex = 0;
        Random rand = new Random();
        for (int columnNum = 0; columnNum < Math.pow(this.lengthOfBoard, 0.5); columnNum++) {
            int randoNum = rand.nextInt(this.lengthOfBoard);
            genUnfishedBoard[rowIndex][randoNum] = this.boardArr[rowIndex][randoNum];

            if(columnNum == Math.pow(this.lengthOfBoard, 0.5) - 1 && rowIndex < lengthOfBoard-1){
                rowIndex++;
                columnNum = 0;
            }
        }

        return genUnfishedBoard;
    }

    public int[][] getBoardArr() {
        return boardArr;
    }

    public int[][] getUnfinishedBoard() {
        return unfinishedBoard;
    }
}
/**
 * I want this class to create a board, allow the player to interact with it (as in have the different coordinates of
 * the squares represent a number from the 2d array, allowing the player to enter a guess and check if it is correct),
 * allow for incorrect tries to be checked, maybe have a score system, a timer, if it is solved, then something should
 * happen
 */

