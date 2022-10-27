/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.workers;

import model.common.UnitType;
import model.field.FieldType;
import model.extractors.Hut;
import model.field.Field;
import model.player.Player;


/**
 *
 * @author sonrisa
 */
public class Woodcutter extends Worker {

    public Woodcutter(Field position, Player player) {
        super(position, player);
        type = UnitType.WOODCUTTER;
    }
    
    @Override
    public boolean canCut(){
        return position.getType() == FieldType.FOREST;
    }
    
    public Hut buildHut(){
        setTimer(Hut.HP.getValue());
        return new Hut(position, player);
    }
}
