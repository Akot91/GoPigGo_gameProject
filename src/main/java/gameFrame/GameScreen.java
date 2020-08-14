package gameFrame;

import game.GameStatus;
import gameMap.MapObjectList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class GameScreen extends JFrame implements KeyListener, IGlobalProperties{
      
    GameStatus gameStatus =  new GameStatus();
    
    public GameScreen(){
        init();
    }
    
    private void init(){
        setLocation(200,200);
        setDefaultCloseOperation(3);
        setVisible(true);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);
        add(new Draw());
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {

        if(Draw.getGameView() == 1){
            if(ke.getKeyCode() == KeyEvent.VK_ENTER) {
                GameStatus.mapStart();
            }
        }
        else if(Draw.getGameView() == 2) {
            if (!MapObjectList.getPlayer().isMoving()) {
                switch (ke.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        MapObjectList.getPlayer().moveRight();
                        MapObjectList.getPlayer().keyPressed();
                        break;
                    case KeyEvent.VK_LEFT:
                        MapObjectList.getPlayer().moveLeft();
                        MapObjectList.getPlayer().keyPressed();
                        break;
                    case KeyEvent.VK_UP:
                        MapObjectList.getPlayer().moveUp();
                        MapObjectList.getPlayer().keyPressed();
                        break;
                    case KeyEvent.VK_DOWN:
                        MapObjectList.getPlayer().moveDown();
                        MapObjectList.getPlayer().keyPressed();
                        //GameStatus.numberOfFruits = 0;
                        break;
                    default:
                        break;
                }
            }
            if (GameStatus.isGameStart()) {
                switch (ke.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE:
                        if (!GameStatus.isGamePaused())
                            GameStatus.setPause(true);
                        else
                            GameStatus.setPause(false);
                        break;
                }
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (Draw.getGameView() == 2) {
            switch (ke.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    MapObjectList.getPlayer().keyReleased();
                    break;
                case KeyEvent.VK_LEFT:
                    MapObjectList.getPlayer().keyReleased();
                    break;
                case KeyEvent.VK_UP:
                    MapObjectList.getPlayer().keyReleased();
                    break;
                case KeyEvent.VK_DOWN:
                    MapObjectList.getPlayer().keyReleased();
                    break;
                default:
                    break;
            }
        }
    }
}
