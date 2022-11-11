/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.trainers;


import commands.TrainFarmerCommand;
import commands.TrainMinerCommand;
import commands.TrainWoodcutterCommand;
import java.util.LinkedList;
import java.util.List;
import model.common.ActionCommand;
import model.common.AttrLevel;
import model.common.UnitState;
import model.common.UnitType;
import model.player.Player;
import model.field.Field;
import model.interfaces.ICommand;
import model.workers.Farmer;
import model.workers.Miner;
import model.workers.Woodcutter;

/**
 *
 * @author sonrisa
 */
public class Castle extends Trainer {
    public static final AttrLevel HP = AttrLevel.HIGH;
    
    //private ActionCommand trainMinerCommand;
    
    public Castle(Field position, Player player) {
        super(HP.getValue() * BASEHEALTH, position, player);
        this.timer = HP.getValue();
        type = UnitType.CASTLE;
        populateActions();
    }
    
    @Override
    public final int getHPValue() {
        return HP.getValue();
    }
    
    @Override
    public boolean isHQ() {
        return true;
    }
    
    @Override
     public List<ICommand> getActions() throws NullPointerException {
        return this.actions;
    }
    
    public boolean canTrainWorker() {
        return true;
    }
    
    public Miner trainMiner() {
        Miner unit = new Miner(position, player);
        player.getTreasury().decrement(unit.cost());
        position.addUnit(unit);
        player.addUnit(unit);
        return unit;
    }
    
    public Woodcutter trainWoodcutter() {
        Woodcutter unit = new Woodcutter(position, player);
        player.getTreasury().decrement(unit.cost());
        position.addUnit(unit);
        player.addUnit(unit);
        return unit;
    }
    
    public Farmer trainFarmer() {
        Farmer unit = new Farmer(position, player);
        player.getTreasury().decrement(unit.cost());
        position.addUnit(unit);
        player.addUnit(unit);
        return unit;
    }

    @Override
    public final void populateActions() {
        actions.add(new TrainMinerCommand(this));
        actions.add(new TrainWoodcutterCommand(this));
        actions.add(new TrainFarmerCommand(this));
    }
}
