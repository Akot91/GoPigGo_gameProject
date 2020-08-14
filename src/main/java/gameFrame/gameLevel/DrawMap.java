package gameFrame.gameLevel;

import game.Calculate;
import game.GameStatus;
import game.gameObjects.ObjectBasics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.List;
import javax.swing.ImageIcon;

public class DrawMap{
    
    private ImageIcon gameHUD = new ImageIcon("src/main/resources/backgrounds/hud.png");
    private ImageIcon HUDlife = new ImageIcon("src/main/resources/hud/hud_life.png");
    private ImageIcon hudBar;
    private ImageIcon gameBacground;
    private ImageIcon HUDPause = new ImageIcon("src/main/resources/backgrounds/bcg_pause_0.png");
    private ImageIcon HUDBorders = new ImageIcon("src/main/resources/hud/hud_borders.png");

    private Component cmpt;
    private Graphics g;

    private DrawEndMap dem;
    
    public DrawMap(Component cmpt, ImageIcon gameBacground, ImageIcon hudBar){
        this.cmpt = cmpt;
        this.gameBacground = gameBacground;
        this.hudBar = hudBar;
        dem = new DrawEndMap(cmpt);
    }
    
    public void setGraphicsG(Graphics g){ this.g = g; }
    
    public void drawMap( List<ObjectBasics> animList){
        
        drawObjects(animList);
        dem.setGraphicsG(g);
        if (GameStatus.isMapEnd())
            dem.start();
        drawHUD();

    }
    
    private void drawObjects(List<ObjectBasics> list){
        gameBacground.paintIcon(cmpt, g, Calculate.calculateBackgroundXAligment(0), Calculate.calculateYAlignment(0));
        
        for(ObjectBasics anim: list){   
            if (anim.isVisible()){
                int x = anim.getCoords().getX();
                int y = anim.getCoords().getY();
                anim.getAnim().getImage().paintIcon(cmpt, g, x, y);
            }
        }
    }
    
    private void drawHUD(){
        gameHUD.paintIcon(cmpt, g, 0, 0);

        HUDBorders.paintIcon(cmpt, g, 0, 115);
        
        g.setColor(Color.white);
        g.drawString(Integer.toString((int)GameStatus.getPoints()), 450, 49);
        
        hudBar.paintIcon(cmpt, g, 38, 75);
        
        g.drawString(Integer.toString((int)GameStatus.getPoints()), 450, 49);
        
        for(int i = 0; i < GameStatus.getNumberOfLives(); i++){
            HUDlife.paintIcon(cmpt, g, (14 * i) + 39, 34);
        }

        if(GameStatus.isGamePaused())
            HUDPause.paintIcon(cmpt, g, Calculate.calculateBackgroundXAligment(0), Calculate.calculateYAlignment(0));
    }

}
