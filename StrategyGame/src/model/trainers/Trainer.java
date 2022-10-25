/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.trainers;

import java.awt.Point;
import model.common.AttrLevel;
import model.common.Position;
import model.common.Stock;
import model.common.Unit;
import model.player.Player;

/**
 *
 * @author laszl
 */
public abstract class Trainer extends Unit{

    
    protected Trainer(int health, Point position, Player player) {
        super(health, position, player);
    }
    
}
