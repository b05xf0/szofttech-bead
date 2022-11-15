package model.extractors;

import model.player.Player;
import model.common.Stock;
import model.field.Field;
import model.workers.Worker;

/**
 *
 * @author sonrisa
 */
public class Farm extends Extractor {

    public static void create(Field position, Player player){
        (new Farm(position, player)).add();
    }
    
    private Farm(Field position, Player player) {
        super(position, player);
    }

    @Override
    public Stock getResources() {
        return new Stock(0, 0, 1);
    }

    @Override
    public int getHC() {
        int counter = 0;
        for (Worker w : position.getWorkers()) {
            if (w.canFarm()) {
                ++counter;
            }
        }
        return counter;
    }
}
