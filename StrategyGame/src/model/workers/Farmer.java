/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.workers;

import model.common.UnitType;
import model.extractors.Farm;
import model.field.FieldType;
import model.player.Player;
import model.field.Field;


/**
 *
 * @author sonrisa
 */
public class Farmer extends Worker {

    public Farmer(Field position, Player player) {
        super(position, player);
        type = UnitType.FARMER;
    }
    
    @Override
    public boolean canFarm(){
        return position.getType() == FieldType.GRASS;
    }
    
    public Farm buildFarm(){
        setTimer(Farm.HP.getValue());
        return new Farm(position, player);
    }

    @Override
    public void populateActions() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
