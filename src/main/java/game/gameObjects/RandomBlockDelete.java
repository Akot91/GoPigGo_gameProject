package game.gameObjects;

import game.GameStatus;
import gameMap.MapObjectList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

public class RandomBlockDelete implements ActionListener, IGlobalObjectsProperties{
    
    private Timer timer = new Timer(0, this);
    private static int blockDeleteTime = BLOCK_DELETE_TIME;
    
    public RandomBlockDelete(){
        timer.start();
    }

    private void randomBlockDelete() {
        if (blockDeleteTime >= 0)
            blockDeleteTime -= 1;
        else {
            int numberOfBlocks = MapObjectList.getBlockList().size();
            if (numberOfBlocks > 0) {
                Random rand = new Random();
                MapObjectList.getSingleBlock(rand.nextInt(numberOfBlocks)).setDelete();
                blockDeleteTime = BLOCK_DELETE_TIME;
            } else {
                timer.stop();
            }
        }
    }

    public void stopRandom(){
        timer.stop();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (GameStatus.isGameInterrupted())
            randomBlockDelete();
        else
            blockDeleteTime = BLOCK_DELETE_TIME;
    }
}
