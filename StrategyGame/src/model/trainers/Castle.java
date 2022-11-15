package model.trainers;

import static model.Configuration.*;
import commands.ActionCommand;
import commands.IllegalCommandException;
import model.GameState;
import model.common.Stock;
import model.player.Player;
import model.field.Field;

import model.workers.Farmer;
import model.workers.Miner;
import model.workers.Woodcutter;

/**
 *
 * @author sonrisa
 */
public class Castle extends Trainer {

    public final static Stock COST = calcMovableCost(CASTLE_HP);

    public static void create(Field position, Player player){
        (new Castle(position, player)).add();
    }
    
    private Castle(Field position, Player player) {
        super(calcBuildingHealth(CASTLE_HP), position, player);
        this.timer = CASTLE_HP.getValue();
        populateActions();
    }

    @Override
    public boolean isHQ() {
        return true;
    }

    public void trainMiner() throws IllegalCommandException {
        player.getTreasury().decrease(Miner.COST);
        setTimer(WORKER_HP.getValue());
        Miner.create(position, player);
    }

    public void trainWoodcutter() throws IllegalCommandException {
        player.getTreasury().decrease(Woodcutter.COST);
        setTimer(WORKER_HP.getValue());
        Woodcutter.create(position, player);
    }

    public void trainFarmer() throws IllegalCommandException {
        player.getTreasury().decrease(Farmer.COST);
        setTimer(WORKER_HP.getValue());
        Farmer.create(position, player);
    }
    
    public final void populateActions() {
        actions.add(new ActionCommand((Object o)->trainMiner(), "Train Miner", GameState.EXECUTION));
        actions.add(new ActionCommand((Object o)->trainWoodcutter(), "Train Woodcutter", GameState.EXECUTION));
        actions.add(new ActionCommand((Object o)->trainFarmer(), "Train Farmer", GameState.EXECUTION));
    }
}
