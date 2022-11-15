package model.extractors;

import model.player.Player;
import model.common.Stock;
import model.field.Field;
import model.workers.Worker;

/**
 *
 * @author sonrisa
 */
public class Hut extends Extractor {
    
    public static void create(Field position, Player player){
        (new Hut(position, player)).add();
    }
    
    private Hut(Field position, Player player) {
        super(position, player);
    }

    @Override
    public Stock getResources() {
        return new Stock(0, 1, 0);
    }

    @Override
    public int getHC() {
        int counter = 0;
        for (Worker w : position.getWorkers()) {
            if (w.canCut()) {
                ++counter;
            }
        }
        return counter;
    }

}
