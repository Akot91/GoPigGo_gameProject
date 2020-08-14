package game.gameObjects;

import game.Anim;
import gameMap.MapCoordinates;
import game.Directions;
import game.GameStatus;
import gameMap.MapObjectList;
import game.gameObjects.effects.DoubleParticleEffect;
import game.gameObjects.effects.Particle;
import gameFrame.Draw;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Player extends MovableObject implements ActionListener, IGlobalObjectsProperties{
    
    private Timer timer = new Timer(0, this);
    private boolean animBase = true;
    private boolean keyReleased = true;
    private int coolDownTime = -1;
    private boolean damaged = false;
    private int damageTime = -1;
   
    public Player(int x, int y){
        anim = new Anim("src/main/resources/anim/pig/start_", 1, 0);
        coord = new MapCoordinates(x, y);
        xx = coord.getX();
        yy = coord.getY();
        tmpLocation = new MapCoordinates(x, y);
        hitBox = new Rectangle(xx, yy, TILE_SIZE, TILE_SIZE);
        visible = true;
        timer.start();
        Draw.addAnim(this);
    }
    
    public void updateCoordinates(int x, int y){
        coord = new MapCoordinates(x, y);
        xx = coord.getX();
        yy = coord.getY();
        tmpLocation = new MapCoordinates(x, y);
        hitBox = new Rectangle(xx, yy, TILE_SIZE, TILE_SIZE);
    }
    
    private void enemyCollision(){
        if(coolDownTime <= 0 && GameStatus.getNumberOfFruits() > 0){
            for(Enemy en: MapObjectList.getEnemyList()){
                if(hitBox.getBounds().intersects(en.getHitBox().getBounds()) && !en.isDead()){
                    DoubleParticleEffect ef = new DoubleParticleEffect(coord.getX() + TILE_SIZE / 2, coord.getY() + TILE_SIZE / 2, "2");
                    MapObjectList.addSingleCollectEffect(new Particle(coord.getX() - 8, coord.getY() + TILE_SIZE / 2, 0.2d, Directions.NORTH, "ef_6", 180));
                    animSet = false;
                    animBase = true;
                    damageTime = PLAYER_DAMAGED_TIME;
                    coolDownTime = PLAYER_COOLDOWN;
                    tmpMove = MAX_MOVE; 
                    direction = Directions.NONE;
                    GameStatus.decreaseNumberOfLives();
                    loadLocation();
                }
            }
        }
    }
    
    private void wallCollision(){
        for(Wall wl: MapObjectList.getWallList()){
            if(hitBox.getBounds().intersects(wl.getHitBox().getBounds())){         
               tmpMove = MAX_MOVE; 
               direction = Directions.NONE;
               loadLocation();
            }
        }
    }
    
    private void blockCollision(){
        for(Block bl: MapObjectList.getBlockList()){
            if(hitBox.getBounds().intersects(bl.getHitBox().getBounds()) && bl.isSolid()){              
               tmpMove = MAX_MOVE; 
               direction = Directions.NONE;
               loadLocation();
            }
        }
    }
    
    public void keyReleased(){
        keyReleased = true;
    }
    
    public void keyPressed(){
        keyReleased = false;       
    }
    
    private boolean isDamaged(){
        return damaged;
    }
    
    private void chooseAnim(){
        if(!isDamaged()){
            if(move){
                if(!animSet){
                    if (direction == Directions.NORTH){
                        anim = new Anim("src/main/resources/anim/pig/move_up_", 4, 200);
                    } else if (direction == Directions.SOUTH){
                        anim = new Anim("src/main/resources/anim/pig/move_down_", 4, 200);
                    } else if (direction == Directions.EAST){
                        anim = new Anim("src/main/resources/anim/pig/move_right_", 4, 200);
                    } else if (direction == Directions.WEST){
                        anim = new Anim("src/main/resources/anim/pig/move_left_", 4, 200);
                    } 
                    animSet = true;
                    animBase = true;
                }
            } else {
                if(keyReleased){
                    if(animBase){
                        anim = new Anim("src/main/resources/anim/pig/nic_", 2, 200);
                        animBase = false;
                    }
                    animSet = false;
                }
            }
        } else {
            if(!animSet)
            {
                anim = new Anim("src/main/resources/anim/pig/move_damage_", 3, 200);
                animSet = true;
            }
            if(damageTime < 0){
                anim = new Anim("src/main/resources/anim/pig/nic_", 2, 200);
                damaged = false;
                animSet = false;
            }
        }
    }
    
    private void damaged(){
        if(coolDownTime >= 0)
        {
            damageTime -= 1;
            coolDownTime -= 1;
                       
            if(damageTime > 0){
                damaged = true;
            } 
        }
    }
    
    public void setPlayerDead(){
        visible = false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (GameStatus.isGameInterrupted())
        {
            damaged();
            
            if(!isDamaged()){
                blockCollision();
                wallCollision();
                enemyCollision();

                if(isMoving())
                    move();
                else 
                    moveNONE();

                windowTransition();
                endMove();
            }
            
            chooseAnim();
            
        }
        if(GameStatus.isGameEnd())
            setPlayerDead();
    }
}
