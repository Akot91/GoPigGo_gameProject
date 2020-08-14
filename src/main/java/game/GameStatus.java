package game;

import game.gameObjects.IGlobalObjectsProperties;
import game.gameObjects.RandomBlockDelete;
import gameFrame.Draw;
import gameFrame.IGlobalProperties;
import gameMap.gameLevel.Level_1;
import gameMap.MapGenerator;
import gameMap.MapStart;
import gameMap.MapObjectList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GameStatus implements ActionListener, IGlobalObjectsProperties, IGlobalProperties {
      
    private static RandomBlockDelete blockDelete = new RandomBlockDelete();;
    private static MapStart mapStart;
    
    private static int numberOfFruits = 0;
    private static double points = 0d;
    private static int lives = 3;
    private int lvlNumber = 1;

    private Timer timer = new Timer(0, this);
    
    private static int timeStart = GAME_START_TIME;
    private static int timeEnd = GAME_END_TIME;
    private static int timeChange = 600;
    private static boolean gameStart = false;
    private static boolean mapEnd = false;
    private static boolean gameEnd = false;
    private static boolean gamePause = false;
    
    public GameStatus(){ timer.start(); }

    public static void mapStart(){
        Draw.changeGameView(10);
        MapObjectList.clearAll();
        MapGenerator.fillObjectList(new Level_1());
    }

    public static void mapEnd(){
        Draw.changeGameView(10);
        MapObjectList.clearAll();
    }

    public static void loadEnd(){
        Draw.changeGameView(2);
        mapStart = new MapStart();
    }
    
    public static void addFruit(){ numberOfFruits += 1; }
    
    public static int getNumberOfFruits(){ return numberOfFruits; }
    
    public static void decreaseNumberOfFruits(){ numberOfFruits -= 1; }
    
    public static void decreaseNumberOfLives(){ lives -= 1; }
    
    public static int getNumberOfLives(){ return lives; }
    
    public static void addNumberOfLives(int x){ lives += x; }
    
    public static void addPoints(int x){ points += x; }
    
    public static double getPoints(){ return points; }
    
    public static boolean isGameStart(){ return gameStart; }
    
    public static void setPause(boolean pause){ gamePause = pause; }
    
    public static boolean isGamePaused(){ return gamePause; }
    
    public static boolean isGameEnd(){ return gameEnd; }

    public static boolean isMapEnd() { return mapEnd; }
    
    public static boolean isGameInterrupted(){
        if (isGameStart() && !isGameEnd() && !isGamePaused()){
            return true;
        } else {
            return false;
        }
    }
    
    public static void startGame(){
        if(!gameStart){
            timeStart -= 1;
        } 
        if(timeStart <=0 )
            gameStart = true;
    }
    
    private void checkGameStatus(){
        if(numberOfFruits <= 0 && gameStart)
            timeEnd -= 1;

        if(timeEnd < 0) {
            gameStart = false;
            mapEnd = true;
        }
        if(mapEnd){
            timeChange -= 1;
            if (timeChange <= 0)
                mapEnd();
        }
        
        if(lives <=0 )
            gameEnd = true;
    }

    public static void resetGame(){
        numberOfFruits = 0;
        points = 0d;
        lives = 3;
        resetGameStatus();
    }

    public static void resetGameStatus(){
        timeStart = GAME_START_TIME;
        timeEnd = GAME_END_TIME;
        gameStart = false;
        mapEnd = false;
        gameEnd = false;
        gamePause = false;
        timeChange = 600;
    }

    @Override
    public void actionPerformed(ActionEvent ae) { checkGameStatus(); }
}
