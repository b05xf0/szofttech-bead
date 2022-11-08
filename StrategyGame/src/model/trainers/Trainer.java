/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.trainers;


import model.common.Stock;
import model.common.Unit;
import model.common.UnitState;
import model.field.Field;
import model.interfaces.ICommand;
import model.interfaces.IMovable;
import model.player.Player;

/**
 *
 * @author sonrisa
 */
public abstract class Trainer extends Unit{
    
    private static final Stock BASECOST = new Stock(100,100,0);
    protected static final int BASEHEALTH = 200;
    
    protected Trainer(int health, Field position, Player player) {
        super(health, position, player);
    }
    
    public boolean isHQ() {
        return false;
    }
    
    @Override
    public final void defend(IMovable m){
        this.health -= m.getAttackValue();
        if(this.health <= 0) this.state = UnitState.DEAD;
    }
    
    @Override
    public final Stock getBaseCost(){ return BASECOST; }
    
}
