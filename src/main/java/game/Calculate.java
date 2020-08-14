package game;

import gameFrame.IGlobalProperties;

public class Calculate implements IGlobalProperties {
    
    private static final int X_ALIGNMENT = 12;
    private static final int Y_ALIGNMENT = 113;
    
    public static int calculateXAlignment(int x){
        return x * TILE_SIZE + X_ALIGNMENT;
    }
    
    public static int calculateYAlignment(int y){
        return y * TILE_SIZE + Y_ALIGNMENT;
    }
    
    public static int calculateBackgroundXAligment(int x){
        return x + X_ALIGNMENT;
    }
    
    public static int calculateBackgroundYAligment(int y){
        return y + Y_ALIGNMENT;
    }
    
    public static int calculateFPS(){
        return (int) 1000 / GAME_SPEED;
    }
}
