package game.gameObjects;

public enum EnemyType {
    
    EYE("eye"),
    GHOST("ghost"),
    SKULL("skull");
    
    private String type;
    
    EnemyType(String type){
        this.type = type;
    }
    
    public String getSource(){
        return "src/main/resources/anim/enemy/" + type + "/" + type;
    }
}
