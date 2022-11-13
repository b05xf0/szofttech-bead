package model;

import model.map.Map;
import model.player.Player;
import model.field.Field;
import java.awt.Point;
import commands.ActionCommand;
import commands.IllegalCommandException;
import model.common.Unit;
import model.common.UnitState;
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
    private ActionCommand selectedCommand;
    
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
    
    public int getAPs() { return getCurrentPlayer().getAPs();}
    
    public Map getMap() { return map; }
    
    public Field getSelectedField() { return selectedField; }
    
    public Field getSelectedTargetField() { return selectedTargetField; }
    
    public Unit getSelectedUnit() { return selectedUnit; }
    
    public Unit getSelectedTargetUnit() { return selectedTargetUnit; }
    
    public ActionCommand getSelectedCommand() { return selectedCommand; }
    
    public void executeCommand(){
       try {
            selectedCommand.execute(selectedTargetField,selectedTargetUnit);
            map.initMovementCosts(0);
            selectField(selectedField.getPos());
        } catch (IllegalCommandException ex) {
            state = ex.getErrState();
        }
    }
    
    public void selectField(Point pos) {
        selectedField = map.getField(pos);
        selectedUnit = null;
        selectedCommand = null;
        selectedTargetField = null;
        selectedTargetUnit = null;
        map.initMovementCosts(0);
        if(selectedField.hasUnits()) {
            state = GameState.SELECT_UNIT;
        }
        else {
            state = GameState.SELECT_FIELD;
        }
    }

    public void selectTargetField(Point pos) {
        selectedTargetUnit = null;
        
        if(map.getField(pos).isValidTarget(getCurrentPlayer()) && getCurrentPlayer().getAPs() >= map.getField(pos).getMovementCost()){
            selectedTargetField = map.getField(pos);
            if(selectedCommand.needTargetUnit()
               && selectedField.hasUnits()){
                state = GameState.SELECT_TARGETUNIT;
            }else{
                state = GameState.EXECUTION;
            }
        }
    }    
    
    public void selectUnit(Unit unit) {
        selectedCommand = null;
        selectedTargetField = null;
        selectedTargetUnit = null;
        map.initMovementCosts(0);
        if(getCurrentPlayer().equals(unit.getPlayer())
           && unit.getState() == UnitState.READY){
            selectedUnit = unit;
            if(!selectedUnit.getActions().isEmpty()){
                state = GameState.SELECT_ACTION;
            } else {
                state = GameState.SELECT_UNIT;
            }
        }
    }
    
    public void selectCommand(ActionCommand command) {
        selectedCommand = command;
        selectedTargetField = null;
        selectedTargetUnit = null;
        map.initMovementCosts(0);
        if(selectedCommand.needTargetField()) {
            map.setMovementCosts(selectedUnit);
            state = GameState.SELECT_TARGETFIELD;
        } else {
            state = GameState.EXECUTION;
        }
    }
    
    public void selectTargetUnit(Unit unit) {
        if(!getCurrentPlayer().equals(unit.getPlayer())){
            selectedTargetUnit = unit;
            state = GameState.EXECUTION;
        } else {
            state = GameState.SELECT_TARGETUNIT;
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
        selectField(getCurrentPlayer().getUnits().get(0).getPosition().getPos());
    }
    public void endTurn(){
        getCurrentPlayer().endTurn();
        switchPlayer();
        if(playerIdx == 0) ++turn;
        selectedField = null;
        selectedUnit = null;
        selectedCommand = null;
        selectedTargetField = null;
        selectedTargetUnit = null;
        selectField(getCurrentPlayer().getUnits().get(0).getPosition().getPos());
    }
    
    @Override
    public String toString() {
        return String.format("Turn #%d", turn);
    }
    
}
