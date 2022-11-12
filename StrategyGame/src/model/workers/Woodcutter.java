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
import model.extractors.Hut;
import model.field.Field;
import model.player.Player;


/**
 *
 * @author sonrisa
 */
public class Woodcutter extends Worker {

    public static Woodcutter create(Field position, Player player){
        return new Woodcutter(position, player);
    }    
    
    public Woodcutter(Field position, Player player) {
        super(position, player);
        type = UnitType.WOODCUTTER;
        populateActions();
    }
    
    @Override
    public boolean canCut(){
        return position.getType() == FieldType.FOREST
               && state == UnitState.READY;
    }
    
    public void buildHut() throws IllegalCommandException{
        if(canCut() && canBuild()){
            player.getTreasury().decrement(Hut.getCost());
            setTimer(Hut.HP.getValue());
            Hut.create(position, player);
        } else {
            throw new IllegalCommandException(GameState.ERR_CANNOT_BUILD);
        }
    }
    
    public final void populateActions() {
        actions.add(new ActionCommand((Field targetField, Unit targetUnit)->this.buildHut(),"Build Hut"));
        actions.add(new ActionCommand((Field targetField, Unit targetUnit)->this.buildCastle(),"Build Castle"));
        actions.add(new ActionCommand((Field targetField, Unit targetUnit)->this.buildBarracks(),"Build Barracks"));
        actions.add(new MoveActionCommand((Field targetField, Unit targetUnit)->this.move(targetField),"Move"));
        actions.add(new AttackActionCommand((Field targetField, Unit targetUnit)->this.attack(targetUnit),"Attack"));
    }   

}
