package game.gameObjects;

import game.Anim;
import gameMap.MapCoordinates;

public abstract class ObjectBasics {
    
    protected MapCoordinates coord;
    protected Anim anim;
    protected boolean visible = false;
    protected boolean destroy = false;
    
    public Anim getAnim(){
        return anim;
    }
    
    public MapCoordinates getCoords(){
        return coord;
    }
    
    public void destroy(){
        destroy = true;
    }
    
    public boolean isDestroyed(){
        return destroy;
    }
    
    public void setVisible(){
        visible = true;
    }
    
    public boolean isVisible(){
        return visible;
    }
    
    public void setUnvisible(){
        visible = false;
    }
}
