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

    public final static Stock getCost(){
        return (new Stock(BASECOST)).multiply(HP.getValue());
    }
    
    protected Extractor(Field position, Player player) {
        super(HP.getValue() * BASEHEALTH, position, player);
        this.timer = HP.getValue();
        add();
    }
    
    public void extract() {
        player.getTreasury().increment(getResources().multiply(Math.min(getHC(),CAPACITY)));
    }
    
    @Override
    public final void defend(IMovable m){
        this.health -= m.getAttackValue();
        if(this.health <= 0) this.state = UnitState.DEAD;
    }

    public abstract Stock getResources();

    public abstract int getHC();
    
    @Override
    public final String getStats(){
        return String.format("HC: %d", getHC());
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
