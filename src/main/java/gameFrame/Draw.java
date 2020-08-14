package gameFrame;

import game.Calculate;
import game.GameStatus;
import game.gameObjects.ObjectBasics;
import gameFrame.gameLevel.DrawMap;
import gameFrame.gameMapInfo.DrawInfo;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Draw extends JPanel implements ActionListener{

    private Timer timer = new Timer(0, this);
    private Graphics g;
    private static int time = 120;
    private boolean wait = false;

    private DrawMap dm;
    private DrawInfo di = new DrawInfo(this);

    private static List<ObjectBasics> animList = new ArrayList<ObjectBasics>();
    private static ImageIcon gameBacground, hudBar;
    private static int view = 1;
    
    public Draw(){
        timer.start();       
    }

    public static void setHudBar(ImageIcon img){
        hudBar = img;
    }
    
    public static void addAnim(ObjectBasics anim){
        animList.add(anim);
    }

    public static void clearAnimList(){ animList.clear(); }
        
    public static void removeAnim(ObjectBasics anim){
        animList.remove(anim);
    }
    
    public static void setBackground(ImageIcon image){
        gameBacground = new ImageIcon();
        gameBacground = image;
    }

    public static void changeGameView(int v){ view = v; }

    public static int getGameView(){ return view; }
    
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);

        switch (view) {
            //menu
            case 0:
                break;
            //start mapy
            case 1:
                di.setGraphicsG(g);
                di.drawMapInfo();
                break;
            //gra
            case 2:
                dm.setGraphicsG(g);
                dm.drawMap(animList);                
                break;
            default:
                g.setColor(Color.BLACK);
                g.fillRect(0,0, 640, 480);
                wait = true;
                break;   
        }
        
        try {
            Thread.sleep(Calculate.calculateFPS());
        } catch (InterruptedException ex) {        }
                
    }
        
    @Override
    public void actionPerformed(ActionEvent ae) {
        repaint();

        if(wait){
            time -= 1;
        }

        if(time <= 0 && wait){
            if(!GameStatus.isMapEnd()) {
                dm = new DrawMap(this, gameBacground, hudBar);
                GameStatus.loadEnd();
                wait = false;
                time = 120;
            } else {
                GameStatus.resetGameStatus();
                view = 1;
                wait = false;
                time = 120;
            }
        }
    }
    
}
