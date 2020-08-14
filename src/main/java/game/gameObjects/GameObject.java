package game.gameObjects;

import java.awt.Rectangle;
import gameFrame.IGlobalProperties;

public abstract class GameObject extends ObjectBasics implements IGlobalProperties{
    
    protected Rectangle hitBox;
        
    public Rectangle getHitBox(){
        return hitBox;
    }
    
}
