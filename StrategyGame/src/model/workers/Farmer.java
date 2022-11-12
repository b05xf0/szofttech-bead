/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.workers;

import commands.ActionCommand;
import commands.AttackActionCommand;
import commands.IllegalCommandException;
import commands.MoveActionCommand;
import model.GameState;
import model.common.Unit;
import model.common.UnitState;
import model.common.UnitType;
import model.extractors.Farm;
import model.field.FieldType;
import model.player.Player;
import model.field.Field;


/**
 *
 * @author sonrisa
 */
public class Farmer extends Worker {
    public static Farmer create(Field position, Player player){
        return new Farmer(position, player);
        
    }
    
    public Farmer(Field position, Player player) {
        super(position, player);
        type = UnitType.FARMER;
        populateActions();
        
    }
    
    @Override
    public boolean canFarm(){
        return position.getType() == FieldType.GRASS
               && state == UnitState.READY;
    }
    
    public void buildFarm() throws IllegalCommandException{
        if(canFarm() && canBuild()){
            player.getTreasury().decrement(Farm.getCost());
            setTimer(Farm.HP.getValue());
            Farm.create(position, player);
        } else {
            throw new IllegalCommandException(GameState.ERR_CANNOT_BUILD);
        }
    }
    public final void populateActions() {
        actions.add(new ActionCommand((Field targetField, Unit targetUnit)->this.buildFarm(),"Build Farm"));
        actions.add(new ActionCommand((Field targetField, Unit targetUnit)->this.buildCastle(),"Build Castle"));
        actions.add(new ActionCommand((Field targetField, Unit targetUnit)->this.buildBarracks(),"Build Barracks"));
        actions.add(new MoveActionCommand((Field targetField, Unit targetUnit)->this.move(targetField),"Move"));
        actions.add(new AttackActionCommand((Field targetField, Unit targetUnit)->this.attack(targetUnit),"Attack"));
    }   
}
