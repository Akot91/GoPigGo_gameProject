package game.gameObjects;

import gameMap.MapCoordinates;
import game.Directions;
import gameFrame.IGlobalProperties;

public abstract class MovableObject extends GameObject implements IGlobalProperties{
    
    protected boolean move = false;
    protected Directions direction = Directions.NONE;
    protected MapCoordinates tmpLocation;
    protected final int MAX_MOVE = 32;
    protected int tmpMove = 0;
    protected int xx, yy;
    protected boolean transit = false;
    protected boolean animSet = false;
    
    public void moveRight(){
        direction = Directions.EAST;
        move = true;
    }
    
    public void moveLeft(){
        direction = Directions.WEST;
        move = true;
    }
    
    public void moveUp(){
        direction = Directions.NORTH;
        move = true;
    }
    
    public void moveDown(){
        direction = Directions.SOUTH;
        move = true;
    }
    
    public void moveNONE(){
        direction = Directions.NONE;
        move = false;
    }
    
    public boolean isMoving(){
        return move;
    }
    
    protected void loadLocation(){
        xx = tmpLocation.getX();
        yy = tmpLocation.getY();
    }
    
    public Directions getDirection(){
        return direction;
    }
        
    protected void move(){          
        if(tmpMove < MAX_MOVE){
            switch (direction) {
                case EAST:
                    xx += 1d;                        
                    break;
                case WEST:
                    xx -= 1d;    
                    break;
                case NORTH:
                    yy -= 1d;                       
                    break;
                case SOUTH:
                    yy += 1d;
                    break;
                default:
                    break;
            }
        }

        coord.setX((int)xx);
        coord.setY((int)yy);

        tmpMove += 1;

        hitBox.setLocation(coord.getX(), coord.getY());                   
    }
    
    protected void endMove(){
        
        if(tmpMove >= MAX_MOVE){
            move = false;
            tmpMove = 0;
            tmpLocation = new MapCoordinates(0, 0);
            tmpLocation.setX(xx);
            tmpLocation.setY(yy);
        }
    }
    
    protected void windowTransition(){
        
        int tmpX = coord.getX();
        int tmpY = coord.getY();
        
        if(tmpX < -1 *(TILE_SIZE - 1) && move){
            xx = SCREEN_WIDTH;
            hitBox.setLocation(xx, yy);
            move = true;
            tmpMove = 0;
        } else if(tmpX >= SCREEN_WIDTH && move){
            xx = -1 *(TILE_SIZE - 1);
            hitBox.setLocation(xx, yy);
            move = true;
            tmpMove = 0;   
        } else if(tmpY < -1 *(TILE_SIZE - 1) && move){
            yy = SCREEN_HEIGHT - 32;
            hitBox.setLocation(xx, yy);
            move = true;
            tmpMove = 0;
        } else if(tmpY >= (SCREEN_HEIGHT - 32) && move){
            yy = -1 * TILE_SIZE;
            hitBox.setLocation(xx, yy);
            move = true;
            tmpMove = 0;
        }
    }
}
