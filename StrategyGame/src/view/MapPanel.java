package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JPanel;
import model.Field;

import model.GameManager;
import resources.ResourceLoader;

public class MapPanel extends JPanel {
    private final static String TILESET_PATH = "resources/tileset.png";
    private final static int TILE_DIM = 16;
    private final GameManager game;
    private final Image tileSet;
    private int tileSize;
    private int offsetX;
    private int offsetY;
    
    private enum TileType {
        EMPTY(7,10),
        GRASS(2,4),GRASS_1(3,4),GRASS_2(4,4),GRASS_3(5,4),
        FOREST(1,0),FOREST_1(0,13),FOREST_2(1,13),FOREST_3(2,13),
        GOLD(0,14),GOLD_1(1,14),GOLD_2(2,14),GOLD_3(3,14),
        BRIDGE_NS(6,4),BRIDGE_EW(7,4),
        RIVER_NEW(6,9),RIVER_ESW(7,9),
        RIVER_NE(4,10),RIVER_NS(3,6),RIVER_NW(3,10),RIVER_ES(5,10),RIVER_EW(5,5),RIVER_SW(6,10),
        WALL_NE(0,2),WALL_NS(2,1),WALL_NW(1,2),WALL_ES(0,1),WALL_EW(2,2),WALL_SW(1,1),
        WALL_N(5,2),WALL_E(6,1),WALL_S(7,1),WALL_W(6,2),
        CASTLE_1(0,15),BARRACKS_1(1,15),CAMP_1(2,15),UNIT_1(3,15),UNIT_SMALL_1(4,15),
        CASTLE_2(0,16),BARRACKS_2(1,16),CAMP_2(2,16),UNIT_2(3,16),UNIT_SMALL_2(4,16),
        SELECTION(5,14);

        TileType(int x, int y) {
            this.x = x;
            this.y = y;
    }

    private final int x;
    private final int y;        
    }
    
    public MapPanel(GameManager game) throws IOException{
        tileSet = ResourceLoader.loadImage(TILESET_PATH);
        this.game = game;
        

    }
    
    private void drawTile(Graphics2D g, TileType t, int x, int y){
        g.drawImage(
            tileSet,
            tileSize * x + offsetX,
            tileSize * y + offsetY,
            tileSize * (x + 1) + offsetX,
            tileSize * (y + 1) + offsetY,
            TILE_DIM * t.x,
            TILE_DIM * t.y,
            TILE_DIM * (t.x + 1),
            TILE_DIM * (t.y + 1),
            this
        );
    }
    
    public Point getPosition(int x, int y) { return new Point((x-offsetX) / tileSize,(y - offsetY) / tileSize); }
    
    public int getTileDim(){return TILE_DIM;}
    
    @Override
    public Dimension getPreferredSize() {
        int dim = getParent().getHeight();
        return new Dimension(dim, dim);
    }
    
    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        int mapSize = game.getMap().getSize();
        tileSize = getHeight() / mapSize;
        offsetX = (getWidth() - mapSize * tileSize) / 2;
        offsetY = (getHeight() - mapSize * tileSize) / 2;

        for (int i = 0; i < mapSize; ++i) {
            for (int j = 0; j < mapSize; ++j) {
                Field field = game.getMap().getField(new Point(i,j));
                TileType tileType;
                switch(field.getVariant()%4){
                    case 1 -> tileType = TileType.GRASS_1;
                    case 2 -> tileType = TileType.GRASS_2;
                    case 3 -> tileType = TileType.GRASS_3;
                    default -> tileType = TileType.GRASS;
                }
                drawTile(g,tileType,i,j);  

                switch (field.getType()) {
                    case GOLD -> {
                        switch(field.getVariant() % 4){
                            case 1 -> tileType = TileType.GOLD_1;
                            case 2 -> tileType = TileType.GOLD_2;
                            case 3 -> tileType = TileType.GOLD_3;
                            default -> tileType = TileType.GOLD;
                        }

                    }
                    case FOREST -> {
                        switch(field.getVariant() % 4){
                            case 1 -> tileType = TileType.FOREST_1;
                            case 2 -> tileType = TileType.FOREST_2;
                            case 3 -> tileType = TileType.FOREST_3;
                            default -> tileType = TileType.FOREST;
                        }
                    }
                    case WALL -> {
                        switch(field.getOrientation()){
                            case NE -> tileType = TileType.WALL_NE;
                            case NS -> tileType = TileType.WALL_NS;
                            case NW -> tileType = TileType.WALL_NW;
                            case ES -> tileType = TileType.WALL_ES;
                            case EW -> tileType = TileType.WALL_EW;
                            case N -> tileType = TileType.WALL_N;
                            case E -> tileType = TileType.WALL_E;
                            case S -> tileType = TileType.WALL_S;
                            case W -> tileType = TileType.WALL_W;
                            default -> tileType = TileType.EMPTY;
                        }
                    }
                    case RIVER -> {
                        switch(field.getOrientation()){
                            case NEW -> tileType = TileType.RIVER_NEW;
                            case ESW -> tileType = TileType.RIVER_ESW;
                            case NE -> tileType = TileType.RIVER_NE;
                            case NS -> tileType = TileType.RIVER_NS;
                            case NW -> tileType = TileType.RIVER_NW;
                            case ES -> tileType = TileType.RIVER_ES;
                            case EW -> tileType = TileType.RIVER_EW;
                            default -> tileType = TileType.EMPTY;
                        }
                    }
                    case BRIDGE -> {
                        switch(field.getOrientation()){
                            case NS -> {
                                drawTile(g,TileType.RIVER_NS,i,j);
                                tileType = TileType.BRIDGE_NS;
                            }
                            case EW -> {
                                drawTile(g,TileType.RIVER_EW,i,j);
                                tileType = TileType.BRIDGE_EW;
                            }
                            default -> tileType = TileType.EMPTY;
                        }
                    }
                    default -> tileType = TileType.EMPTY;
                    
                }
                drawTile(g,tileType,i,j);
                if (field.equals(game.getSelectedField())) drawTile(g,TileType.SELECTION,i,j);
                
                // only for test purpose
                if (field.equals(game.getMap().getField(game.getMap().getStartingPosition(0)))) {
                    drawTile(g,TileType.CASTLE_1,i,j);
                    drawTile(g,TileType.UNIT_SMALL_1,i,j);
                }
                if (field.equals(game.getMap().getField(game.getMap().getStartingPosition(1)))) {
                    drawTile(g,TileType.CASTLE_2,i,j);
                    drawTile(g,TileType.UNIT_SMALL_2,i,j);
                }
            
            
            }
        }
        
    }
}


    