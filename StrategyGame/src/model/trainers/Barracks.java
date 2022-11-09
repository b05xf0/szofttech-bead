/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.trainers;


import model.common.AttrLevel;
import model.player.Player;
import model.common.UnitState;
import model.common.UnitType;
import model.field.Field;
import model.warriors.Dragon;
import model.warriors.Knight;
import model.warriors.Peasant;
import model.warriors.Swordsman;

/**
 *
 * @author sonrisa
 */

public class Barracks extends Trainer{
    public static final AttrLevel HP = AttrLevel.MEDIUM;
    
    public Barracks(Field position, Player player) {
        super(HP.getValue() * BASEHEALTH, position, player);
        this.timer = HP.getValue();
        type = UnitType.BARRACKS;
    }
   
    @Override
    public final int getHPValue() {
        return HP.getValue();
    }
    
    
    public boolean canTrainPeasant() {
        return true;
    }
    
    
    public boolean canTrainSwordsman() {
        return true;
    }
    
    
    public boolean canTrainKnight() {
        return true;

    }
    
   
    public boolean canTrainDragon() {
        return true;
    }
    
    public Peasant trainPeasant() {
        setTimer(Peasant.HP.getValue());
        return new Peasant(position, player);
    }
    
    public Swordsman trainSwordsman() {
        setTimer(Swordsman.HP.getValue());
        return new Swordsman(position, player);
    }
    
    public Knight trainKnight() {
        setTimer(Knight.HP.getValue());
        return new Knight(position, player);
    }
    
    public Dragon trainDragon() {
        setTimer(Dragon.HP.getValue());
        return new Dragon(position, player);
    }

    @Override
    public void populateActions() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
