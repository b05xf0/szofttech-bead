package model.warriors;

import commands.ActionCommand;
import static model.Configuration.*;
import commands.IllegalCommandException;
import model.GameState;
import model.player.Player;
import model.common.Unit;
import model.common.UnitState;
import model.field.Field;
import model.interfaces.IMovable;

/**
 *
 * @author sonrisa
 */
public abstract class Warrior extends Unit implements IMovable {

    boolean strikeBack;
    
    protected Warrior(int health, Field position, Player player) {
        super(health, position, player);
        strikeBack = false;
        populateActions();
    }

    @Override
    public final void move(Field targetField) throws IllegalCommandException {
        player.decreaseAPs(targetField.getMovementCost());
        //this.setTimer(1);
        this.position.removeUnit(this);
        this.position = targetField;
        this.position.addUnit(this);
    }

    @Override
    public final void defend(IMovable m) {
        this.health -= Math.max(m.getAttackValue() - getDefenceValue(), 0);
        if (this.health <= 0) {
            this.state = UnitState.DEAD;
            this.health = 0;
        } else if (strikeBack) {
            counterAttack((Unit) m);
        }
    }

    @Override
    public final void attack(Unit targetUnit) throws IllegalCommandException {
        this.setTimer(1);
        targetUnit.defend(this);
    }

    @Override
    public final boolean canStrikeBack(){
        return strikeBack;
    }
    
    @Override
    public final void setStrikeBack(boolean c){
        strikeBack = c;
    }
    
    @Override
    public final void counterAttack(Unit targetUnit){
        if(strikeBack){
            targetUnit.defend(this);
            strikeBack = false;
        }
    } 
    
    @Override
    public boolean canFly() {
        return false;
    }

    @Override
    public final void remove() {
        this.position.removeUnit(this);
        this.player.removeUnit(this);
    }

    @Override
    public final void add() {
        this.position.addUnit(this);
        this.player.addUnit(this);
    }
    
    public final void populateActions() {
        actions.add(new ActionCommand((Object o)->move((Field)o), "Move", GameState.MOVE_SELECT_TARGETFIELD));
        actions.add(new ActionCommand((Object o)->attack((Unit)o), "Attack", GameState.ATTACK_SELECT_TARGETFIELD));
    }
    
    @Override
    public final int getMovementCost(){
        return calcMovementCost(getMovement());
    }
    

    @Override
    public final int getAttackValue(){
        return calcAttackValue(getAttack());
    }
    
    @Override
    public final int getDefenceValue(){
        return calcDefenceValue(getDefence());
    }

    @Override
    public final String getStats(){
        return String.format("Attack: %s | Defence: %s | Movement: %s", getAttack().toString(),getDefence().toString(),getMovement().toString());
    }
 
}
