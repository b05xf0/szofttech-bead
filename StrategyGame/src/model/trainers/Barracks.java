/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.trainers;


import commands.ActionCommand;
import commands.IllegalCommandException;
import model.common.AttrLevel;
import model.common.Stock;
import model.common.Unit;
import model.player.Player;
import model.common.UnitState;
import model.common.UnitType;
import model.field.Field;
import model.warriors.Dragon;
import model.warriors.Knight;
import model.warriors.Peasant;
import model.warriors.Swordsman;

/**
 *
 * @author sonrisa
 */

public class Barracks extends Trainer{
    public static final AttrLevel HP = AttrLevel.MEDIUM;

    public final static Stock getCost(){
        return (new Stock(BASECOST)).multiply(HP.getValue());
    }
    public static Barracks create(Field position, Player player){
        return new Barracks(position, player);
    }
    
    public Barracks(Field position, Player player) {
        super(HP.getValue() * BASEHEALTH, position, player);
        this.timer = HP.getValue();
        type = UnitType.BARRACKS;
        populateActions();
    }
   
    public void trainPeasant() throws IllegalCommandException {
        player.getTreasury().decrement(Peasant.getCost());
        setTimer(Peasant.HP.getValue());
        Peasant.create(position, player);
    }
    
    public void trainSwordsman() throws IllegalCommandException {
        player.getTreasury().decrement(Swordsman.getCost());
        setTimer(Swordsman.HP.getValue());
        Swordsman.create(position, player);
    }
    
    public void trainKnight() throws IllegalCommandException {
        player.getTreasury().decrement(Knight.getCost());
        setTimer(Knight.HP.getValue());
        Knight.create(position, player);
    }
    
    public void trainDragon() throws IllegalCommandException{
        player.getTreasury().decrement(Dragon.getCost());
        setTimer(Dragon.HP.getValue());
        Dragon.create(position, player);
    }
    public final void populateActions() {
        actions.add(new ActionCommand((Field f,Unit u)->this.trainPeasant(),"Train Peasant"));
        actions.add(new ActionCommand((Field f,Unit u)->this.trainSwordsman(),"Train Swordsman"));
        actions.add(new ActionCommand((Field f,Unit u)->this.trainKnight(),"Train Knigth"));
        actions.add(new ActionCommand((Field f,Unit u)->this.trainDragon(),"Train Dragon"));
    }
    
}
