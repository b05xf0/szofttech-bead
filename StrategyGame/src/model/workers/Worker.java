package model.workers;

import static model.Configuration.*;
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

    public final static Stock COST = calcMovableCost(WORKER_HP);
    
    private boolean strikeBack;
    
    protected Worker(Field position, Player player) {
        super(calcMovableHealth(WORKER_HP), position, player);
        this.timer = WORKER_HP.getValue();
    }

    @Override
    public final int getMovementCost() {
        return calcMovementCost(WORKER_M);
    }

    @Override
    public final int getAttackValue() {
        return calcAttackValue(WORKER_A);
    }

    @Override
    public final int getDefenceValue() {
        return calcDefenceValue(WORKER_D);
    }
    
    @Override
    public AttrLevel getAttack(){
        return WORKER_A;
    }

    @Override
    public AttrLevel getDefence(){
        return WORKER_D;
    }

    @Override
    public AttrLevel getMovement(){
        return WORKER_M;
    }
   
    @Override
    public final void move(Field targetField) throws IllegalCommandException {
        player.decreaseAPs(targetField.getMovementCost());
        this.setTimer(1);
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

    public boolean canMine() {
        return false;
    }

    public boolean canCut() {
        return false;
    }

    public boolean canFarm() {
        return false;
    }

    public final boolean canBuild() {
        return position.getTrainer() == null
                && position.getExtractor() == null
                && state == UnitState.READY;
    }

    public final boolean canBuildTrainer() {
        return canBuild() && position.getType() == FieldType.GRASS;
    }

    public final boolean canBuildCastle() {
        return canBuildTrainer() && !player.hasHQ();
    }

    public final void buildCastle() throws IllegalCommandException {
        if (canBuildCastle()) {
            player.getTreasury().decrease(Castle.COST);
            setTimer(CASTLE_HP.getValue());
            Castle.create(position, player);
        } else {
            throw new IllegalCommandException(GameState.ERR_CANNOT_BUILD);
        }
    }

    public final void buildBarracks() throws IllegalCommandException {
        if (canBuildTrainer()) {
            player.getTreasury().decrease(Barracks.COST);
            setTimer(BARRACKS_HP.getValue());
            Barracks.create(position, player);
        } else {
            throw new IllegalCommandException(GameState.ERR_CANNOT_BUILD);
        }
    }

    @Override
    public final int getRank() {
        return 0;
    }

    @Override
    public final String getStats() {
        return String.format("Attack: %s | Defence: %s | Movement: %s", getAttack().toString(),getDefence().toString(),getMovement().toString());
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

}
