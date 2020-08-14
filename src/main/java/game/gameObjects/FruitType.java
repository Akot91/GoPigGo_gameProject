package game.gameObjects;

public enum FruitType {
    
    APPLE("apple"),
    ORANGE("orange"),
    STRAWBERRY("strawberry"),
    WATERMELON("watermelon");
    
    private String name;
    
    FruitType(String name){
        this.name = name;
    }
    
    public String getSource(){
        return "src/main/resources/anim/fruits/" + name + "_";
    }
}
