/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.workers;

import commands.ActionCommand;
import commands.IllegalCommandException;
import model.GameState;
import model.common.AttrLevel;
import model.trainers.Barracks;
import model.trainers.Castle;
import model.interfaces.IMovable;
import model.player.Player;
import model.common.Stock;
import model.common.Unit;
import model.common.UnitState;
import model.field.Field;
import model.field.FieldType;

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
   
    public final static Stock COST(){
        return (new Stock(BASECOST)).multiply(HP.getValue());
    }
    
    protected Worker(Field position, Player player) {
        super(HP.getValue() * BASEHEALTH, position, player);
        this.timer = HP.getValue();
        add();
    }

    @Override
    public final int getMovementCost(){
        return (10 - MOVEMENT.getValue());
    }
   
    @Override
    public void move(Field targetField) throws IllegalCommandException{
        System.out.println(targetField.getMovementCost());
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
    public int getAttackValue(){
        return ATTACK.getValue()*10;
    }
    
    @Override
    public int getDefenceValue(){
        return DEFENCE.getValue()*5;
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

    public boolean canBuild(){
        return position.getTrainer() == null
               && position.getExtractor() == null
               && state == UnitState.READY;
    }


    public boolean canBuildTrainer(){
        return canBuild() && position.getType() == FieldType.GRASS;
    }
    
    
    public boolean canBuildCastle(){
        return canBuildTrainer() && !player.hasHQ();
    }
    
    
    
    public void buildCastle() throws IllegalCommandException {
        if(canBuildCastle()){
            player.getTreasury().decrement(Castle.getCost());
            setTimer(Castle.HP.getValue());
            Castle.create(position, player);
        } else {
            throw new IllegalCommandException(GameState.ERR_CANNOT_BUILD);
        }
    }
    
    public void buildBarracks() throws IllegalCommandException {
        if(canBuildTrainer()){
            player.getTreasury().decrement(Barracks.getCost());
            setTimer(Barracks.HP.getValue());
            Barracks.create(position, player);
        } else {
            throw new IllegalCommandException(GameState.ERR_CANNOT_BUILD);
        }
    }

   
    @Override
    public int getRank() { return 0; }

    @Override
    public final String getStats(){
        return String.format("Attack: %s | Defence: %s | Movement: %s", ATTACK.toString(),DEFENCE.toString(),MOVEMENT.toString());
    }
    
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
}    
    
