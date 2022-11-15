package model.interfaces;

import commands.IllegalCommandException;
import model.common.AttrLevel;
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
    
    public AttrLevel getAttack();

    public AttrLevel getDefence();

    public AttrLevel getMovement();
    
    public boolean canFly();

    public int getRank();
    
    public boolean canStrikeBack();
    
    public void counterAttack(Unit targetUnit);
    
    public void setStrikeBack(boolean c);
}
