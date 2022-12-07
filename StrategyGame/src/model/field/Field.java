package model.field;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import model.extractors.Extractor;
import model.map.Map;
import model.map.Orientation;
import model.trainers.Trainer;
import model.warriors.Warrior;
import model.workers.Worker;

public class Field {

    private final Point pos;
    private final FieldType type;
    private final Map map;
    private final int variant;
    private Extractor extractor;
    private Trainer trainer;
    private final List<Worker> workers;
    private final List<Warrior> warriors;

    
    public Field(Map map, Point pos, FieldType type){
        this.map = map;
        this.pos = pos;
        this.type = type;
        this.variant = (int)(Math.random() * 100);
        workers = new LinkedList<>();
        warriors = new LinkedList<>();
    }
    
    public void init(){
        
        workers.clear();
        warriors.clear();
        extractor = null;
        trainer = null;
    }
    
    public FieldType getType() { return type; }
    public int getVariant() { return variant; }
    
    public List<Worker> getWorkers() { return workers; }
    public List<Warrior> getWarriors() { return warriors; }
    public Extractor getExtractor() { return extractor; }
    public Trainer getTrainer() { return trainer; }
    public int getHighestRank() {
        int rank = 0;
        for(Warrior w: warriors)
            if(w.getRank() > rank) rank = w.getRank();
        return rank;
    }
    
    public void addUnit(Extractor u) { extractor = u; }
    public void addUnit(Trainer u) { trainer = u; }
    public void addUnit(Warrior u) { warriors.add(u); }
    public void addUnit(Worker u) { workers.add(u); }

    public void removeUnit(Extractor u) { extractor = null; }
    public void removeUnit(Trainer u) { trainer = null; }
    public void removeUnit(Warrior u) { warriors.remove(u); }
    public void removeUnit(Worker u) { workers.remove(u); }
    
    public Field getFieldToNorth(int d) { return map.getField(new Point(pos.x, pos.y - d)); }
    public Field getFieldToSouth(int d) { return map.getField(new Point(pos.x, pos.y + d)); }
    public Field getFieldToEast(int d) { return map.getField(new Point(pos.x + d, pos.y)); }
    public Field getFieldToWest(int d) { return map.getField(new Point(pos.x - d, pos.y)); }

    public Field getFieldToNorth() { return getFieldToNorth(1); }
    public Field getFieldToSouth() { return getFieldToSouth(1); }
    public Field getFieldToEast() { return getFieldToEast(1); }
    public Field getFieldToWest() { return getFieldToWest(1); }
    
    public boolean isOnBorder() { return getFieldToNorth() == null || getFieldToSouth() == null || getFieldToEast() == null || getFieldToWest() == null; }
    
    public boolean isOrientedToNorth() { return (getFieldToNorth() == null || getFieldToNorth().getType() == this.type); }
    public boolean isOrientedToEast() { return (getFieldToEast() == null || getFieldToEast().getType() == this.type); }
    public boolean isOrientedToSouth() { return (getFieldToSouth() == null || getFieldToSouth().getType() == this.type); }
    public boolean isOrientedToWest() { return (getFieldToWest() == null || getFieldToWest().getType() == this.type); }
    
    public Orientation getOrientation(){
        if (type == FieldType.BRIDGE && getFieldToNorth() != null && getFieldToNorth().getType() == FieldType.RIVER) return Orientation.NS;
        if (type == FieldType.BRIDGE && getFieldToEast() != null && getFieldToEast().getType() == FieldType.RIVER) return Orientation.EW;
        if (type == FieldType.RIVER && getFieldToNorth() != null && getFieldToNorth().getType() == FieldType.BRIDGE) return Orientation.NS;
        if (type == FieldType.RIVER && getFieldToSouth() != null && getFieldToSouth().getType() == FieldType.BRIDGE) return Orientation.NS;
        if (type == FieldType.RIVER && getFieldToEast() != null && getFieldToEast().getType() == FieldType.BRIDGE) return Orientation.EW;
        if (type == FieldType.RIVER && getFieldToWest() != null && getFieldToWest().getType() == FieldType.BRIDGE) return Orientation.EW;
        if (isOrientedToNorth() && isOrientedToEast() && isOrientedToSouth() && isOrientedToWest()) return Orientation.NESW;
        if (isOrientedToNorth() && isOrientedToEast() && isOrientedToSouth()) return Orientation.NES;
        if (isOrientedToNorth() && isOrientedToEast() && isOrientedToWest()) return Orientation.NEW;
        if (isOrientedToNorth() && isOrientedToSouth() && isOrientedToWest()) return Orientation.NSW;
        if (isOrientedToEast() && isOrientedToSouth() && isOrientedToWest()) return Orientation.ESW;
        if (isOrientedToNorth() && isOrientedToEast()) return Orientation.NE;
        if (isOrientedToNorth() && isOrientedToSouth()) return Orientation.NS;
        if (isOrientedToNorth() && isOrientedToWest()) return Orientation.NW;
        if (isOrientedToEast() && isOrientedToSouth()) return Orientation.ES;
        if (isOrientedToEast() && isOrientedToWest()) return Orientation.EW;
        if (isOrientedToSouth() && isOrientedToWest()) return Orientation.SW;        
        if (isOrientedToNorth()) return Orientation.N;
        if (isOrientedToEast()) return Orientation.E;
        if (isOrientedToSouth()) return Orientation.S;
        if (isOrientedToWest()) return Orientation.W;
        return Orientation._NA_;
    }
    public String getBuildingInfo(){
        if(trainer != null) return trainer.toString();
        if(extractor != null) return extractor.toString();
        return "Empty";
    }    
    @Override
    public String toString(){
        return "(" + this.pos.x + ", " + this.pos.y + "): " + this.type + " (" + this.getOrientation() + ")";
    
    }
    /*
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.pos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Field other = (Field) obj;
        return Objects.equals(this.pos, other.pos);
    }
 */   
}