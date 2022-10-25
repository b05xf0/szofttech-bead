/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.workers;

import model.common.AttrLevel;
import model.field.FieldType;
import model.extractors.Hut;
import model.player.Player;
import java.awt.Point;
import model.workers.Miner;
import model.workers.Worker;

/**
 *
 * @author sonrisa
 */
public class Woodcutter extends Worker {

    public Woodcutter(Point position, Player player) {
        super(AttrLevel.LOW.getValue(), position, player);
    }
    
    public boolean canCut(FieldType fieldType){
        return fieldType == FieldType.FOREST;
    }
    
    public Hut buildHut(){
        return new Hut(this.getPosition(), this.getPlayer());
    }
}
