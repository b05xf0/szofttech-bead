/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.extractors;
import model.player.Player;
import model.common.Stock;
import model.common.UnitType;
import model.field.Field;
import model.workers.Worker;

/**
 *
 * @author sonrisa
 */
public class Hut extends Extractor {
    private static final Stock RESOURCES = new Stock(0,10,0);

    public static Hut create(Field position, Player player){
        return new Hut(position, player);
    }
    
    public Hut(Field position, Player player) {
        super(position, player);
        type = UnitType.HUT;
    }

    @Override
    public Stock getResources() {
        return new Stock(RESOURCES);
    }

    @Override
    public int getHC() {
        int counter = 0;
        for(Worker w : position.getWorkers()){
            if(w.canCut()) ++counter;
        }
        return counter;
    }

}
