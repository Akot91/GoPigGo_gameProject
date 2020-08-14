package game.gameObjects;

public enum WallType {
    
    A("0"),
    B("1"),
    C("2"),
    D("3");
    
    private String name;
    
    WallType(String name){
        this.name = name;
    }
    
    public String getSource(){
        return "src/main/resources/anim/walls/wall_" + name + "_";
    }
}
