/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.extractors;

import model.common.AttrLevel;
import model.interfaces.IMovable;
import model.player.Player;
import java.awt.Point;
import model.common.Stock;
import model.extractors.Extractor;

/**
 *
 * @author sonrisa
 */
public class Mine extends Extractor {
    
    private final Stock RESOURCES;

    public Mine(Point position, Player player) {
        super(position, player);
        
        this.RESOURCES = new Stock(999, 0, 0);
    }

    @Override
    public Stock getResources() {
        return this.RESOURCES;
    }
    
}
