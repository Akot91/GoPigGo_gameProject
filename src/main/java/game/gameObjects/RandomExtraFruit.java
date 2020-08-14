package game.gameObjects;

import gameMap.MapObjectList;
import java.util.Random;

public class RandomExtraFruit {
    
    private Random rand = new Random();
    private int fruitChoice;
    
    public void setExtraFruit(){ 
        int fruitsNumb = MapObjectList.getElementList().size();
        if(fruitsNumb > 0){
           fruitChoice = rand.nextInt(fruitsNumb);
           MapObjectList.getElement(fruitChoice).setExtra();
        }
    }
}
