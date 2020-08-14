package game.gameObjects;

import game.Anim;
import gameMap.MapCoordinates;
import game.Directions;
import game.GameStatus;
import gameMap.MapObjectList;
import game.gameObjects.effects.DoubleParticleEffect;
import game.gameObjects.effects.Particle;
import game.gameObjects.effects.SingleParticleEffect;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Element extends GameObject implements ActionListener{
        
    private Timer timer = new Timer(0, this);
    private int x, y;
    private FruitType type;
    private boolean extra = false;
    private RandomExtraFruit rEF = new RandomExtraFruit();
    
    public Element(int x, int y, FruitType type){
        this.type = type;
        anim = new Anim(type.getSource(), 1, 0);
        this.x = x;
        this.y = y;
        coord = new MapCoordinates(x, y);
        hitBox = new Rectangle(coord.getX(), coord.getY(), TILE_SIZE, TILE_SIZE);  
        timer.start();
    }
    
    public void setExtra(){
        extra = true;
        anim = new Anim(type.getSource(), 2, 200);
    }
    
    private void playerCollision(){
        
        if(hitBox.getBounds().intersects(MapObjectList.getPlayer().getHitBox().getBounds()))
        {
            GameStatus.decreaseNumberOfFruits();
            if(!extra){
                GameStatus.addPoints(100);
                SingleParticleEffect ef = new SingleParticleEffect(coord.getX() + TILE_SIZE / 2, coord.getY() + TILE_SIZE / 2, "1"); 
                MapObjectList.addSingleCollectEffect(new Particle(coord.getX(), coord.getY() + TILE_SIZE / 2, 0.2d, Directions.NORTH, "ef_3", 90));
                destroy();
                MapObjectList.removeElement(this);
            } else {
                GameStatus.addPoints(500);
                DoubleParticleEffect ef = new DoubleParticleEffect(coord.getX() + TILE_SIZE / 2, coord.getY() + TILE_SIZE / 2, "1"); 
                MapObjectList.addSingleCollectEffect(new Particle(coord.getX(), coord.getY() + TILE_SIZE / 2, 0.2d, Directions.NORTH, "ef_5", 90));
                destroy();
                MapObjectList.removeElement(this);
                rEF.setExtraFruit();
            }
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (GameStatus.isGameInterrupted()){
            playerCollision();
        }
    }
}
