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

/**
 *
 * @author sonrisa
 */
public abstract class Extractor extends Unit {

    private final AttrLevel HP;
    
    protected Extractor(int health, Point position, Player player) {
        super(health, position, player);
        
        this.HP = AttrLevel.MEDIUM; // TODO
    }
    
    protected abstract Stock getResources();
    
    public Stock cost(){
        return new Stock(3, 4, 0);
    }
    
    public abstract Stock extract();
}
