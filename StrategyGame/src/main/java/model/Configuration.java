package model;

import model.common.AttrLevel;
import static model.common.AttrLevel.*;
import model.common.Stock;

/**
 *
 * @author laszl
 */
public class Configuration {
  
    public final static int
            
// starting conditions

// treasury
    
    INIT_GOLD = 2000, INIT_LUMBER = 2000, INIT_FOOD = 2000,
    
// units
            
    INIT_MINERS = 1, INIT_WOODCUTTERS = 1, INIT_FARMERS = 1,
    
    INIT_PEASANTS = 1, INIT_SWORDSMEN = 1, INIT_KNIGHTS = 1, INIT_DRAGONS = 1,
    
// other attributes
    
    EXTRACTOR_CAP = 5, EXTRACTOR_PRODUCTIVITY = 50;
    
// unit stats
    
    public final static AttrLevel
    
//  ATTACK                 DEFENCE               MOVEMENT               HP          
            
    WORKER_A    = LOWEST,  WORKER_D    = LOWEST, WORKER_M    = MEDIUM,  WORKER_HP    = LOW,
    PEASANT_A   = LOW,     PEASANT_D   = LOWEST, PEASANT_M   = MEDIUM,  PEASANT_HP   = LOW,
    SWORDSMAN_A = MEDIUM,  SWORDSMAN_D = HIGH,   SWORDSMAN_M = LOW,     SWORDSMAN_HP = MEDIUM,
    KNIGHT_A    = HIGH,    KNIGHT_D    = HIGH,   KNIGHT_M    = HIGH,    KNIGHT_HP    = HIGH,
    DRAGON_A    = HIGHEST, DRAGON_D    = HIGH,   DRAGON_M    = HIGHEST, DRAGON_HP    = HIGHEST,
            
    EXTRACTOR_HP = LOW, BARRACKS_HP = MEDIUM, CASTLE_HP = HIGH;
    
    public static final Stock calcBuildingCost(AttrLevel hitPoints){
        return (new Stock(1, 1, 0)).multiply(hitPoints.getValue() * 200);
    }
    
    public static final Stock calcMovableCost(AttrLevel hitPoints){
        return (new Stock(1, 0, 1)).multiply(hitPoints.getValue() * 100);
    }

    public static final int calcBuildingHealth(AttrLevel hitPoints){
        return 200 * hitPoints.getValue();
    }
    
    public static final int calcMovableHealth(AttrLevel hitPoints){
        return 100 * hitPoints.getValue();
    }
    
    public static final int calcAttackValue(AttrLevel attack){
        return 20 * attack.getValue();
    }

    public static final int calcDefenceValue(AttrLevel attack){
        return 2 * attack.getValue();
    }
    
    public static final int calcMovementCost(AttrLevel movement){
        return 14 - movement.getValue() * 2;
    }
    
}
