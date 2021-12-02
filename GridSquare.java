import javax.swing.*;
import java.awt.*;

public class GridSquare {
    private int mouseX;
    private int mouseY;
    private double xCoord;
    private double yCoord;
    private double dimBox;
    private JFrame sudokuWindow;

    public GridSquare(Point mouseLoc, double xCoord, double yCoord, double dimBox, JFrame sudokuWindow) {
        this.mouseX = mouseLoc.x;
        this.mouseY = mouseLoc.y;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.dimBox = dimBox;
        this.sudokuWindow = sudokuWindow;
    }
}
