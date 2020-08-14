package gameFrame.gameLevel;

import game.Directions;
import gameFrame.IGlobalProperties;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawEndMap implements IGlobalProperties {

    private List<DarkRectangle> rectangles = new ArrayList<>();
    private Component cmpt;
    private Graphics g;
    private int numberOfRectangles;
    private int tmp = 0;
    private int rectangleWidth = (int)(SCREEN_WIDTH / 4);

    private ImageIcon HUDEndCharacter = new ImageIcon("src/main/resources/backgrounds/winner.png");

    public DrawEndMap(Component cmpt){
        this.cmpt = cmpt;
        createRectangles();
    }

    public void setGraphicsG(Graphics g){
        this.g = g;
        for(DarkRectangle dr : rectangles){
            dr.setGraphicsG(g);
        }
    }

    private void createRectangles(){
        rectangles.add(new DarkRectangle(cmpt, Directions.SOUTH, 0, 0, rectangleWidth));
        rectangles.add(new DarkRectangle(cmpt, Directions.EAST, 0, 275, rectangleWidth));
        rectangles.add(new DarkRectangle(cmpt, Directions.NORTH, 480, SCREEN_HEIGHT, rectangleWidth));
        rectangles.add(new DarkRectangle(cmpt, Directions.WEST, SCREEN_WIDTH, 115, rectangleWidth));

        numberOfRectangles = rectangles.size() - 1;
    }

    public void start() {
        for (DarkRectangle dr : rectangles) {
            dr.drawRectangle();
        }

        HUDEndCharacter.paintIcon(cmpt, g, 247, 187);

        rectangles.get(tmp).setStart();

        if(rectangles.get(tmp).isEnd() && tmp < numberOfRectangles){
            tmp += 1;
        }
    }
}
