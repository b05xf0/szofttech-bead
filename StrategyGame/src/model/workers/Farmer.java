/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.workers;

import model.common.AttrLevel;
import model.extractors.Farm;
import model.field.FieldType;
import model.player.Player;
import model.common.Position;
import model.workers.Worker;

/**
 *
 * @author sonrisa
 */
public class Farmer extends Worker {

    public Farmer(Position position, Player player) {
        super(AttrLevel.LOW.getValue(), position, player);
    }
    
    public boolean canFarm(FieldType fieldType){
        return fieldType == FieldType.GRASS;
    }
    
    public Farm buildFarm(){
        return new Farm(this.getPosition(), this.getPlayer());
    }
}
