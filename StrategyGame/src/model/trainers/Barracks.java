package model.trainers;

import static model.Configuration.*;
import commands.ActionCommand;
import commands.IllegalCommandException;
import model.GameState;
import model.common.Stock;
import model.player.Player;
import model.field.Field;
import model.warriors.Dragon;
import model.warriors.Knight;
import model.warriors.Peasant;
import model.warriors.Swordsman;

/**
 *
 * @author sonrisa
 */
public class Barracks extends Trainer {

    public final static Stock COST = calcMovableCost(BARRACKS_HP);

    public static void create(Field position, Player player){
        (new Barracks(position, player)).add();
    }
    
    private Barracks(Field position, Player player) {
        super(calcBuildingHealth(BARRACKS_HP), position, player);
        this.timer = BARRACKS_HP.getValue();
        populateActions();
    }

    public void trainPeasant() throws IllegalCommandException {
        player.getTreasury().decrease(Peasant.COST);
        setTimer(PEASANT_HP.getValue());
        Peasant.create(position, player);
    }

    public void trainSwordsman() throws IllegalCommandException {
        player.getTreasury().decrease(Swordsman.COST);
        setTimer(SWORDSMAN_HP.getValue());
        Swordsman.create(position, player);
    }

    public void trainKnight() throws IllegalCommandException {
        player.getTreasury().decrease(Knight.COST);
        setTimer(KNIGHT_HP.getValue());
        Knight.create(position, player);
    }

    public void trainDragon() throws IllegalCommandException {
        player.getTreasury().decrease(Dragon.COST);
        setTimer(DRAGON_HP.getValue());
        Dragon.create(position, player);
    }

    public final void populateActions() {
        actions.add(new ActionCommand((Object o)->trainPeasant(), "Train Peasant", GameState.EXECUTION));
        actions.add(new ActionCommand((Object o)->trainSwordsman(), "Train Swordsman", GameState.EXECUTION));
        actions.add(new ActionCommand((Object o)->trainKnight(), "Train Knigth", GameState.EXECUTION));
        actions.add(new ActionCommand((Object o)->trainDragon(), "Train Dragon", GameState.EXECUTION));
    }
    
    @Override
    public final int getHP() {
        return calcBuildingHealth(BARRACKS_HP);
    }
}
