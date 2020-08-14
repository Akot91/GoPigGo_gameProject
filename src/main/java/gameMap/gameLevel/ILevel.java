package gameMap.gameLevel;

import game.gameObjects.BlockType;
import game.gameObjects.EnemyType;
import game.gameObjects.FruitType;
import game.gameObjects.WallType;
import javax.swing.ImageIcon;

public interface ILevel {
    
    public WallType getWallType();
    
    public EnemyType getEnemyType();
    
    public BlockType getBlockType();
    
    public FruitType getFruitType();
    
    public ImageIcon getBackground();
    
    public int[][] getLevelMap();
     
    public ImageIcon getHudBarTitle();
    
}
