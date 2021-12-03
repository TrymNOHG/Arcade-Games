package Sudoku;

import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GridSquare {
    private int mouseX;
    private int mouseY;
    private double xCoord;
    private double yCoord;
    private double dimOfBox;
    private Rectangle2D.Double rect;

    public GridSquare(double xCoord, double yCoord, double dimOfBox) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.dimOfBox = dimOfBox;
        this.rect = createRect();
    }

    public Rectangle2D.Double createRect(){
            return new Rectangle2D.Double(xCoord, yCoord, dimOfBox, dimOfBox);
    }

    public Rectangle2D.Double getRect() {
        return rect;
    }
}
