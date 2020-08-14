package gameMap.gameLevel;

import game.gameObjects.BlockType;
import game.gameObjects.EnemyType;
import game.gameObjects.FruitType;
import game.gameObjects.WallType;
import gameMap.gameLevel.ILevel;
import gameMap.gameLevel.IMapProperties;
import gameMap.gameLevel.Level;

import javax.swing.ImageIcon;

public class Level_1 extends Level implements IMapProperties, ILevel {
      
    WallType wall = WallType.A; // 1 wall
    BlockType block = BlockType.A; // 2 block
    FruitType fruit = FruitType.APPLE; // 3 fruit
    EnemyType enemy = EnemyType.EYE; // 4 enemy
    String mapTitle = "Widziały gały co brały";
    
    ImageIcon background = new ImageIcon("src/main/resources/backgrounds/bcg_level1.png");
    
    @Override
    protected void levelRows(){
        row0 = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        row1 = new int[]{1, 3, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 3, 1};
        row2 = new int[]{1, 0, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 0, 1};
        row3 = new int[]{1, 0, 1, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 1, 0, 1};
        row4 = new int[]{1, 0, 1, 0, 2, 2, 2, 3, 2, 2, 2, 3, 2, 2, 2, 0, 1, 0, 1};
        row5 = new int[]{1, 0, 1, 0, 2, 3, 2, 2, 2, 3, 2, 2, 2, 3, 2, 0, 1, 0, 1};
        row6 = new int[]{1, 0, 1, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 1, 0, 1};
        row7 = new int[]{1, 0, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 0, 1};
        row8 = new int[]{1, 3, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 3, 1};
        row9 = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    }
    
    @Override
    public WallType getWallType(){
        return wall;
    }
    
    @Override
    public EnemyType getEnemyType(){
        return enemy;
    }
    
    @Override
    public BlockType getBlockType(){
        return block;
    }
    
    @Override
    public FruitType getFruitType(){
        return fruit;
    }
    
    @Override
    public ImageIcon getBackground(){
        return background;
    }
    
    @Override
    public int[][] getLevelMap(){
        levelRows();
        fillLevelTab();
        return level;
    }          

    @Override
    public ImageIcon getHudBarTitle() {
        return new ImageIcon("src/main/resources/hud/lvl1.png");
    }
}
