/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.workers;

import model.common.AttrLevel;
import model.field.FieldType;
import model.extractors.Mine;
import model.player.Player;
import model.common.Position;
import model.workers.Worker;

/**
 *
 * @author sonrisa
 */
public class Miner extends Worker {
    
    public Miner(Position position, Player player){
        super(AttrLevel.LOW.getValue(), position, player);
    }
    
    @Override
    public boolean canMine(FieldType fieldType){
        return fieldType == FieldType.GOLD;
    }
    
    public Mine buildMine(){
        return new Mine(this.getPosition(), this.getPlayer());
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        
        if(!(o instanceof Miner)) return false;
        
        Miner m = (Miner)o;
        
        return m.getPosition().equals(this.getPosition())
                && m.getPlayer().equals(this.getPlayer());
    }
}
