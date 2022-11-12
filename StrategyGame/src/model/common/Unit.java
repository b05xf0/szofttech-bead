/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.common;

import commands.ActionCommand;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import model.field.Field;
import model.interfaces.ICommand;
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
    protected List<ActionCommand> actions; 
    
    protected Unit(int health, Field position, Player player){
        this.health = health;
        this.position = position;
        this.player = player;
        this.state = UnitState.BUSY;
        this.actions = new LinkedList<>();
    }
    
    public List<ActionCommand> getActions(){
        return this.actions;
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
    
    public UnitState getState(){ return state; }
    
    public String getStateDisplay(){
        return timer > 0 
               ? String.format("%s(%d)", state, timer) 
               : state.toString();
    }
    
    public UnitType getType(){
        return this.type;
    }
    
    public void setTimer(int t){
        timer = t;
        state = (state != UnitState.DEAD && timer == 0) ? UnitState.READY : UnitState.BUSY;
    }
    
    public void decrementTimer(){
        if(this.timer > 0) setTimer(this.timer - 1);
    }
    
    
    public abstract void defend(IMovable m);
    
    public abstract void remove();

    public abstract void add();

    public abstract String getStats();
    
    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }
}
