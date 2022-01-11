package Sudoku;

import java.awt.*;

public class GridSquare {

    public GridSquare() {

    }

    public void createRect(Graphics2D graphics2D, Color c , int xCoord, int yCoord, int width, int height){
            graphics2D.drawRect(xCoord, yCoord, width, height);
            graphics2D.setColor(c);
            graphics2D.fillRect(xCoord, yCoord, width, height);
    }

}
