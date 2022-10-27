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
public class Swordsman extends Warrior implements IMovable {

    public static final AttrLevel HP = AttrLevel.MEDIUM;
    public static final AttrLevel ATTACK = AttrLevel.MEDIUM;
    public static final AttrLevel DEFENCE = AttrLevel.HIGH;
    public static final AttrLevel MOVEMENT = AttrLevel.LOW;   
    
    public Swordsman(Field position, Player player) {
        super(HP.getValue() * BASEHEALTH, position, player);
        this.timer = HP.getValue();
        type = UnitType.SWORDSMAN;
    }

    @Override
    public int getHPValue() {
        return HP.getValue();
    }

    @Override
    public boolean canFly() {
        return false;
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
    public int getRank(){return 2;}
}
