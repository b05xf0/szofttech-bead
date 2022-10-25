/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.warriors;

import model.common.AttrLevel;
import model.common.Position;
import model.common.Stock;
import model.interfaces.IMovable;
import model.player.Player;

/**
 *
 * @author sonrisa
 */
public class Dragon extends Warrior implements IMovable {
    
    public Dragon(Position position, Player player) {
        super(AttrLevel.HIGHEST.getValue(), position, player);
        
        this.HP = AttrLevel.HIGHEST;
        this.ATTACK = AttrLevel.HIGHEST;
        this.DEFENCE = AttrLevel.HIGH;
        this.MOVEMENT = AttrLevel.HIGHEST;
    }

    @Override
    public Stock cost() {
        return new Stock(10, 0, 13);
    }

    @Override
    public boolean canFly() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        
        if(!(o instanceof Dragon)) return false;
        
        Dragon d = (Dragon)o;
        
        return d.getPosition().equals(this.getPosition())
                && d.getPlayer().equals(this.getPlayer());
    }
}
