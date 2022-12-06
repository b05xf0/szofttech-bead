package model;

import model.map.Map;
import model.player.Player;
import model.field.Field;
import java.awt.Point;
import commands.ActionCommand;
import commands.IllegalCommandException;
import model.common.Unit;
import model.common.UnitState;

public class GameManager {

    private final Player[] players = new Player[2];

    private int turn;
    private int playerIdx;

    private GameState state;
    private final Map map;
    private Field selectedField;
    private Unit selectedUnit;
    private ActionCommand selectedCommand;

    public GameManager() {
        state = GameState.SETUP;
        map = new Map();
        players[0] = new Player(0, map.getStartingPosition(0));
        players[1] = new Player(1, map.getStartingPosition(1));
    }

    public GameState getState() {
        return state;
    }

    public Player getPlayer(int idx) {
        return players[idx];
    }

    public Player getCurrentPlayer() {
        return players[this.playerIdx];
    }

    public int getTurn() {
        return turn;
    }

    public int getAPs() {
        return getCurrentPlayer().getAPs();
    }

    public Map getMap() {
        return map;
    }

    public Field getSelectedField() {
        return selectedField;
    }

    public Field getSelectedTargetField() {
        return (selectedCommand != null) ? selectedCommand.getTargetField() : null;
    }

    public Unit getSelectedUnit() {
        return selectedUnit;
    }

    public Unit getSelectedTargetUnit() {
        return (selectedCommand != null) ? selectedCommand.getTargetUnit() : null;
    }

    public ActionCommand getSelectedCommand() {
        return selectedCommand;
    }

    public void executeCommand() {
        try {
            selectedCommand.execute();
            selectField(selectedField.getPos());
        } catch (IllegalCommandException ex) {
            state = ex.getErrState();
        }
    }

    public void selectField(Point pos) {
        selectedField = map.getField(pos);
        selectedUnit = null;
        selectedCommand = null;
        map.initMovementCosts(0);
        if (selectedField.hasUnits()) {
            if (selectedField.getUnits().size() == 1) {
                selectUnit(selectedField.getUnits().get(0));
            } else {
                state = GameState.SELECT_UNIT;
            }
        } else {
            state = GameState.SELECT_FIELD;
        }

    }

    public void selectTargetField(Point pos) {
        if (selectedCommand.getNextState() == GameState.ATTACK_SELECT_TARGETFIELD && map.getField(pos).getMovementCost() < Integer.MAX_VALUE) {
            selectedCommand.setTargetField(map.getField(pos));
            if (getSelectedTargetField().getTrainer() != null) {
                selectTargetUnit(getSelectedTargetField().getTrainer());
            } else if (getSelectedTargetField().getExtractor() != null) {
                selectTargetUnit(getSelectedTargetField().getExtractor());
            } else if (getSelectedTargetField().getUnits().size() == 1) {
                selectTargetUnit(getSelectedTargetField().getUnits().get(0));
            } else if (getSelectedTargetField().getUnits().size() > 1) {
                state = GameState.SELECT_TARGETUNIT;
            } else {
                state = GameState.SELECT_ACTION;
            }
        } else if (map.getField(pos).isValidTarget(getCurrentPlayer()) && getCurrentPlayer().getAPs() >= map.getField(pos).getMovementCost()) {
            selectedCommand.setTargetField(map.getField(pos));
            state = GameState.EXECUTION;
        }
        //map.initMovementCosts(0);
    }

    public void selectUnit(Unit unit) {
        selectedCommand = null;
        map.initMovementCosts(0);
        if (getCurrentPlayer().equals(unit.getPlayer())
                && unit.getState() == UnitState.READY) {
            selectedUnit = unit;
            selectedUnit.initActions();
            if (!selectedUnit.getActions().isEmpty()) {
                state = GameState.SELECT_ACTION;
            } else {
                state = GameState.SELECT_UNIT;
            }
        } else {
            state = GameState.SELECT_UNIT;
        }
    }

    public void selectCommand(ActionCommand command) {
        selectedUnit.initActions();
        selectedCommand = command;
        switch (state = selectedCommand.getNextState()) {
            case MOVE_SELECT_TARGETFIELD ->
                map.setMovementCosts(selectedUnit);
            case ATTACK_SELECT_TARGETFIELD ->
                map.setAttackMode(selectedUnit);
            default ->
                map.initMovementCosts(0);
        }
    }

    public void selectTargetUnit(Unit unit) {
        if (!getCurrentPlayer().equals(unit.getPlayer())
                && ((getSelectedTargetField().getTrainer() == null && getSelectedTargetField().getExtractor() == null)
                || unit.equals(getSelectedTargetField().getTrainer())
                || unit.equals(getSelectedTargetField().getTrainer()))) {
            selectedCommand.setTargetUnit(unit);
            state = GameState.EXECUTION;
        } else if (selectedCommand.getTargetUnit() == null) {
            state = GameState.SELECT_TARGETUNIT;
        } else {
            //map.initMovementCosts(0);
            state = GameState.EXECUTION;
        }
        
    }

    private void switchPlayer() {
        this.playerIdx = this.playerIdx == 1 ? 0 : 1;
    }

    public void start() {
        selectedField = null;
        playerIdx = 0;
        turn = 1;
        map.init();
        players[0].init();
        players[1].init();
        players[0].setStrikeBack(playerIdx == 1);
        players[1].setStrikeBack(playerIdx == 0);
        selectField(getCurrentPlayer().getUnits().get(0).getPosition().getPos());
    }

    public void endTurn() {

        if (playerIdx == 1) {
            players[0].endTurn();
            players[1].endTurn();
            ++turn;
        }
        selectedUnit = null;
        selectedCommand = null;
        switchPlayer();
        players[0].setStrikeBack(playerIdx == 1);
        players[1].setStrikeBack(playerIdx == 0);
        if (players[0].isAlive() && players[1].isAlive()){
            selectField(getCurrentPlayer().getUnits().get(0).getPosition().getPos());
        } else {
            state = GameState.OVER;
        }
    }
    
    public Player getWinner(){
        if (state == GameState.OVER) {
            return players[0].isAlive() ? players[0] : players[1]; 
        } else {
            return null;
        }
    }
    
    public void allYourBaseAreBelongToUs(){
        for (Unit u :  players[playerIdx == 1 ? 0 : 1].getUnits()){
           u.changePlayer(players[playerIdx]);
        }
    }

    @Override
    public String toString() {
        return String.format("Turn #%d", turn);
    }

}
