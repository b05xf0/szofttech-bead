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
public class Knight extends Warrior implements IMovable {
    
    public Knight(Position position, Player player) {
        super(AttrLevel.HIGH.getValue(), position, player);
        
        this.HP = AttrLevel.HIGH;
        this.ATTACK = AttrLevel.HIGH;
        this.DEFENCE = AttrLevel.HIGH;
        this.MOVEMENT = AttrLevel.HIGH;
    }

    @Override
    public Stock cost() {
        return new Stock(5, 0, 4);
    }

    @Override
    public boolean canFly() {
        return false;
    }
}
