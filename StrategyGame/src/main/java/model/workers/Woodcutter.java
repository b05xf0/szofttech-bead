package model.workers;

import static model.Configuration.*;
import commands.ActionCommand;
import commands.IllegalCommandException;
import java.util.LinkedList;
import java.util.List;
import model.GameState;
import model.common.Unit;
import model.common.UnitState;
import model.field.FieldType;
import model.extractors.Hut;
import model.field.Field;
import model.player.Player;

/**
 *
 * @author sonrisa
 */
public class Woodcutter extends Worker {

    public static void create(Field position, Player player){
        (new Woodcutter(position, player)).add();
    }    
    
    private Woodcutter(Field position, Player player) {
        super(position, player);
        populateActions();
    }

    @Override
    public boolean canCut() {
        return position.getType() == FieldType.FOREST
                && state == UnitState.READY;
    }

    public void buildHut() throws IllegalCommandException {
        if (canCut() && canBuild()) {
            player.getTreasury().decrease(Hut.COST);
            setTimer(EXTRACTOR_HP.getValue());
            Hut.create(position, player);
        } else {
            throw new IllegalCommandException(GameState.ERR_CANNOT_BUILD);
        }
    }
    
    public final void populateActions() {
        actions.add(new ActionCommand((Object o)->buildHut(), "Build Hut", GameState.EXECUTION));
        actions.add(new ActionCommand((Object o)->buildCastle(), "Build Castle", GameState.EXECUTION));
        actions.add(new ActionCommand((Object o)->buildBarracks(), "Build Barracks", GameState.EXECUTION));
        actions.add(new ActionCommand((Object o)->move((Field)o), "Move", GameState.MOVE_SELECT_TARGETFIELD));
        actions.add(new ActionCommand((Object o)->attack((Unit) o), "Attack", GameState.ATTACK_SELECT_TARGETFIELD));
    }

}
