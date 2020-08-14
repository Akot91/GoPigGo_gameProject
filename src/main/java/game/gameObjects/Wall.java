package game.gameObjects;

import game.Anim;
import gameMap.MapCoordinates;
import java.awt.Rectangle;

public class Wall extends GameObject{
    
    public Wall(int x, int y, WallType type){
        anim = new Anim(type.getSource(), 1, 0);
        coord = new MapCoordinates(x, y);
        hitBox = new Rectangle(coord.getX(), coord.getY(), TILE_SIZE, TILE_SIZE);    
    }
}
