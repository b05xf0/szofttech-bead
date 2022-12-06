package model.workers;

import static model.Configuration.*;
import commands.ActionCommand;
import commands.IllegalCommandException;
import model.GameState;
import model.common.Unit;
import model.common.UnitState;
import model.extractors.Farm;
import model.field.FieldType;
import model.player.Player;
import model.field.Field;


/**
 *
 * @author sonrisa
 */
public class Farmer extends Worker {

    public static void create(Field position, Player player){
        (new Farmer(position, player)).add();
    }
    
    private Farmer(Field position, Player player) {
        super(position, player);
        populateActions();
    }
    
    @Override
    public boolean canFarm(){
        return position.getType() == FieldType.GRASS
               && state == UnitState.READY;
    }
    
    public void buildFarm() throws IllegalCommandException{
        if(canFarm() && canBuild()){
            player.getTreasury().decrease(Farm.COST);
            setTimer(EXTRACTOR_HP.getValue());
            Farm.create(position, player);
        } else {
            throw new IllegalCommandException(GameState.ERR_CANNOT_BUILD);
        }
    }

    public final void populateActions() {
        actions.add(new ActionCommand((Object o)->buildFarm(), "Build Farm", GameState.EXECUTION));
        actions.add(new ActionCommand((Object o)->buildCastle(), "Build Castle", GameState.EXECUTION));
        actions.add(new ActionCommand((Object o)->buildBarracks(), "Build Barracks", GameState.EXECUTION));
        actions.add(new ActionCommand((Object o)->move((Field)o), "Move", GameState.MOVE_SELECT_TARGETFIELD));
        actions.add(new ActionCommand((Object o)->attack((Unit) o), "Attack", GameState.ATTACK_SELECT_TARGETFIELD));
    }
}
