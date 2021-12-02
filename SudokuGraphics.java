import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Rectangle2D;

//implements ComponentListener

public class SudokuGraphics extends JComponent {
    private int boardLength;
    private Rectangle2D.Double[] gridRow;
    private JFrame sudokuWindow;


    public SudokuGraphics(int boardLength) {
        this.boardLength = boardLength;
        this.sudokuWindow = new JFrame();
//        this.sudokuWindow.addComponentListener(this);
        windowScreen();
    }

    //Make the grid
    //Add the timer maybe
    //Add other cool things

    public void windowScreen(){
        sudokuWindow.setSize(700, 700);
        sudokuWindow.setTitle("Sudoku");
        sudokuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    protected void paintComponent(Graphics g){
        Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
        int mouseX = mouseLoc.x;
        int mouseY = mouseLoc.y;
        Graphics2D graph2D = (Graphics2D) g;
        gridRow = new Rectangle2D.Double[this.boardLength + 1];
        gridRow[0] = new Rectangle2D.Double(0.15 * sudokuWindow.getWidth(), 0.25 * sudokuWindow.getHeight(), 0.6 * sudokuWindow.getHeight(), 0.6 * sudokuWindow.getHeight());
        graph2D.setColor(Color.black);
        graph2D.fill(gridRow[0]);
        int translater = 0;
        for (int i = 1; i < gridRow.length; i++) {
            if((i-1) % (Math.pow(this.boardLength, (0.5))) == 0 && i != 1){
                translater += ((0.32 * this.sudokuWindow.getHeight())/(2 * Math.pow(this.boardLength, 0.5) - 1)) * 0.05;
            }
            double xCoord = 0.15 * sudokuWindow.getWidth() + 0.01 * sudokuWindow.getHeight() + (i-1) * ((0.32 * this.sudokuWindow.getHeight())/(2 * Math.pow(this.boardLength, 0.5) - 1))  + translater;
            double yCoord = 0.25 * sudokuWindow.getHeight() + + 0.01 * sudokuWindow.getHeight();
            double dimOfBox = ((0.32 * this.sudokuWindow.getHeight())/(2 * Math.pow(this.boardLength, 0.5) - 1)) * 0.95;

            if(mouseX > xCoord && mouseX < xCoord + dimOfBox && mouseY > yCoord && mouseY < yCoord + dimOfBox){
                gridRow[i] = new Rectangle2D.Double(xCoord, yCoord, dimOfBox, dimOfBox);
                graph2D.setColor(Color.gray);
                graph2D.fill(gridRow[i]);
            }
            else {
                gridRow[i] = new Rectangle2D.Double(xCoord, yCoord, dimOfBox, dimOfBox);
                graph2D.setColor(Color.white);
                graph2D.fill(gridRow[i]);
            }
        }

        int counter = 1;
        translater = 0;
        for (int i = 1; i < this.boardLength; i++) {
            if(counter % (Math.pow(this.boardLength, (0.5))) == 0 && counter != 1){
                translater += ((0.32 * this.sudokuWindow.getHeight())/(2 * Math.pow(this.boardLength, 0.5) - 1)) * 0.05;
            }
            graph2D.translate(0, ((0.32 * this.sudokuWindow.getHeight())/(2 * Math.pow(this.boardLength, 0.5) - 1)) + translater);
            translater = 0;
            for (int j = 1; j < gridRow.length; j++) {

                graph2D.fill(gridRow[j]);
            }
            counter++;
        }


    }


//    @Override
//    public void componentResized(ComponentEvent e) {
//
//    }
//
//    @Override
//    public void componentMoved(ComponentEvent e) {
//
//    }
//
//    @Override
//    public void componentShown(ComponentEvent e) {
//
//    }
//
//    @Override
//    public void componentHidden(ComponentEvent e) {
//
//    }

    public Rectangle2D.Double[] getGridRow() {
        return gridRow;
    }

    public JFrame getSudokuWindow() {
        return sudokuWindow;
    }
}

//I want to display the correct answers by making the number green and incorrect by having the number turn red. I could
//also make it so that it doesn't show if the answers are correct or not until it is filled in.
//Make the board change based on the window size
//Use the cursor to highlight squares (change them to grey)
