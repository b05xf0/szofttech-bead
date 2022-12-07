/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.common;

import java.awt.Point;
import model.field.Field;
import model.player.Player;
import model.interfaces.IMovable;

/**
 *
 * @author sonrisa
 */
public abstract class Unit {
    protected final Player player;
    protected UnitState state;
    protected int timer;
    protected Field position;
    protected int health;
    protected UnitType type;
    
    protected Unit(int health, Field position, Player player){
        this.health = health;
        this.position = position;
        this.player = player;
        this.state = UnitState.BUSY;
    }
    
    public int getHealth(){
        return this.health;
    }
    
    public Field getPosition(){
        return this.position;
    }
    
    public Player getPlayer(){
        return this.player;
    }
    
    public UnitState getState(){
        return this.state;
    }
    
    public UnitType getType(){
        return this.type;
    }
    
    public void setTimer(int t){
        timer = t;
        state = (state != UnitState.DEAD && timer == 0) ? UnitState.READY : UnitState.BUSY;
    }
    
    public void decrementTimer(int t){
        if(this.timer > 0) setTimer(this.timer-1);
    }
    
    
    public abstract void defend(IMovable m);
    
    public Stock cost() {
        return (new Stock(getBaseCost())).multiply(getHPValue());
    }
    
    protected abstract Stock getBaseCost();
    public abstract int getHPValue();
    
    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }
}