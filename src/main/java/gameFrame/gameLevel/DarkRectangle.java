package gameFrame.gameLevel;

import game.Directions;
import gameFrame.IGlobalProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DarkRectangle implements ActionListener, IGlobalProperties {

    private Directions direction;
    private int x, y;
    private Graphics g;
    private Component cmpt;

    private int width;
    private final int maxLength = SCREEN_WIDTH;
    private int tmpLength = 0;

    private boolean start = false;
    private boolean end = false;

    private Timer timer = new Timer (0, this);

    public DarkRectangle(Component cmpt, Directions dir, int x, int y, int width){
        this.cmpt = cmpt;
        this.g = g;
        this.direction = dir;
        this.width = width;
        this.x = x;
        this.y = y;
    }

    public void setGraphicsG(Graphics g){
        this.g = g;
    }

    public void setStart(){
        start = true;
    }

    public boolean isEnd(){
        return end;
    }

    public void drawRectangle(){
        g.setColor(Color.BLACK);
        if(start) {
            if (tmpLength <= maxLength) {
                tmpLength += 20;
            } else {
                end = true;
                start = false;
                timer.stop();
            }
        }

        if (direction == Directions.SOUTH)
            g.fillRect(x, y, width, tmpLength);
        else if (direction == Directions.NORTH)
            g.fillRect(x, y - tmpLength, width, tmpLength);
        else if (direction == Directions.EAST)
            g.fillRect(x, y, tmpLength, width);
        else if (direction == Directions.WEST)
            g.fillRect(x - tmpLength, y, tmpLength, width);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        drawRectangle();
    }
}
