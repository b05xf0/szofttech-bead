/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.trainers;

import model.common.AttrLevel;
import model.player.Player;
import java.awt.Point;
import model.common.Stock;
import model.workers.Farmer;
import model.workers.Miner;
import model.workers.Woodcutter;

/**
 *
 * @author sonrisa
 */
public class Castle extends Trainer {
    
    
    public Castle(Point position, Player player) {
        super(AttrLevel.HIGH.getValue(), position, player);
    }
    
    public Stock cost() {
        return new Stock(10, 8, 0);
    }
    
    @Override
    public boolean isHQ() {
        return false;
    }
    
    @Override
    public boolean canTrainWorker() {
        return false;
    }
    
    public Miner trainMiner() {
        return new Miner(this.getPosition(), this.getPlayer());
    }
    
    public Woodcutter trainWoodcutter() {
        return new Woodcutter(this.getPosition(), this.getPlayer());
    }
    
    public Farmer trainFarmer() {
        return new Farmer(this.getPosition(), this.getPlayer());
    }
}
