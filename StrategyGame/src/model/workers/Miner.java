/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.workers;

import model.common.UnitType;
import model.field.FieldType;
import model.extractors.Mine;
import model.player.Player;
import model.field.Field;

/**
 *
 * @author sonrisa
 */
public class Miner extends Worker {
    
    public Miner(Field position, Player player){
        super(position, player);
        type = UnitType.MINER;
    }
    
    @Override
    public boolean canMine(){
        return position.getType() == FieldType.GOLD;
    }
    
    public Mine buildMine(){
        setTimer(Mine.HP.getValue());
        return new Mine(position, player);
    }
}
