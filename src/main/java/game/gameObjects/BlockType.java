package game.gameObjects;

public enum BlockType {
    
    A("0"),
    B("1"),
    C("2"),
    D("3");
    
    private String name;
    
    BlockType(String name){
        this.name = name;
    }
    
    public String getSource(){
        return "src/main/resources/anim/blocks/block_" + name + "_";
    }
}
