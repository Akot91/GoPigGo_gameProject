package gameMap;

import game.GameStatus;

import static gameMap.MapObjectList.addBlock;
import static gameMap.MapObjectList.addElement;
import static gameMap.MapObjectList.addEnemy;
import static gameMap.MapObjectList.addWall;
import game.gameObjects.Block;
import game.gameObjects.Element;
import game.gameObjects.Enemy;
import game.gameObjects.Wall;
import gameFrame.Draw;
import gameMap.gameLevel.ILevel;
import gameMap.gameLevel.IMapProperties;

public class MapGenerator implements IMapProperties {
                      
    public static void fillObjectList(ILevel lvl){
        
        int[][] map = new int[NUMBER_OF_X_TILES][NUMBER_OF_Y_TILES];
        map = lvl.getLevelMap().clone();
        
        for(int x = 0; x < NUMBER_OF_X_TILES; x++){
            for(int y = 0; y < NUMBER_OF_Y_TILES; y ++){
                switch (map[x][y]) {
                    case 1:
                        addWall(new Wall(x, y, lvl.getWallType()));
                        break;
                    case 2:
                        addBlock(new Block(x, y, lvl.getBlockType()));
                        break;
                    case 3:
                        addElement(new Element(x, y, lvl.getFruitType()));
                        GameStatus.addFruit();
                        break;
                    case 4:
                        addEnemy(new Enemy(x, y, lvl.getEnemyType()));
                        break;
                    case 5:
                        MapObjectList.createNewPlayer();
                        MapObjectList.getPlayer().setVisible();
                        MapObjectList.getPlayer().updateCoordinates(x, y);
                        break;
                    default:
                        break;
                }
            }
        }
        
        Draw.setBackground(lvl.getBackground());
        Draw.setHudBar(lvl.getHudBarTitle());
    }   
}
