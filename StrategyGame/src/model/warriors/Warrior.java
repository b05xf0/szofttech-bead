/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.warriors;

import model.player.Player;
import model.common.Stock;
import model.common.Unit;
import model.common.UnitState;
import model.field.Field;
import model.interfaces.IMovable;
import static model.workers.Worker.ATTACK;
import static model.workers.Worker.DEFENCE;
import static model.workers.Worker.MOVEMENT;

/**
 *
 * @author sonrisa
 */
public abstract class Warrior extends Unit implements IMovable {
    
    private static final Stock BASECOST = new Stock(50,0,50);
    protected static final int BASEHEALTH = 50;
    
    protected Warrior(int health, Field position, Player player) {
        super(health, position, player);
    }
    
    @Override
    public void attack(Unit u){
        u.defend(this);
    }
    
    @Override
    public final void defend(IMovable m){
        this.health -= Math.min(m.getAttackValue() - getDefenceValue(), 0) ;
        if(this.health <= 0) this.state = UnitState.DEAD;
    }
  
    @Override
    public int move(Field pos){
        this.position = pos;
        return getMovementCost();
    }
    
    @Override
    public boolean canFly(){ return false; }
    
    @Override
    public final Stock getBaseCost(){ return BASECOST; }
    
    public abstract int getRank();
}
