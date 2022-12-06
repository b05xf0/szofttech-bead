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
public class Dragon extends Warrior implements IMovable {

    public final static Stock COST = calcMovableCost(DRAGON_HP);

    public static void create(Field position, Player player){
        (new Dragon(position, player)).add();
    }
    
    private Dragon(Field position, Player player) {
        super(calcMovableHealth(DRAGON_HP), position, player);
        this.timer = DRAGON_HP.getValue();
    }
    
    @Override
    public AttrLevel getAttack(){
        return DRAGON_A;
    }

    @Override
    public AttrLevel getDefence(){
        return DRAGON_D;
    }

    @Override
    public AttrLevel getMovement(){
        return DRAGON_M;
    }
    @Override
    public boolean canFly(){
        return true;
    }
    
    @Override
    public int getRank(){return 4;}
    
    @Override
    public final int getHP() {
        return calcMovableHealth(DRAGON_HP);
    }

}
