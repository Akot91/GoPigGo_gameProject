package game;

import game.gameObjects.Block;
import game.gameObjects.Enemy;
import game.gameObjects.Wall;
import gameFrame.IGlobalProperties;
import gameMap.MapObjectList;

import java.util.Random;

public class AI implements IGlobalProperties {
    
    private Enemy en;
    private int wallNWSE = 0b0000;
    private Random rand = new Random();
    private int tmp;
    
    public AI(Enemy en){
        this.en = en;
    }
    
    public void resetWallNWSE(){
        wallNWSE = 0b0000;
    }
    
    private void checkWalls(){
        
        int tmpX = en.getCoords().getX();
        int tmpY = en.getCoords().getY();
        
        for(Wall wl: MapObjectList.getWallList()){
            
            int tmpWlX = wl.getCoords().getX();
            int tmpWlY = wl.getCoords().getY();
            
            if(((tmpX - TILE_SIZE) == tmpWlX) && (tmpY == tmpWlY))
                wallNWSE |= 0b0001;
            
            if(((tmpX + TILE_SIZE) == tmpWlX) && (tmpY == tmpWlY))
                wallNWSE |= 0b0100;
            
            if((tmpX == tmpWlX) && ((tmpY - TILE_SIZE) == tmpWlY))
                wallNWSE |= 0b1000;
            
            if((tmpX == tmpWlX) && ((tmpY + TILE_SIZE) == tmpWlY))
                wallNWSE |= 0b0010;       
        }            
        
        for(Block bl: MapObjectList.getBlockList()){
            
            int tmpBlX = bl.getCoords().getX();
            int tmpBlY = bl.getCoords().getY();
            
            if(((tmpX - TILE_SIZE) == tmpBlX) && (tmpY == tmpBlY))
                wallNWSE |= 0b0001;
            
            if(((tmpX + TILE_SIZE) == tmpBlX) && (tmpY == tmpBlY))
                wallNWSE |= 0b0100;
            
            if((tmpX == tmpBlX) && ((tmpY - TILE_SIZE) == tmpBlY))
                wallNWSE |= 0b1000;
            
            if((tmpX == tmpBlX) && ((tmpY + TILE_SIZE) == tmpBlY))
                wallNWSE |= 0b0010;       
        }    
    }
    
    public Directions randomChooseDirection(){
        
        Directions direction = en.getDirection();
        
        checkWalls();
        
        switch (wallNWSE) {
            case 13:
                return Directions.SOUTH;             
            case 11:
                return  Directions.EAST;
            case 14:
                return Directions.WEST;
            case 7:
                return Directions.NORTH;
            case 9:
                if(direction == Directions.NORTH || direction == Directions.NONE)
                    return Directions.EAST;
                else if(direction == Directions.WEST)
                    return Directions.SOUTH;
            case 12:
                if(direction == Directions.NORTH || direction == Directions.NONE)
                    return Directions.WEST;
                else if(direction ==  Directions.EAST)
                    return Directions.SOUTH;
            case 3:
                if(direction == Directions.SOUTH || direction == Directions.NONE)
                    return Directions.EAST;
                else if(direction == Directions.WEST)
                    return Directions.NORTH;
            case 6:
                if(direction ==  Directions.EAST || direction == Directions.NONE)
                    return Directions.NORTH;
                else if(direction == Directions.SOUTH)
                    return Directions.WEST;
            case 10:
                if(direction ==  Directions.EAST)
                    return  Directions.EAST;
                else if(direction == Directions.WEST)
                    return Directions.WEST;
                else if(direction == Directions.NONE)
                    return Directions.EAST;
            case 5:
                if(direction == Directions.NORTH)
                    return Directions.NORTH;
                else if(direction == Directions.SOUTH)
                    return Directions.SOUTH;
                else if(direction == Directions.NONE)
                    return Directions.NORTH;
            case 0:
                tmp = rand.nextInt(3);
                if(direction == Directions.NORTH){
                    switch (tmp) {
                        case 0:
                            return Directions.WEST;
                        case 1:
                            return Directions.EAST;
                        case 2:
                            return Directions.NORTH;
                        default:
                    }
                } else if (direction == Directions.EAST) {
                    switch (tmp) {
                        case 0:
                            return Directions.EAST;
                        case 1:
                            return Directions.NORTH;
                        case 2:
                            return Directions.SOUTH;
                        default:
                    }
                } else if (direction == Directions.SOUTH) {
                    switch (tmp) {
                        case 0:
                            return Directions.WEST;
                        case 1:
                            return Directions.SOUTH;
                        case 2:
                            return Directions.EAST;
                        default:
                    }
                } else {
                    switch (tmp) {
                        case 0:
                            return Directions.WEST;
                        case 1:
                            return Directions.NORTH;
                        case 2:
                            return Directions.SOUTH;
                        default:
                    }
                }
            case 15:
                return  Directions.NONE;
            case 1:
                tmp = rand.nextInt(3);
                switch (tmp) {
                    case 0:
                        return Directions.EAST;
                    case 1:
                        return Directions.SOUTH;
                    case 2:
                        return Directions.NORTH;
                    default:
                }
            case 2:
                tmp = rand.nextInt(3);
                switch (tmp) {
                    case 0:
                        return Directions.WEST;
                    case 1:
                        return Directions.EAST;
                    case 2:
                        return Directions.NORTH;
                    default:
                }
            case 4:
                tmp = rand.nextInt(3);
                switch (tmp) {
                    case 0:
                        return Directions.WEST;
                    case 1:
                        return Directions.SOUTH;
                    case 2:
                        return Directions.NORTH;
                    default:
                }
            case 8:
                tmp = rand.nextInt(3);
                switch (tmp) {
                    case 0:
                        return Directions.WEST;
                    case 1:
                        return Directions.SOUTH;
                    case 2:
                        return Directions.EAST;
                    default:
                }
            default:
                return  Directions.NONE;
        }        
    }
}
