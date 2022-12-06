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
public class Peasant extends Warrior implements IMovable {

    public final static Stock COST = calcMovableCost(PEASANT_HP);

    public static void create(Field position, Player player){
        (new Peasant(position, player)).add();
    }
    
    private Peasant(Field position, Player player) {
        super(calcMovableHealth(PEASANT_HP), position, player);
        this.timer = PEASANT_HP.getValue();
    }
    
    @Override
    public AttrLevel getAttack(){
        return PEASANT_A;
    }

    @Override
    public AttrLevel getDefence(){
        return PEASANT_D;
    }

    @Override
    public AttrLevel getMovement(){
        return PEASANT_M;
    }        
    @Override
    public int getRank(){return 1;}
    
    @Override
    public final int getHP() {
        return calcMovableHealth(PEASANT_HP);
    }

}
