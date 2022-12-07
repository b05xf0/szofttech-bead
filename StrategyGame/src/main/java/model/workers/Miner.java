package model.workers;

import static model.Configuration.*;
import commands.ActionCommand;
import commands.IllegalCommandException;
import model.GameState;
import model.common.Unit;
import model.common.UnitState;
import model.field.FieldType;
import model.extractors.Mine;
import model.player.Player;
import model.field.Field;

/**
 *
 * @author sonrisa
 */
public class Miner extends Worker {
    
    public static void create(Field position, Player player){
        (new Miner(position, player)).add();
    }
    
    private Miner(Field position, Player player){
        super(position, player);
        populateActions();

    }
    
    @Override
    public boolean canMine(){
        return position.getType() == FieldType.GOLD
               && state == UnitState.READY;
    }
    
    public void buildMine() throws IllegalCommandException{
        if(canMine() && canBuild()){
            player.getTreasury().decrease(Mine.COST);
            setTimer(EXTRACTOR_HP.getValue());
            Mine.create(position, player);
        } else {
            throw new IllegalCommandException(GameState.ERR_CANNOT_BUILD);
        }
    }
    
    public final void populateActions() {
        actions.add(new ActionCommand((Object o)->buildMine(), "Build Mine", GameState.EXECUTION));
        actions.add(new ActionCommand((Object o)->buildCastle(), "Build Castle", GameState.EXECUTION));
        actions.add(new ActionCommand((Object o)->buildBarracks(), "Build Barracks", GameState.EXECUTION));
        actions.add(new ActionCommand((Object o)->move((Field)o), "Move", GameState.MOVE_SELECT_TARGETFIELD));
        actions.add(new ActionCommand((Object o)->attack((Unit) o), "Attack", GameState.ATTACK_SELECT_TARGETFIELD));
    }
}
