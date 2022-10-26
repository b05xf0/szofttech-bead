package model.player;

import java.util.LinkedList;
import java.util.List;
import model.common.Stock;
import model.common.Unit;
import model.extractors.Extractor;
import model.field.Field;
import model.trainers.Castle;
import model.trainers.Trainer;
import model.warriors.Warrior;
import model.workers.Farmer;
import model.workers.Miner;
import model.workers.Woodcutter;
import model.workers.Worker;

public class Player {
    
    private String name;
    private final int idx;
    private final Field startPos;
    private final Stock treasury;
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
    public final void init(){
        Trainer firstCastle;
        this.extractors.clear();
        this.trainers.clear();
        this.warriors.clear();
        this.workers.clear();
        this.treasury.init();

        this.trainers.add(firstCastle = new Castle(startPos,this));
        startPos.addUnit(firstCastle);
        firstCastle.setTimer(0);

        this.workers.add(new Miner(startPos,this));
        this.workers.add(new Woodcutter(startPos,this));
        this.workers.add(new Farmer(startPos,this));
        for(Worker w : workers){startPos.addUnit(w); w.setTimer(0);}
    }
}
