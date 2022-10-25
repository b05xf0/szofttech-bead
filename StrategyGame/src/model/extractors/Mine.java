/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.extractors;

import java.awt.Point;
import model.common.AttrLevel;
import model.interfaces.IMovable;
import model.player.Player;
import model.common.Position;
import model.common.Stock;
import model.extractors.Extractor;

/**
 *
 * @author sonrisa
 */
public class Mine extends Extractor {
    
    private final Stock RESOURCES;

    public Mine(Point position, Player player) {
        super(AttrLevel.MEDIUM.getValue(), position, player);
        
        this.RESOURCES = new Stock(999, 0, 0);
    }

    @Override
    protected Stock getResources() {
        return this.RESOURCES;
    }

    @Override
    public void defend(IMovable m) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Stock extract() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
