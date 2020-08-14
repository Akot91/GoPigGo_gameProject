package game.gameObjects;

import game.AI;
import game.Anim;
import gameMap.MapCoordinates;
import game.Directions;
import game.GameStatus;
import gameMap.MapObjectList;
import game.gameObjects.effects.Particle;
import game.gameObjects.effects.SingleParticleEffect;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Enemy extends MovableObject implements ActionListener, IGlobalObjectsProperties{

    private Timer timer = new Timer(0, this);    
    private AI ai = new AI(this);
    private EnemyType enemyType;
    private int deadTime = 0;
    private boolean dead = false;
    
    private Directions tmpDirection;
        
    public Enemy(int x, int y, EnemyType enemyType){
        anim = new Anim(enemyType.getSource() + "_left_", 1, 0);
        coord = new MapCoordinates(x, y);
        xx = coord.getX();
        yy = coord.getY();
        hitBox = new Rectangle(xx, yy, TILE_SIZE, TILE_SIZE);
        this.enemyType = enemyType;
        visible = true;
        timer.start();
    }      
    
    private void blockCollision(){
        for(Block bl: MapObjectList.getBlockList()){
            if(!dead){
                if(hitBox.getBounds().intersects(bl.getHitBox().getBounds())){
                    if(bl.isMoving()){
                        bl.destroy();                  
                        deadTime = ENEMY_RESPAWN_TIME;
                        SingleParticleEffect ef = new SingleParticleEffect(coord.getX() + TILE_SIZE / 2, coord.getY() + TILE_SIZE / 2, "2"); 
                        visible = false;
                        animSet = false;
                        GameStatus.addPoints(200);
                        MapObjectList.addSingleCollectEffect(new Particle(coord.getX(), coord.getY() + TILE_SIZE / 2, 0.2d, Directions.NORTH, "ef_4", 90));
                    } else {
                        if(deadTime < ENEMY_RESPAWN_VIEW_TIME / 2)
                          bl.setDelete();
                    }
                }
            }
        }
    } 
    
    public boolean isDead(){
        return dead;
    }

    public void selfDestory(){
        MapObjectList.removeEnemy(this);
        timer.stop();
    }
    
    private void enemyRest(){
        
        if(deadTime > 0){
            dead = true;
            direction = Directions.NONE;
            deadTime -= 1;
        
            if(deadTime < ENEMY_RESPAWN_VIEW_TIME){
                if(!animSet){
                    visible = true;
                    anim = new Anim(enemyType.getSource() + "_rest_", 2, 200);
                    animSet = true;
                    loadLocation();
                }
            }
        }
        else {
            dead = false;
        }
        
    }
    
    private void chooseAnim(){
        
        if(!isDead()){
            if(direction != tmpDirection){
                if (direction == Directions.NORTH){
                    anim = new Anim(enemyType.getSource() + "_up_", 4, 200);
                } else if (direction == Directions.SOUTH){
                    anim = new Anim(enemyType.getSource() + "_down_", 4, 200);
                } else if (direction == Directions.EAST){
                    anim = new Anim(enemyType.getSource() + "_right_", 4, 200);
                } else if (direction == Directions.WEST){
                    anim = new Anim(enemyType.getSource() + "_left_", 4, 200);
                } else if (direction == Directions.NONE){
                    anim = new Anim(enemyType.getSource() + "_left_", 4, 200);
                }
                tmpDirection = direction;
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (GameStatus.isGameInterrupted())
        {             
            blockCollision();
            
            if (!isDead()){
               
                chooseAnim();
                move(); 

                if(tmpMove >= MAX_MOVE){
                    ai.resetWallNWSE();
                    direction = ai.randomChooseDirection();
                }
                endMove();
            }
            
            enemyRest();
        }
    }
      
}
