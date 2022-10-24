package model.player;

public class Player {
    private String name;
    private final int idx;
   
    public Player(int idx) {
        this.idx = idx;
        this.name = "Player#" + (idx + 1);
    }
    
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public void init(){
        //TODO
    }
}
