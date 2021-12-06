package Sudoku;

import java.awt.*;

public class GridSquare {

    public GridSquare() {

    }

    public void createRect(Graphics2D graphics2D, Color c , int xCoord, int yCoord, int dimOfBox){
            graphics2D.drawRect(xCoord, yCoord, dimOfBox, dimOfBox);
            graphics2D.setColor(c);
            graphics2D.fillRect(xCoord, yCoord, dimOfBox, dimOfBox);
    }

}
