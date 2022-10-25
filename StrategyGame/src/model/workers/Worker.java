/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.workers;

import model.common.AttrLevel;
import model.trainers.Barracks;
import model.trainers.Castle;
import model.field.FieldType;
import model.interfaces.IMovable;
import model.player.Player;
import java.awt.Point;
import model.common.Stock;
import model.common.Unit;

/**
 *
 * @author sonrisa
 */
public abstract class Worker extends Unit implements IMovable {
    private final AttrLevel HP;
    private final AttrLevel ATTACK;
    private final AttrLevel DEFENCE;
    private final AttrLevel MOVEMENT;

    protected Worker(int health, Point position, Player player) {
        super(health, position, player);
        
        this.HP = AttrLevel.LOW;
        this.ATTACK = AttrLevel.LOWEST;
        this.DEFENCE = AttrLevel.LOWEST;
        this.MOVEMENT = AttrLevel.MEDIUM;
    }
    
    public Stock cost(){
        return new Stock(1, 0, 2);
    }
    
    public int move(Point pos){
        this.position = pos;
        return this.MOVEMENT.getValue();
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
    
    public boolean canFly(){
        return false;
    }
    
    public boolean canMine(FieldType fieldType){
        return false;
    }
    
    public boolean canCut(FieldType fieldType){
        return false;
    }
    
    public boolean canFarm(FieldType fieldType){
        return false;
    }
    
    public Castle buildCastle(){
        return new Castle(this.getPosition(), this.getPlayer());
    }
    
    public Barracks buildBarracks(){
        return new Barracks(this.getPosition(), this.getPlayer());
    }
}
