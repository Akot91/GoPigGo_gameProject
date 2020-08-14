package gameMap;

import game.Calculate;

public class MapCoordinates {
    
    private int x, y;
    
    public MapCoordinates(int x, int y){
        this.x = Calculate.calculateXAlignment(x);
        this.y = Calculate.calculateYAlignment(y);
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
}
