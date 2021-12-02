import javax.swing.*;

public class Sudoku {
    //I want this class to be able to extract a saved game's information from a file.
    //I, then, want to create a SudokuBoard object based on the game info.
    //Have a save button
    private int boardLength;
    private SudokuBoard sBoard;

    public Sudoku() {
        newGame(9);
    }



    public void newGame(int boardLength){
        sBoard = new SudokuBoard(9);
        printBoards();
        SudokuGraphics sGraph = new SudokuGraphics(9);
        sGraph.getSudokuWindow().add(sGraph);
        sGraph.getSudokuWindow().setVisible(true);
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


}
