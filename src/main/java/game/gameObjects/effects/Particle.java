package game.gameObjects.effects;

import game.Anim;
import gameMap.MapCoordinates;
import game.Directions;
import game.GameStatus;
import gameMap.MapObjectList;
import game.gameObjects.ObjectBasics;
import gameFrame.Draw;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Particle extends ObjectBasics implements ActionListener{
    
    private Timer timer = new Timer(0, this);
    private int liveTime = 60;
    private Directions dir;
    private double x, y, speed;
    
    public Particle(double x, double y, double speed, Directions dir, String type){
        anim = new Anim("src/main/resources/anim/effects/" + type + "_", 4, 200);
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.dir = dir;
        coord = new MapCoordinates((int)x, (int)y);
        visible = true;
        timer.start();
        Draw.addAnim(this);
    }
    
    public Particle(double x, double y, double speed, Directions dir, String type, int liveTime){
        anim = new Anim("src/main/resources/anim/effects/" + type + "_", 4, 200);
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.dir = dir;
        this.liveTime = liveTime;
        coord = new MapCoordinates((int)x, (int)y);
        visible = true;
        timer.start();
        Draw.addAnim(this);
    }
    
    private void effectMove(){
         
            switch (dir) {
                case EAST:
                    x += speed + 0.2d;                        
                    break;
                case WEST:
                    x -= speed + 0.2d;    
                    break;
                case NORTH:
                    y -= speed + 0.2d;                       
                    break;
                case SOUTH:
                    y += speed + 0.2d;
                    break;
                case NORTHEAST:
                    x += speed;
                    y -= speed;
                    break;
                case SOUTHEAST:
                    x += speed;
                    y += speed;
                    break;
                case SOUTHWEST:
                    x -= speed;
                    y += speed;
                    break;
                case NORTHWEST:
                    x -= speed;
                    y -= speed;
                    break;
                default:
                    break;
            }
            
        coord.setX((int)x);
        coord.setY((int)y);        
        
    }
    
    private void timer(){
        liveTime -= 1;
        
        if(liveTime <= 0){
            MapObjectList.removeSingleCollectEffect(this);
            timer.stop();
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(!GameStatus.isGamePaused()){
            timer();
            effectMove();
        }
    }
}
