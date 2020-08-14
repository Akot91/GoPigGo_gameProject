package gameMap;

import game.gameObjects.Block;
import game.gameObjects.Element;
import game.gameObjects.Enemy;
import game.gameObjects.Player;
import game.gameObjects.Wall;
import game.gameObjects.effects.Particle;
import gameFrame.Draw;
import java.util.ArrayList;
import java.util.List;

public class MapObjectList {
      
    private static List<Block> blockList = new ArrayList<Block>();
    private static List<Wall> wallList = new ArrayList<Wall>();
    private static List<Element> fruitList = new ArrayList<Element>();
    private static List<Enemy> enemyList = new ArrayList<Enemy>();
    
    private static List<Particle> collectEffect = new ArrayList<Particle>();
    
    private static Player player = new Player(-10, -10);
    
    public static void addBlock(Block bl){
        blockList.add(bl);
        Draw.addAnim(bl);
    }
    
    public static void removeBlock(Block bl){
        blockList.remove(bl);
        Draw.removeAnim(bl);
    }
    
    public static List<Block> getBlockList(){
        return blockList;
    }
    
    public static Block getSingleBlock(int number){
        return blockList.get(number);
    }
    
    public static Player getPlayer(){
        return player;
    }

    public static void createNewPlayer(){ player = new Player(-10, -10); }
    
    public static void addWall(Wall wl){
        wallList.add(wl);
        Draw.addAnim(wl);
    }
    
    public static List<Wall> getWallList(){
        return wallList;
    }
    
    public static void addElement(Element el){
        fruitList.add(el);
        Draw.addAnim(el);
    }
    
    public static void removeElement(Element el){
        fruitList.remove(el);
        Draw.removeAnim(el);
    }
    
    public static Element getElement(int el){
        if(fruitList.size() > 0)
            return fruitList.get(el);
        else
            return null;
    }
    
    public static List<Element> getElementList(){
        return fruitList;
    }
    
    public static void addEnemy(Enemy en){
        enemyList.add(en);
        Draw.addAnim(en);
    }
    
    public static void removeEnemy(Enemy en){
        enemyList.remove(en);
        Draw.removeAnim(en);
    }
    
    public static void addSingleCollectEffect(Particle cl){
        collectEffect.add(cl);
    }
    
    public static void removeSingleCollectEffect(Particle cl){
        collectEffect.add(cl);
        Draw.removeAnim(cl);
    }
    
    public static List<Enemy> getEnemyList(){
        return enemyList;
    }

    public static void clearAll(){
        //blockList.clear();
        for(Block bl : blockList){
            bl.selfDestroy();
        }
        wallList.clear();
        fruitList.clear();
        //enemyList.clear();
        for(Enemy en : enemyList){
            en.selfDestory();
        }
        collectEffect.clear();
        player.setUnvisible();
        player.updateCoordinates(-10, -10);
        Draw.clearAnimList();
    }
}
