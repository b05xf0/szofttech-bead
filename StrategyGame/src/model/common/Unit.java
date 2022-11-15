package model.common;

import commands.ActionCommand;
import java.util.LinkedList;
import java.util.List;
import model.field.Field;
import model.player.Player;
import model.interfaces.IMovable;
import static model.common.UnitState.*;

/**
 *
 * @author sonrisa
 */
public abstract class Unit {

    protected final Player player;
    protected Field position;
    protected UnitState state;
    protected int timer;
    protected int health;
    protected List<ActionCommand> actions = new LinkedList<>();
    
    protected Unit(int health, Field position, Player player) {
        this.health = health;
        this.position = position;
        this.player = player;
        this.state = BUSY;
    }

    public final List<ActionCommand> getActions(){
        //initActions();
        return actions;
    }
 
    public final void initActions(){
        for(ActionCommand a : actions) {
            a.setTargetField(null);
            a.setTargetUnit(null);
        }
    }
    
    public final int getHealth() {
        return this.health;
    }

    public final Field getPosition() {
        return this.position;
    }

    public final Player getPlayer() {
        return this.player;
    }

    public final UnitState getState() {
        return state;
    }

    public final String getStateWithTimer() {
        return timer > 0
                ? String.format("%s(%d)", state, timer)
                : state.toString();
    }

    public final String getType() {
        return this.getClass().getSimpleName();
    }

    public final void setTimer(int t) {
        timer = t;
        state = (state != DEAD && timer == 0) ? READY : BUSY;
    }

    public final void decrementTimer() {
        if (this.timer > 0) {
            setTimer(this.timer - 1);
        }
    }

    public boolean canStrikeBack(){
        return false;
    }
    
    public abstract void defend(IMovable m);

    public abstract void remove();

    public abstract void add();

    public abstract String getStats();

    @Override
    public String toString() {
        return String.format("%s", this.getClass().getSimpleName());
    }
}
