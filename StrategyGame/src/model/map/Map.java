package model.map;

import model.field.FieldType;
import model.field.Field;
import java.awt.Point;
import java.io.InputStream;
import java.util.Scanner;
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
}
