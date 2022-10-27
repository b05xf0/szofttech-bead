/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.trainers;


import model.common.AttrLevel;
import model.common.UnitState;
import model.common.UnitType;
import model.player.Player;
import model.field.Field;
import model.workers.Farmer;
import model.workers.Miner;
import model.workers.Woodcutter;

/**
 *
 * @author sonrisa
 */
public class Castle extends Trainer {
    public static final AttrLevel HP = AttrLevel.HIGH;
    
    
    public Castle(Field position, Player player) {
        super(HP.getValue() * BASEHEALTH, position, player);
        this.timer = HP.getValue();
        type = UnitType.CASTLE;
    }
    
    @Override
    public final int getHPValue() {
        return HP.getValue();
    }
    
    @Override
    public boolean isHQ() {
        return false;
    }
    
    @Override
    public boolean canTrainWorker() {
        return true;
    }
    
    public Miner trainMiner() {
        return new Miner(position, player);
    }
    
    public Woodcutter trainWoodcutter() {
        return new Woodcutter(position, player);
    }
    
    public Farmer trainFarmer() {
        return new Farmer(position, player);
    }
}
