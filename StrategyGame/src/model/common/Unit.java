/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.common;

import java.awt.Point;
import model.player.Player;
import model.common.Stock;
import model.interfaces.IMovable;

/**
 *
 * @author sonrisa
 */
public abstract class Unit {
    private final Player player;
    private UnitState state;
    private int stateTimer;
    protected Point position;
    protected int health;
    
    protected Unit(int health, Point position, Player player){
        this.health = health;
        this.position = new Point(position);
        this.player = player;
        
        this.state = UnitState.READY;
    }
    
    public int getHealth(){
        return this.health;
    }
    
    public Point getPosition(){
        return this.position;
    }
    
    public Player getPlayer(){
        return this.player;
    }
    
    public UnitState getState(){
        return this.state;
    }
    
    public abstract void defend(IMovable m);
    
    public abstract Stock cost();
}
