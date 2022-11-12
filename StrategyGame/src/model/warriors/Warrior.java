/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.warriors;


import commands.ActionCommand;
import commands.AttackActionCommand;
import commands.IllegalCommandException;
import commands.MoveActionCommand;
import model.player.Player;
import model.common.Stock;
import model.common.Unit;
import model.common.UnitState;
import model.field.Field;
import model.interfaces.IMovable;

/**
 *
 * @author sonrisa
 */
public abstract class Warrior extends Unit implements IMovable {
    
    protected static final Stock BASECOST = new Stock(50,0,50);
    protected static final int BASEHEALTH = 50;
    
    protected Warrior(int health, Field position, Player player) {
        super(health, position, player);
        populateActions();
        add();
    }
    
    
    @Override
    public void move(Field targetField) throws IllegalCommandException{
        player.decrementAPs(targetField.getMovementCost());
        this.position.removeUnit(this);
        this.position = targetField;
        this.position.addUnit(this);
    }
    
    @Override
    public final void defend(IMovable m){
        this.health -= Math.max(m.getAttackValue() - getDefenceValue(), 0) ;
        if(this.health <= 0) this.state = UnitState.DEAD;
    }  
    
    @Override
    public void attack(Unit targetUnit) throws IllegalCommandException{
        targetUnit.defend(this);
    }
    
    
    @Override
    public boolean canFly(){ return false; }
    

    @Override
    public final void remove(){
        this.position.removeUnit(this);
        this.player.removeUnit(this);
    }

    @Override
    public final void add(){
        this.position.addUnit(this);
        this.player.addUnit(this);
    }
    public final void populateActions() {
        actions.add(new MoveActionCommand((Field targetField, Unit targetUnit)->this.move(targetField),"Move"));
        actions.add(new AttackActionCommand((Field targetField, Unit targetUnit)->this.attack(targetUnit),"Attack"));
    }
}
