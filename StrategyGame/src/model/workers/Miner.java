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
import model.field.FieldType;
import model.extractors.Mine;
import model.player.Player;
import model.field.Field;

/**
 *
 * @author sonrisa
 */
public class Miner extends Worker {

    public static Miner create(Field position, Player player){
        return new Miner(position, player);
    }
    
    public Miner(Field position, Player player){
        super(position, player);
        type = UnitType.MINER;
        populateActions();
    }
    
    @Override
    public boolean canMine(){
        return position.getType() == FieldType.GOLD
               && state == UnitState.READY;
    }
    
    public void buildMine() throws IllegalCommandException{
        if(canMine() && canBuild()){
            player.getTreasury().decrement(Mine.getCost());
            setTimer(Mine.HP.getValue());
            Mine.create(position, player);
        } else {
            throw new IllegalCommandException(GameState.ERR_CANNOT_BUILD);
        }
    }
    
    public final void populateActions() {
        actions.add(new ActionCommand((Field targetField, Unit targetUnit)->this.buildMine(),"Build Mine"));
        actions.add(new ActionCommand((Field targetField, Unit targetUnit)->this.buildCastle(),"Build Castle"));
        actions.add(new ActionCommand((Field targetField, Unit targetUnit)->this.buildBarracks(),"Build Barracks"));
        actions.add(new MoveActionCommand((Field targetField, Unit targetUnit)->this.move(targetField),"Move"));
        actions.add(new AttackActionCommand((Field targetField, Unit targetUnit)->this.attack(targetUnit),"Attack"));
    }   
}
