package game;

public enum Directions {
    
    NONE(-1),
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3),
    NORTHEAST(4),
    SOUTHEAST(5),
    SOUTHWEST(6),
    NORTHWEST(7);
    
    private int direction;
    
    Directions(int direction){
        this.direction = direction;
    }
    
    public int getDirection()
    {
        return direction;
    }
}
