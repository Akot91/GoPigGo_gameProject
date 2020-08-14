package gameMap;

import game.GameStatus;
import game.gameObjects.Block;
import game.gameObjects.RandomExtraFruit;
import game.gameObjects.Wall;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class MapStart implements ActionListener{
    
    private boolean fruitCreated = false;
    private boolean blockCreated = false;
    private boolean wallCreated = false;
    private int timeCreate = 10;   
    private int fruitNumber = 0;
    private RandomExtraFruit rEF = new RandomExtraFruit();
    
    private Timer timer = new Timer(0, this);
    
    public MapStart(){
        timer.start();
    }
    
    private void createMap(){
        if(!fruitCreated){
            timeCreate -= 1;
            if(timeCreate <= 0){
                if(fruitNumber < GameStatus.getNumberOfFruits()){
                    MapObjectList.getElement(fruitNumber).setVisible();
                    fruitNumber += 1;
                    timeCreate = 10;
                } else {
                    fruitCreated = true;  
                    timeCreate = 80;
                }
            } 
        } else {
            if(!blockCreated){
                timeCreate -= 1;
                if(timeCreate <= 0){
                    for(Block bl: MapObjectList.getBlockList()){
                        bl.setVisible();
                    }
                    timeCreate = 80;
                    blockCreated = true;
                }                   
            } else {
                timeCreate -= 1;
                if(timeCreate <= 0){
                    for(Wall wl: MapObjectList.getWallList()){
                        wl.setVisible();
                    }
                      wallCreated = true;
                } 
            }       
        }   
        
        if(wallCreated){
            GameStatus.startGame();
        }
        
        if(GameStatus.isGameStart()){
            rEF.setExtraFruit();
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        createMap();
    }
}
