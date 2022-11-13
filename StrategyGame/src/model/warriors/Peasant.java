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
public class Peasant extends Warrior implements IMovable {

    public static final AttrLevel HP = AttrLevel.LOW;
    public static final AttrLevel ATTACK = AttrLevel.LOW;
    public static final AttrLevel DEFENCE = AttrLevel.LOWEST;
    public static final AttrLevel MOVEMENT = AttrLevel.MEDIUM;   
    public final static Stock COST(){
        return (new Stock(BASECOST)).multiply(HP.getValue());
    }
    public static Peasant create(Field position, Player player){
        return new Peasant(position, player);
    }
    
    public Peasant(Field position, Player player) {
        super(HP.getValue() * BASEHEALTH, position, player);
        this.timer = HP.getValue();
        type = UnitType.PEASANT;
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
    public int getRank(){return 1;}

    
    @Override
    public final String getStats(){
        return String.format("Attack: %s | Defence: %s | Movement: %s", ATTACK.toString(),DEFENCE.toString(),MOVEMENT.toString());
    } 
}
