package model.warriors;

import static model.Configuration.*;
import model.common.AttrLevel;
import model.common.Stock;
import model.field.Field;
import model.interfaces.IMovable;
import model.player.Player;

/**
 *
 * @author sonrisa
 */
public class Swordsman extends Warrior implements IMovable {

    public final static Stock COST = calcMovableCost(SWORDSMAN_HP);

    public static void create(Field position, Player player){
        (new Swordsman(position, player)).add();
    }
    
    private Swordsman(Field position, Player player) {
        super(calcMovableHealth(SWORDSMAN_HP), position, player);
        this.timer = SWORDSMAN_HP.getValue();
    }
 
    @Override
    public AttrLevel getAttack(){
        return SWORDSMAN_A;
    }

    @Override
    public AttrLevel getDefence(){
        return SWORDSMAN_D;
    }

    @Override
    public AttrLevel getMovement(){
        return SWORDSMAN_M;
    }

    @Override
    public int getRank(){return 2;}

}
