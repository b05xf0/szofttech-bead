package model;

import model.map.Map;
import model.player.Player;
import model.field.Field;
import java.awt.Point;
import model.common.Unit;
import model.interfaces.ICommand;

public class GameManager {
    private final Player[] players = new Player[2]; 

    private int turn;
    private int playerIdx;

    private GameState state;
    private final Map map;
    private Field selectedField;
    private Field selectedTargetField;
    private Unit selectedUnit;
    private Unit selectedTargetUnit;
    private ICommand selectedCommand;
    
    public GameManager(){
        state = GameState.SETUP;
        map = new Map();
        players[0] = new Player(0,map.getStartingPosition(0));
        players[1] = new Player(1,map.getStartingPosition(1));
    }

    public GameState getState() { return state; }
    
    public Player getPlayer(int idx) { return players[idx]; }
    
    public Player getCurrentPlayer() { return players[this.playerIdx]; }
    
    public int getTurn() { return turn; }
    
    public int getAPs() { return players[this.playerIdx].getAPs();}
    
    public Map getMap() { return map; }
    
    public Field getSelectedField() { return selectedField; }
    
    public Unit getSelectedUnit() { return selectedUnit; }
    
    public ICommand getSelectedCommand() { return selectedCommand; }
    
    public void selectField(Point pos) {
        selectedField = map.getField(pos);
        selectedUnit = null;
        selectedCommand = null;
        if(selectedField.hasUnits()) {
            state = GameState.SELECT_UNIT;
        }
        else {
            state = GameState.SELECT_FIELD;
        }
    }

    public void selectTargetField(Point pos) {
        selectedTargetField = map.getField(pos);
        if(selectedField.hasUnits()) state = GameState.SELECT_TARGETUNIT;
    }    
    
    public void selectUnit(Unit unit) {
        selectedCommand = null;
        if(getCurrentPlayer().equals(unit.getPlayer())){
            selectedUnit = unit;
            if(!selectedUnit.getActions().isEmpty()){
                state = GameState.SELECT_ACTION;
            } else {
                state = GameState.SELECT_UNIT;
            }
        }
    }
    
    public void selectCommand(ICommand command) {
        selectedCommand = command;
        if(!selectedCommand.needTarget()) {
            state = GameState.EXECUTION;
        } else {
            state = GameState.SELECT_ACTION;
        }
    }
    
    
    private void switchPlayer() { this.playerIdx = this.playerIdx == 1 ? 0 : 1; }
    
    public void start(){
        selectedField = null;
        playerIdx = 0;
        turn = 1;
        map.init();
        players[0].init();
        players[1].init();
        state = GameState.SELECT_FIELD;
        //TODO
    }
    public void endTurn(){
        if(playerIdx == 1) ++turn;
        switchPlayer();
        selectedField = null;
        selectedUnit = null;
        selectedCommand = null;
        state = GameState.SELECT_FIELD;
    }
    
    @Override
    public String toString() {
        return String.format("Turn #%d", turn);
    }
    
}
