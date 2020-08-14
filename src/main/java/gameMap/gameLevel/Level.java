package gameMap.gameLevel;

import game.gameObjects.BlockType;
import game.gameObjects.EnemyType;
import game.gameObjects.FruitType;
import game.gameObjects.WallType;
import static gameMap.gameLevel.IMapProperties.NUMBER_OF_X_TILES;
import static gameMap.gameLevel.IMapProperties.NUMBER_OF_Y_TILES;
import javax.swing.ImageIcon;

public abstract class Level{
    
    protected int[][] level = new int[NUMBER_OF_X_TILES][NUMBER_OF_Y_TILES]; //0 nothing, 5 player
    protected int[] row0, row1, row2, row3, row4, row5, row6, row7, row8, row9;
    protected WallType wall; // 1 wall
    protected BlockType block; // 2 block
    protected FruitType fruit; // 3 fruit
    protected EnemyType enemy; // 4 enemy
    protected ImageIcon background;
    protected String mapTitle;
    
    protected void levelRows(){};
    
    protected void fillRowLevelTab(int y, int[] row){
        for(int x = 0; x < row.length; x++){
            level[x][y] = row[x];
        }
    }
    
    protected void fillLevelTab(){
        fillRowLevelTab(0, row0);
        fillRowLevelTab(1, row1);
        fillRowLevelTab(2, row2);
        fillRowLevelTab(3, row3);
        fillRowLevelTab(4, row4);
        fillRowLevelTab(5, row5);
        fillRowLevelTab(6, row6);
        fillRowLevelTab(7, row7);
        fillRowLevelTab(8, row8);
        fillRowLevelTab(9, row9);
    }   

}
