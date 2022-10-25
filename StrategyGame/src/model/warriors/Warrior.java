/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.warriors;

import java.awt.Point;
import model.common.AttrLevel;
import model.player.Player;
import java.awt.Point;
import model.common.Stock;
import model.common.Unit;
import model.interfaces.IMovable;

/**
 *
 * @author sonrisa
 */
public abstract class Warrior extends Unit implements IMovable {
    
    protected AttrLevel HP;
    protected AttrLevel ATTACK;
    protected AttrLevel DEFENCE;
    protected AttrLevel MOVEMENT;

    protected Warrior(int health, Point position, Player player) {
        super(health, position, player);
    }
    
    public void defend(IMovable m){
        this.health -= Math.abs(this.DEFENCE.getValue() - m.getAttackValue());
    }
    
    public void attack(Unit u){
        u.defend(this);
    }
    
    public int getAttackValue(){
        return this.ATTACK.getValue();
    }
    
    public int getMovementCost(){
        return this.MOVEMENT.getValue();
    }
    
    public int move(Point pos){
        this.position = pos;
        return this.MOVEMENT.getValue();
    }
    
    public abstract Stock cost();
        
    public abstract boolean canFly();
}
