package model.player;

import commands.IllegalCommandException;
import java.util.LinkedList;
import java.util.List;
import model.GameState;
import model.common.Stock;
import model.common.Unit;
import model.extractors.*;
import model.field.Field;
import model.trainers.*;
import model.warriors.*;
import model.workers.*;
import static model.Configuration.*;

public class Player {
    public static final int ACTION_POINTS = 100;    
    private String name;
    private final int idx;
    private final Field startPos;
    private final Stock treasury;
    private int actionPoints;
    private final List<Extractor> extractors;
    private final List<Trainer> trainers;
    private final List<Warrior> warriors;
    private final List<Worker> workers;

    public Player(int idx, Field startPos) {
        this.idx = idx;
        this.startPos = startPos;
        this.name = "Player#" + (idx + 1);
        this.extractors = new LinkedList<>();
        this.trainers = new LinkedList<>();
        this.warriors = new LinkedList<>();
        this.workers = new LinkedList<>();
        this.treasury = new Stock(INIT_GOLD,INIT_LUMBER,INIT_FOOD);
    }
    
    public String getName() { return this.name; }
    
    public void setName(String name) { this.name = name; }
    
    public int getIndex() { return idx; }
    
    public Stock getTreasury() { return treasury; }
    
    public final void init(){
        this.extractors.clear();
        this.trainers.clear();
        this.warriors.clear();
        this.workers.clear();
        this.treasury.init(INIT_GOLD,INIT_LUMBER,INIT_FOOD);

        Castle.create(startPos,this);
               
        for(int i = 0; i < INIT_MINERS; ++i) Miner.create(startPos,this);
        for(int i = 0; i < INIT_WOODCUTTERS; ++i) Woodcutter.create(startPos,this);
        for(int i = 0; i < INIT_FARMERS; ++i) Farmer.create(startPos,this);
        for(int i = 0; i < INIT_PEASANTS; ++i) Peasant.create(startPos,this);
        for(int i = 0; i < INIT_SWORDSMEN; ++i) Swordsman.create(startPos,this);
        for(int i = 0; i < INIT_KNIGHTS; ++i) Knight.create(startPos,this);
        for(int i = 0; i < INIT_DRAGONS; ++i) Dragon.create(startPos,this);
       
        for(Unit u : getUnits()) u.setTimer(0);
        
        actionPoints = ACTION_POINTS;
    }
    
    public void endTurn(){
        for(Unit u : getUnits()){
            switch(u.getState()){
                case BUSY -> u.decrementTimer();
                case DEAD -> u.remove();
                default -> {}
            }
        }
        for(Extractor e : extractors) e.extract();
        actionPoints = ACTION_POINTS;
    }
    
    public boolean hasHQ(){
        for(Trainer t : trainers)
            if(t.isHQ()) return true;
        return false;
    }
    
    public void setStrikeBack(boolean c){
        for(Worker w : workers) w.setStrikeBack(c);
        for(Warrior w : warriors) w.setStrikeBack(c);
    }
 
    public List<Unit> getUnits(){
        List<Unit> units = new LinkedList<>();
        units.addAll(trainers);
        units.addAll(extractors);
        units.addAll(workers);
        units.addAll(warriors);
        return units;
    }
    public int getAPs() { return actionPoints; }
    
    public void decreaseAPs(int n) throws IllegalCommandException {
        if(n > actionPoints){
            throw new IllegalCommandException(GameState.ERR_TARGET_IS_TOO_FAR);
        } else {
            actionPoints -= n;
        }
    }
    
    public void addUnit(Extractor u) { extractors.add(u); }
    public void addUnit(Trainer u) { trainers.add(u); }
    public void addUnit(Warrior u) { warriors.add(u); }
    public void addUnit(Worker u) { workers.add(u); }

    public void removeUnit(Extractor u) { extractors.remove(u); }
    public void removeUnit(Trainer u) { trainers.remove(u); }
    public void removeUnit(Warrior u) { warriors.remove(u); }
    public void removeUnit(Worker u) { workers.remove(u); }
    
    @Override
    public String toString() {
        return String.format("%s - APs: %d", name, actionPoints);
    }
}
