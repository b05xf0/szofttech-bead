package model.player;

import java.util.LinkedList;
import java.util.List;
import model.common.Stock;
import model.extractors.*;
import model.field.Field;
import model.trainers.*;
import model.warriors.*;
import model.workers.*;

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
        this.treasury = new Stock();
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
        this.treasury.init();

        addUnit(new Castle(startPos,this));
        addUnit(new Miner(startPos,this));
        addUnit(new Woodcutter(startPos,this));
        addUnit(new Farmer(startPos,this));
        
        for(Trainer u : trainers){u.getPosition().addUnit(u); u.setTimer(0);}
        for(Extractor u : extractors){u.getPosition().addUnit(u); u.setTimer(0);}
        for(Worker u : workers){u.getPosition().addUnit(u); u.setTimer(0);}
        for(Warrior u : warriors){u.getPosition().addUnit(u); u.setTimer(0);}
        actionPoints = ACTION_POINTS;
    }
    public int getAPs() { return actionPoints; }
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
