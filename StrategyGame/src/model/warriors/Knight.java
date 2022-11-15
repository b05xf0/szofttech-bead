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
public class Knight extends Warrior implements IMovable {

    public final static Stock COST = calcMovableCost(KNIGHT_HP);
 
    public static void create(Field position, Player player){
        (new Knight(position, player)).add();
    }   
    
    private Knight(Field position, Player player) {
        super(calcMovableHealth(KNIGHT_HP), position, player);
        this.timer = KNIGHT_HP.getValue();
    }
    
    @Override
    public AttrLevel getAttack(){
        return KNIGHT_A;
    }

    @Override
    public AttrLevel getDefence(){
        return KNIGHT_D;
    }

    @Override
    public AttrLevel getMovement(){
        return KNIGHT_M;
    }        
    @Override
    public int getRank(){return 3;}

}
