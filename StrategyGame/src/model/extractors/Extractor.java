/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.extractors;

import java.awt.Point;
import model.common.AttrLevel;
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
public abstract class Extractor extends Unit{
    
    private static final Stock BASECOST = new Stock(100,100,0);
    private static final int CAPACITY = 5;
    private static final int BASEHEALTH = 200;
    
    public static final AttrLevel HP = AttrLevel.MEDIUM;

    protected Extractor(Field position, Player player) {
        super(HP.getValue() * BASEHEALTH, position, player);
        this.timer = HP.getValue();
    }
    
    public Stock extract() {
        return getResources().multiply(Math.min(getHC(),CAPACITY));
    }
    
    @Override
    public final void defend(IMovable m){
        this.health -= m.getAttackValue();
        if(this.health <= 0) this.state = UnitState.DEAD;
    }

    @Override
    public final Stock getBaseCost(){ return BASECOST; }
    
    @Override
    public final int getHPValue() {
        return HP.getValue();
    }
    
    public abstract Stock getResources();
    public abstract int getHC();
    
}
