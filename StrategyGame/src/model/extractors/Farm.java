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
public class Farm extends Extractor {
    
    private static final Stock RESOURCES = new Stock(0,0,10);
    
    public static Farm create(Field position, Player player){
        return new Farm(position, player);
    }

    public Farm(Field position, Player player) {
        super(position, player);
        type = UnitType.FARM;
    }

    @Override
    public Stock getResources() {
        return new Stock(RESOURCES);
    }

    @Override
    public int getHC() {
        int counter = 0;
        for(Worker w : position.getWorkers()){
            if(w.canFarm()) ++counter;
        }
        return counter;
    }
   
}
