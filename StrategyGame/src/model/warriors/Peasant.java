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
public class Peasant extends Warrior implements IMovable {

    public Peasant(Position position, Player player) {
        super(AttrLevel.LOW.getValue(), position, player);
        
        this.HP = AttrLevel.LOW;
        this.ATTACK = AttrLevel.LOW;
        this.DEFENCE = AttrLevel.LOWEST;
        this.MOVEMENT = AttrLevel.MEDIUM;
    }

    @Override
    public Stock cost() {
        return new Stock(1, 0, 1);
    }

    @Override
    public boolean canFly() {
        return false;
    }
}
