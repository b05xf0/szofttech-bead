package model.map;

import model.field.FieldType;
import model.field.Field;
import java.awt.Point;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import model.common.Unit;
import model.interfaces.IMovable;
import resources.ResourceLoader;

public class Map {
    private static final int MAX_SIZE = 50;
    private static final String MAP_PATH = "resources/map.txt";
    
    private final Field[][] fields = new Field[MAX_SIZE][MAX_SIZE];
    private Field[] startPos = new Field[2];
    private int size;

    public Map(){
        InputStream is = ResourceLoader.loadResource(MAP_PATH);
       
        try (Scanner sc = new Scanner(is)){
            String line;
            int j = 0;
            while (sc.hasNextLine()){
                
                line = sc.nextLine();
                for (int i = 0; i < line.length(); i++){
                    Point pos = new Point(i,j);
                    FieldType type;
                    type = switch (line.charAt(i)) {
                        case 'g' -> FieldType.GOLD;
                        case 'f' -> FieldType.FOREST;
                        case 'r' -> FieldType.RIVER;
                        case 'b' -> FieldType.BRIDGE;
                        case 'w' -> FieldType.WALL;
                        default -> FieldType.GRASS;
                    };
                    fields[i][j] = new Field(this, pos, type);
                    if (line.charAt(i) == '1') startPos[0] = fields[i][j];
                    if (line.charAt(i) == '2') startPos[1] = fields[i][j];
                }
                ++j;
            }
            size = j;
        } catch (Exception e){
            System.out.println("Ajaj");
        }
    }
    
    public void init(){
        for(int i = 0; i < size; ++i)
            for(int j = 0; j < size; ++j)
                fields[i][j].init();
    }
    
    public Field getField(Point pos){ return pos.x < 0 || pos.y < 0 || pos.x >= size || pos.y >= size ? null: fields[pos.x][pos.y]; }
    
    public int getSize() { return size; }
    
    public Field getStartingPosition(int idx) { return startPos[idx]; }
    
    public void initMovementCosts(int c){
         for(int i = 0; i < size; ++i)
            for(int j = 0; j < size; ++j)
                fields[i][j].setMovementCost(c);   
    }
    
    public void setMovementCosts(Unit unit){
        final int INFINITY = Integer.MAX_VALUE;
        List<Field> q = new LinkedList<>();
        Field s = unit.getPosition();
        int c = ((IMovable) unit).getMovementCost();
        boolean unitCanFly = ((IMovable) unit).canFly();
        initMovementCosts(INFINITY);
        s.setMovementCost(0);
        q.add(s);
        while(!q.isEmpty()){
            Field u = q.remove(0);
            for(Field v : u.getNeighbours(unitCanFly,unit.getPlayer())){
                if(v.getMovementCost() == INFINITY && u.getMovementCost() < unit.getPlayer().getAPs()){
                    v.setMovementCost(u.getMovementCost() + c);
                    q.add(v);
                }
            }
        }
    }
    
    public void setAttackMode(Unit unit){
        final int INFINITY = Integer.MAX_VALUE;
        List<Field> q = new LinkedList<>();
        Field s = unit.getPosition();
        int c = 1;
        initMovementCosts(INFINITY);
        s.setMovementCost(0);
        q.add(s);
        while(!q.isEmpty()){
            Field u = q.remove(0);
            for(Field v : u.getNeighboursWithEnemies(unit.getPlayer())){
                if(v.getMovementCost() == INFINITY && u.getMovementCost() < 1){
                    v.setMovementCost(u.getMovementCost() + c);
                    q.add(v);
                }
            }
        }
    
    }
}
