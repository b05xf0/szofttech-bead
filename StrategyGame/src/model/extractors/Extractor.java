/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.extractors;

import model.common.AttrLevel;
import model.player.Player;
import model.common.Position;
import model.common.Stock;
import model.common.Unit;
import model.interfaces.IMovable;

/**
 *
 * @author sonrisa
 */
public abstract class Extractor extends Unit{
    
    private AttrLevel HP;
    private int headCount;
    protected final AttrLevel DEFENCE;
    
    protected Extractor(Position position, Player player) {
        super(AttrLevel.LOW.getValue(), position, player);
        
        this.DEFENCE = AttrLevel.LOW;
        this.HP = AttrLevel.MEDIUM;
    }
    
    public Stock cost() {
        return new Stock(4, 3, 0);
    }
    
    public Stock extract (int x) {
        return this.getResources().multiply(x);
    }
    
    public void defend(IMovable m){
        this.health -= Math.abs(this.DEFENCE.getValue() - m.getAttackValue());
    }
    
    public abstract Stock getResources();
}
