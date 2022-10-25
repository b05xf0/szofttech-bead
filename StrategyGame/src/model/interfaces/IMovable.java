/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.interfaces;

import java.awt.Point;
import model.common.Unit;

/**
 *
 * @author sonrisa
 */
public interface IMovable {
    public int move(Point position);
    public void attack(Unit unit);
    public int getAttackValue();
    public int getMovementCost();
    public boolean canFly();
}
