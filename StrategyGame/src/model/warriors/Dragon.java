/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.warriors;

import model.common.AttrLevel;
import model.common.UnitType;
import model.field.Field;
import model.interfaces.IMovable;
import model.player.Player;

/**
 *
 * @author sonrisa
 */
public class Dragon extends Warrior implements IMovable {
    
    public static final AttrLevel HP = AttrLevel.HIGHEST;
    public static final AttrLevel ATTACK = AttrLevel.HIGHEST;
    public static final AttrLevel DEFENCE = AttrLevel.HIGH;
    public static final AttrLevel MOVEMENT = AttrLevel.HIGHEST;   
  
    public Dragon(Field position, Player player) {
        super(HP.getValue() * BASEHEALTH, position, player);
        this.timer = HP.getValue();
        type = UnitType.DRAGON;
    }

    @Override
    public boolean canFly() {
        return true;
    }

    @Override
    public int getHPValue() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAttackValue() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public int getDefenceValue(){
        return DEFENCE.getValue();
    }

    @Override
    public int getMovementCost() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public int getRank(){return 4;}

    @Override
    public void populateActions() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
