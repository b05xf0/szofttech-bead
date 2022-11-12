/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.warriors;

import commands.ActionCommand;
import model.common.AttrLevel;
import model.common.Stock;
import model.common.UnitType;
import model.field.Field;
import model.interfaces.IMovable;
import model.player.Player;

/**
 *
 * @author sonrisa
 */
public class Swordsman extends Warrior implements IMovable {

    public static final AttrLevel HP = AttrLevel.MEDIUM;
    public static final AttrLevel ATTACK = AttrLevel.MEDIUM;
    public static final AttrLevel DEFENCE = AttrLevel.HIGH;
    public static final AttrLevel MOVEMENT = AttrLevel.LOW;   
    public final static Stock getCost(){
        return (new Stock(BASECOST)).multiply(HP.getValue());
    }
    public static Swordsman create(Field position, Player player){
        return new Swordsman(position, player);
    }
    
    public Swordsman(Field position, Player player) {
        super(HP.getValue() * BASEHEALTH, position, player);
        this.timer = HP.getValue();
        type = UnitType.SWORDSMAN;
    }
    

    @Override
    public boolean canFly() {
        return false;
    }

    @Override
    public int getAttackValue(){
        return ATTACK.getValue()*10;
    }
    
    @Override
    public int getDefenceValue(){
        return DEFENCE.getValue()*5;
    }
    
    
    @Override
    public int getRank(){return 2;}


    @Override
    public final String getStats(){
        return String.format("Attack: %s | Defence: %s | Movement: %s", ATTACK.toString(),DEFENCE.toString(),MOVEMENT.toString());
    }  
}
