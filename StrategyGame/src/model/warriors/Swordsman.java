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
public class Swordsman extends Warrior implements IMovable {

    public Swordsman(Position position, Player player) {
        super(AttrLevel.MEDIUM.getValue(), position, player);
        
        this.HP = AttrLevel.MEDIUM;
        this.ATTACK = AttrLevel.MEDIUM;
        this.DEFENCE = AttrLevel.HIGH;
        this.MOVEMENT = AttrLevel.LOW;
    }

    @Override
    public Stock cost() {
        return new Stock(2, 0, 2);
    }

    @Override
    public boolean canFly() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        
        if(!(o instanceof Swordsman)) return false;
        
        Swordsman sm = (Swordsman)o;
        
        return sm.getPosition().equals(this.getPosition())
                && sm.getPlayer().equals(this.getPlayer());
    }
}
