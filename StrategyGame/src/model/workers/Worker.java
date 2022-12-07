/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.workers;

import model.common.AttrLevel;
import model.trainers.Barracks;
import model.trainers.Castle;
import model.interfaces.IMovable;
import model.player.Player;
import model.common.Stock;
import model.common.Unit;
import model.common.UnitState;
import model.field.Field;

/**
 *
 * @author sonrisa
 */
public abstract class Worker extends Unit implements IMovable {
    
    private static final Stock BASECOST = new Stock(50,0,50);
    private static final int BASEHEALTH = 50;
    public final static AttrLevel HP = AttrLevel.LOW;;
    public final static AttrLevel ATTACK = AttrLevel.LOWEST;
    public final static AttrLevel DEFENCE = AttrLevel.LOWEST;
    public final static AttrLevel MOVEMENT = AttrLevel.MEDIUM;

    protected Worker(Field position, Player player) {
        super(HP.getValue() * BASEHEALTH, position, player);
        this.timer = HP.getValue();
    }
    
    @Override
    public int move(Field pos){
        this.position = pos;
        return getMovementCost();
    }
    
    @Override
    public final void defend(IMovable m){
        this.health -= Math.min(m.getAttackValue() - getDefenceValue(), 0) ;
        if(this.health <= 0) this.state = UnitState.DEAD;
    }   
    
    @Override
    public void attack(Unit u){
        u.defend(this);
    }
    
    @Override
    public int getAttackValue(){
        return ATTACK.getValue();
    }
    
    @Override
    public int getDefenceValue(){
        return DEFENCE.getValue();
    }
    
    @Override
    public int getMovementCost(){
        return MOVEMENT.getValue();
    }
    
    @Override
    public boolean canFly(){
        return false;
    }
    
    public boolean canMine(){
        return false;
    }
    
    public boolean canCut(){
        return false;
    }
    
    public boolean canFarm(){
        return false;
    }
    
    public Castle buildCastle(){
        setTimer(Castle.HP.getValue());
        return new Castle(position, player);
    }
    
    public Barracks buildBarracks(){
        setTimer(Barracks.HP.getValue());
        return new Barracks(position,player);
    }
    @Override
    public final Stock getBaseCost(){ return BASECOST; }
    
    @Override
    public final int getHPValue(){ return HP.getValue(); }
    
    
}

