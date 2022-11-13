/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.interfaces;

import commands.IllegalCommandException;
import model.common.Unit;
import model.field.Field;

/**
 *
 * @author sonrisa
 */
public interface IMovable {
    public void move(Field targetField) throws IllegalCommandException;
    public void attack(Unit targetUnit) throws IllegalCommandException;
    public int getAttackValue();
    public int getDefenceValue();
    public int getMovementCost();
    public boolean canFly();
    public int getRank();
}
