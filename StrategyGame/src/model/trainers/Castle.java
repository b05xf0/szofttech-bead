/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.trainers;


import java.util.LinkedList;
import java.util.List;
import commands.ActionCommand;
import model.common.AttrLevel;
import commands.IllegalCommandException;
import model.common.Stock;
import model.common.Unit;
import model.common.UnitType;
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
    public static final AttrLevel HP = AttrLevel.HIGH;
    
    //private ActionCommand trainMinerCommand;
    public static Castle create(Field position, Player player){
        return new Castle(position, player);
    }
    
    public final static Stock getCost(){
        return (new Stock(BASECOST)).multiply(HP.getValue());
    }
    
    public Castle(Field position, Player player) {
        super(HP.getValue() * BASEHEALTH, position, player);
        this.timer = HP.getValue();
        type = UnitType.CASTLE;
        populateActions();
    }
    
   
    @Override
    public boolean isHQ() {
        return true;
    }
    
    public void trainMiner() throws IllegalCommandException {
        player.getTreasury().decrement(Miner.getCost());
        setTimer(Miner.HP.getValue());
        Miner.create(position, player);
    }
    
    public void trainWoodcutter() throws IllegalCommandException {
        player.getTreasury().decrement(Woodcutter.getCost());
        setTimer(Woodcutter.HP.getValue());
        Woodcutter.create(position, player);
    }
    
    public void trainFarmer() throws IllegalCommandException {
        player.getTreasury().decrement(Farmer.getCost());
        setTimer(Farmer.HP.getValue());
        Farmer.create(position, player);
    }

    public final void populateActions() {
        actions.add(new ActionCommand((Field f,Unit u)->this.trainMiner(),"Train Miner"));
        actions.add(new ActionCommand((Field f,Unit u)->this.trainWoodcutter(),"Train Woodcutter"));
        actions.add(new ActionCommand((Field f,Unit u)->this.trainFarmer(),"Train Farmer"));
    }
}
