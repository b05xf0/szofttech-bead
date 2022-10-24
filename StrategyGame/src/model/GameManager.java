package model;

import model.map.Map;
import model.player.Player;
import model.field.Field;
import java.awt.Point;

public class GameManager {
    private static final int ACTION_POINTS = 100;
    private final Player[] players = new Player[2]; 

    private int turn;
    private int playerIdx;
    private int actionPoints;
    private GameState state;
    private final Map map;
    private Field selectedField;
    public GameManager(){
        state = GameState.SETUP;
        players[0] = new Player(0);
        players[1] = new Player(1);
        map = new Map();
    }

    public GameState getState() { return state; }
    
    public Player getPlayer(int idx) { return players[idx]; }
    
    public Player getCurrentPlayer() { return players[this.playerIdx]; }
    
    public Map getMap() { return map; }
    
    public Field getSelectedField() { return selectedField; }
    
    public void selectField(Point pos) { selectedField = map.getField(pos); }
    
    private void switchPlayer() { this.playerIdx = this.playerIdx == 1 ? 0 : 1; }
    
    public void start(){
        selectedField = null;
        playerIdx = 0;
        actionPoints = ACTION_POINTS;
        map.init();
        players[0].init();
        players[1].init();
        state = GameState.SELECTFIELD;
        //TODO
    }
    
}
