package model.extractors;

import commands.ActionCommand;
import java.util.LinkedList;
import java.util.List;
import static model.Configuration.*;
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
public abstract class Extractor extends Unit {
    
    public final static Stock COST = calcBuildingCost(EXTRACTOR_HP);
 
    protected Extractor(Field position, Player player) {
        super(calcBuildingHealth(EXTRACTOR_HP), position, player);
        this.timer = EXTRACTOR_HP.getValue();
    }

    public void extract() {
        player.getTreasury().increase(getResources().multiply(Math.min(getHC(), EXTRACTOR_CAP) * EXTRACTOR_PRODUCTIVITY));
    }

    @Override
    public final void defend(IMovable m) {
        this.health -= m.getAttackValue();
        if (this.health <= 0) {
            this.state = UnitState.DEAD;
        }
    }

    public abstract Stock getResources();

    public abstract int getHC();

    @Override
    public final String getStats() {
        return String.format("Headcount: %d", getHC());
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
