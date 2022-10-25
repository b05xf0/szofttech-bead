/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.extractors;

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
public class Hut extends Extractor {

    private final Stock RESOURCES;
    
    public Hut(Position position, Player player) {
        super(position, player);
        
        this.RESOURCES = new Stock(0, 999, 0);
    }

    @Override
    public Stock getResources() {
        return this.RESOURCES;
    }
}
