/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.interfaces;

import model.common.Unit;
import model.field.Field;

/**
 *
 * @author sonrisa
 */
public interface IMovable {
    public int move(Field position);
    public void attack(Unit unit);
    public int getAttackValue();
    public int getDefenceValue();
    public int getMovementCost();
    public boolean canFly();
}
