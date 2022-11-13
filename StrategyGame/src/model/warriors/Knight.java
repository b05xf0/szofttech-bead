/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.warriors;

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
public class Knight extends Warrior implements IMovable {
    
    public static final AttrLevel HP = AttrLevel.HIGH;
    public static final AttrLevel ATTACK = AttrLevel.HIGH;
    public static final AttrLevel DEFENCE = AttrLevel.HIGH;
    public static final AttrLevel MOVEMENT = AttrLevel.HIGH;   
    public final static Stock COST(){
        return (new Stock(BASECOST)).multiply(HP.getValue());
    }
    public static Knight create(Field position, Player player){
        return new Knight(position, player);
    }    
    public Knight(Field position, Player player) {
        super(HP.getValue() * BASEHEALTH, position, player);
        this.timer = HP.getValue();
        type = UnitType.KNIGHT;
    }

    @Override
    public final int getMovementCost(){
        return (10 - MOVEMENT.getValue());
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
    public int getRank(){return 3;}

    
    @Override
    public final String getStats(){
        return String.format("Attack: %s | Defence: %s | Movement: %s", ATTACK.toString(),DEFENCE.toString(),MOVEMENT.toString());
    } 
}
