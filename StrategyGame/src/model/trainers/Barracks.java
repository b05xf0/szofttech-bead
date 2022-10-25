/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.trainers;

import model.common.AttrLevel;
import model.player.Player;
import model.common.Position;
import model.common.Stock;
import model.warriors.Dragon;
import model.warriors.Knight;
import model.warriors.Peasant;
import model.warriors.Swordsman;

/**
 *
 * @author sonrisa
 */
public class Barracks extends Trainer{
    
    public Barracks(Position position, Player player) {
        super(AttrLevel.MEDIUM.getValue(), position, player);
        
        this.HP = AttrLevel.MEDIUM;
        this.DEFENCE = AttrLevel.MEDIUM;
    }
   
    public Stock cost() {
        return new Stock(5, 6, 0);
    }
    
    @Override
    public boolean canTrainPeasant() {
        return true;
    }
    
    @Override
    public boolean canTrainSwordsman() {
        return true;
    }
    
    @Override
    public boolean canTrainKnight() {
        return true;
    }
    
    @Override
    public boolean canTrainDragon() {
        return true;
    }
    
    public Peasant trainPeasant() {
        return new Peasant(this.getPosition(), this.getPlayer());
    }
    
    public Swordsman trainSwordsman() {
        return new Swordsman(this.getPosition(), this.getPlayer());
    }
    
    public Knight trainKnight() {
        return new Knight(this.getPosition(), this.getPlayer());
    }
    
    public Dragon trainDragon() {
        return new Dragon(this.getPosition(), this.getPlayer());
    }
}
