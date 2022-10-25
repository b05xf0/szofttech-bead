/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.trainers;


import model.common.AttrLevel;
import java.awt.Point;
import model.common.Unit;
import model.interfaces.IMovable;
import model.player.Player;

/**
 *
 * @author sonrisa
 */
public abstract class Trainer extends Unit{
    
    protected AttrLevel HP;
    protected AttrLevel DEFENCE;
        
    protected Trainer(int health, Point position, Player player) {
        super(health, position, player);
    }
    
    public void defend(IMovable m){
        this.health -= Math.abs(this.DEFENCE.getValue() - m.getAttackValue());
    }
    
    public boolean isHQ() {
        return false;
    }
    public boolean canTrainWorker() {
        return false;
    }
    
    public boolean canTrainPeasant() {
        return false;
    }
    
    public boolean canTrainSwordsman() {
        return false;
    }
    
    public boolean canTrainKnight() {
        return false;
    }
    
    public boolean canTrainDragon() {
        return false;
    }
}
