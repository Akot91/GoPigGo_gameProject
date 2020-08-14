package gameFrame.gameMapInfo;

import game.Anim;
import game.GameStatus;
import gameFrame.IGlobalProperties;

import javax.swing.*;
import java.awt.*;

public class DrawInfo implements IGlobalProperties{

    private Component cmpt;
    private Graphics g;

    private int mapNumber = 1;

    private ImageIcon HUDBorder = new ImageIcon("src/main/resources/backgrounds/border.png");
    private ImageIcon HUDEndCharacter = new ImageIcon("src/main/resources/backgrounds/mapChocie.png");
    private ImageIcon mapIcon;
    private Anim start = new Anim("src/main/resources/backgrounds/start_", 4, 200);
    private Anim pig = new Anim("src/main/resources/anim/pig/move_down_", 4, 150);

    public DrawInfo(Component cmpt){
        this.cmpt = cmpt;
        mapIcon = new ImageIcon("src/main/resources/backgrounds/map" + mapNumber + ".png");
    }

    public void setMapNumber(int numb){ mapNumber = numb; }

    public void setGraphicsG(Graphics g){ this.g = g; }

    public void drawMapInfo(){

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        g.setColor(Color.WHITE);

        HUDBorder.paintIcon(cmpt, g, 0, 0);
        HUDEndCharacter.paintIcon(cmpt, g, 240, 100);
        mapIcon.paintIcon(cmpt, g, 251, 109);
        start.getImage().paintIcon(cmpt, g, 270, 360);

        for(int i = 0; i < GameStatus.getNumberOfLives(); i++){
            if (i < 6){
                pig.getImage().paintIcon(cmpt, g, 80, 220 - (i * 40));
            }
        }

        g.setFont(new Font("Courier", Font.PLAIN, 18));
        g.drawString("ŻYCIA", 70, 280);
        g.drawString("PUNKTY", 470, 280);
        g.drawString(Integer.toString((int)GameStatus.getPoints()), 470, 240);
    }
}
