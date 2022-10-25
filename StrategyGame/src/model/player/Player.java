package model.player;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import model.common.Stock;
import model.extractors.Extractor;
import model.trainers.Castle;
import model.trainers.Trainer;
import model.warriors.Warrior;
import model.workers.Worker;

public class Player {
    
    private String name;
    private final int idx;
    private final Point startPos;
    private final Stock treasury;
    private final List<Extractor> extractors;
    private final List<Trainer> trainers;
    private final List<Warrior> warriors;
    private final List<Worker> workers;

    public Player(int idx, Point startPos) {
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
    public void init(){
        this.extractors.clear();
        this.trainers.clear();
        this.warriors.clear();
        this.workers.clear();
        this.treasury.init();
        //this.trainers.add(new Castle(startPos,this));
        //TODO
    }
}
