package game.gameObjects;

import game.Anim;
import gameMap.MapCoordinates;
import game.Directions;
import game.GameStatus;
import gameMap.MapObjectList;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Block extends MovableObject implements ActionListener{
    
    private Timer timer = new Timer(0, this);
    private boolean solid = false;
    private BlockType type;
    private boolean deleted = false;
    
    public Block(int x, int y, BlockType type){
        this.type = type;
        anim = new Anim(type.getSource(), 1, 0);
        coord = new MapCoordinates(x, y);
        xx = coord.getX();
        yy = coord.getY();
        hitBox = new Rectangle(xx, yy, TILE_SIZE, TILE_SIZE);
        tmpLocation = new MapCoordinates(xx, yy);
        timer.start();
    }           
    
    private void moveSwitch(Directions dir){
        
        if(MapObjectList.getPlayer().isMoving()){
            switch (dir) {
                case EAST:    
                    moveRight();
                    break;
                case SOUTH:
                    moveDown();
                    break;
                case WEST:
                    moveLeft();
                    break;
                case NORTH:
                    moveUp();
                    break;
                default:
                    break;      
            }
        }
    }
    
    public boolean isSolid(){
        return solid;
    }
    
    public boolean isDeleted(){
        return deleted;
    }
    
    private void setNotMovable(){
        solid = true;
        tmpMove = MAX_MOVE; 
        direction = Directions.NONE;
        loadLocation();
    }
    
    private void playerCollision(){

        if(hitBox.getBounds().intersects(MapObjectList.getPlayer().getHitBox().getBounds()) && !isDeleted()){
            Directions dir = MapObjectList.getPlayer().getDirection();
            moveSwitch(dir);
        } 
    }
    
    private void blockCollision(){
        for(Block bl: MapObjectList.getBlockList()){
            if(hitBox.getBounds().intersects(bl.getHitBox().getBounds()) && !bl.isDeleted())
            {       
                if(bl.isSolid()){
                    setNotMovable();                    
                } else {                    
                    Directions dir = bl.getDirection();
                    moveSwitch(dir);
                }
            } 
        }
    }
    
    private void wallCollision(){
        for(Wall wl: MapObjectList.getWallList()){
            if(hitBox.getBounds().intersects(wl.getHitBox().getBounds())){              
                setNotMovable();
            } else {
               if(!MapObjectList.getPlayer().isMoving())
                    solid = false;
            }
        }
    }
    
    private void fruitCollision(){
        for(Element el: MapObjectList.getElementList()){
            if(hitBox.getBounds().intersects(el.getHitBox().getBounds())){              
                setNotMovable();
            } else {
               if(!MapObjectList.getPlayer().isMoving())
                    solid = false;
            }
        }
    }

    public void selfDestroy(){
        if(isDestroyed()){
            MapObjectList.removeBlock(this);
            timer.stop();
        }              
    }  
    
    private void selfDelete(){
        if(deleted){
            if(!animSet){
                anim = new Anim(type.getSource() + "del_", 5, 200);
                animSet = true;
            }
            if(anim.isAnimationEnd())
                destroy();
        }
    }
    
    public void setDelete(){
        deleted = true;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (GameStatus.isGameInterrupted())
        {       
            wallCollision();
            fruitCollision();
            blockCollision();
            playerCollision();

            windowTransition();
            selfDestroy();
            selfDelete();

            if(isMoving())
                move();
            else moveNONE();
            
            endMove();
        }
    }
}
