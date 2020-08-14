package game.gameObjects.effects;

import game.Directions;
import gameMap.MapObjectList;

public class SingleParticleEffect {
    
    public SingleParticleEffect(int x, int y, String type){
        MapObjectList.addSingleCollectEffect(new Particle(x, y, 1, Directions.NORTH, "ef_" + type));
        MapObjectList.addSingleCollectEffect(new Particle(x, y, 1, Directions.SOUTH, "ef_" + type));
        MapObjectList.addSingleCollectEffect(new Particle(x, y, 1, Directions.EAST, "ef_" + type));
        MapObjectList.addSingleCollectEffect(new Particle(x, y, 1, Directions.WEST, "ef_" + type));
        MapObjectList.addSingleCollectEffect(new Particle(x, y, 1, Directions.NORTHEAST, "ef_" + type));
        MapObjectList.addSingleCollectEffect(new Particle(x, y, 1, Directions.NORTHWEST, "ef_" + type));
        MapObjectList.addSingleCollectEffect(new Particle(x, y, 1, Directions.SOUTHEAST, "ef_" + type));
        MapObjectList.addSingleCollectEffect(new Particle(x, y, 1, Directions.SOUTHWEST, "ef_" + type));
    }
}
