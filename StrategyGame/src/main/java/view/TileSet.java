/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import javax.swing.JComponent;
import model.map.Orientation;
import resources.ResourceLoader;

/**
 *
 * @author laszl
 */
public class TileSet {
    private final static Image TILESET = ResourceLoader.loadImage("resources/tileset.png");
    private final static int TILE_DIM = 16;
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
        LEVEL_1(5,15),LEVEL_2(6,15),LEVEL_3(7,15),LEVEL_4(5,16),
        LEVEL_SMALL_1(7,14),LEVEL_SMALL_2(6,14),LEVEL_SMALL_3(7,16),LEVEL_SMALL_4(6,16),
        SELECTION(5,14),SELECTION_1(4,14);

        TileType(int x, int y) {
            this.x = x;
            this.y = y;
    }

    private final int x;
    private final int y;        
    }
    
    private static void drawTile(TileType t,Point pos, int size,Graphics2D g,JComponent c){
        g.drawImage(TILESET,pos.x,pos.y,pos.x+size,pos.y+size,TILE_DIM * t.x,TILE_DIM * t.y,TILE_DIM * (t.x + 1),TILE_DIM * (t.y + 1),c);
    }
  
    public static void drawGrass(int variant,int size, Point pos, Graphics2D g, JComponent c){
        switch(variant % 4){
            case 1 -> drawTile(TileType.GRASS_1,pos,size,g,c);
            case 2 -> drawTile(TileType.GRASS_2,pos,size,g,c);
            case 3 -> drawTile(TileType.GRASS_3,pos,size,g,c);
            default -> drawTile(TileType.GRASS,pos,size,g,c);
        }
    }
    
    public static void drawForest(int variant,int size, Point pos, Graphics2D g, JComponent c){
        drawGrass(variant,size,pos,g,c);
        switch(variant % 4){
            case 1 -> drawTile(TileType.FOREST_1,pos,size,g,c);
            case 2 -> drawTile(TileType.FOREST_2,pos,size,g,c);
            case 3 -> drawTile(TileType.FOREST_3,pos,size,g,c);
            default -> drawTile(TileType.FOREST,pos,size,g,c);
        }
    }

    public static void drawGold(int variant,int size, Point pos, Graphics2D g, JComponent c){
        drawGrass(variant,size,pos,g,c);
        switch(variant % 4){
            case 1 -> drawTile(TileType.GOLD_1,pos,size,g,c);
            case 2 -> drawTile(TileType.GOLD_2,pos,size,g,c);
            case 3 -> drawTile(TileType.GOLD_3,pos,size,g,c);
            default -> drawTile(TileType.GOLD,pos,size,g,c);
        }
    }
    
    public static void drawWall(int variant, Orientation orient,int size, Point pos, Graphics2D g, JComponent c){
        drawGrass(variant,size,pos,g,c);
        switch(orient){
            case NE -> drawTile(TileType.WALL_NE,pos,size,g,c);
            case NS -> drawTile(TileType.WALL_NS,pos,size,g,c);
            case NW -> drawTile(TileType.WALL_NW,pos,size,g,c);
            case ES -> drawTile(TileType.WALL_ES,pos,size,g,c);
            case EW -> drawTile(TileType.WALL_EW,pos,size,g,c);
            case N -> drawTile(TileType.WALL_N,pos,size,g,c);
            case E -> drawTile(TileType.WALL_E,pos,size,g,c);
            case S -> drawTile(TileType.WALL_S,pos,size,g,c);
            case W -> drawTile(TileType.WALL_W,pos,size,g,c);
            default -> drawTile(TileType.EMPTY,pos,size,g,c);
        }
    }
        
    public static void drawRiver(int variant, Orientation orient,int size, Point pos, Graphics2D g, JComponent c){
        drawGrass(variant,size,pos,g,c);
        switch(orient){
            case NEW -> drawTile(TileType.RIVER_NEW,pos,size,g,c);
            case ESW -> drawTile(TileType.RIVER_ESW,pos,size,g,c);
            case NE -> drawTile(TileType.RIVER_NE,pos,size,g,c);
            case NS -> drawTile(TileType.RIVER_NS,pos,size,g,c);
            case NW -> drawTile(TileType.RIVER_NW,pos,size,g,c);
            case ES-> drawTile(TileType.RIVER_ES,pos,size,g,c);
            case EW -> drawTile(TileType.RIVER_EW,pos,size,g,c);
            default -> drawTile(TileType.EMPTY,pos,size,g,c);
        }
    }
    
    public static void drawBridge(int variant, Orientation orient,int size, Point pos, Graphics2D g, JComponent c){
        drawGrass(variant,size,pos,g,c);
        switch(orient){
            case NS -> {
                drawTile(TileType.RIVER_NS,pos,size,g,c);
                drawTile(TileType.BRIDGE_NS,pos,size,g,c);
            }
            case EW -> {
                drawTile(TileType.RIVER_EW,pos,size,g,c);
                drawTile(TileType.BRIDGE_EW,pos,size,g,c);
                
            }
            default -> drawTile(TileType.EMPTY,pos,size,g,c);
        }
    }
    public static void drawSelection(int size, Point pos, Graphics2D g, JComponent c){
        drawTile(TileType.SELECTION,pos,size,g,c);
    }

    public static void drawTargetSelection(int size, Point pos, Graphics2D g, JComponent c){
        drawTile(TileType.SELECTION_1,pos,size,g,c);
    }
    
    public static void drawCastle(int variant,int size, Point pos,Graphics2D g, JComponent c){
        if(variant == 0) drawTile(TileType.CASTLE_1,pos,size,g,c);
        else drawTile(TileType.CASTLE_2,pos,size,g,c);
    }
    
    public static void drawBarracks(int variant,int size, Point pos,Graphics2D g, JComponent c){
        if(variant == 0) drawTile(TileType.BARRACKS_1,pos,size,g,c);
        else drawTile(TileType.BARRACKS_2,pos,size,g,c);
    }
    
    public static void drawCamp(int variant,int size, Point pos,Graphics2D g, JComponent c){
        if(variant == 0) drawTile(TileType.CAMP_1,pos,size,g,c);
        else drawTile(TileType.CAMP_2,pos,size,g,c);
    }
    
    public static void drawUnit(int variant, int level,int size, Point pos,Graphics2D g, JComponent c){
        if(variant == 0) drawTile(TileType.UNIT_1,pos,size,g,c);
        else drawTile(TileType.UNIT_2,pos,size,g,c);
        switch(level){
            case 1 -> drawTile(TileType.LEVEL_1,pos,size,g,c);
            case 2 -> drawTile(TileType.LEVEL_2,pos,size,g,c);
            case 3 -> drawTile(TileType.LEVEL_3,pos,size,g,c);
            case 4 -> drawTile(TileType.LEVEL_4,pos,size,g,c);        
        }

    }
    
    public static void drawSmallUnit(int variant, int level,int size, Point pos,Graphics2D g, JComponent c){
        if(variant == 0) drawTile(TileType.UNIT_SMALL_1,pos,size,g,c);
        else drawTile(TileType.UNIT_SMALL_2,pos,size,g,c);
        switch(level){
            case 1 -> drawTile(TileType.LEVEL_SMALL_1,pos,size,g,c);
            case 2 -> drawTile(TileType.LEVEL_SMALL_2,pos,size,g,c);
            case 3 -> drawTile(TileType.LEVEL_SMALL_3,pos,size,g,c);
            case 4 -> drawTile(TileType.LEVEL_SMALL_4,pos,size,g,c);        
        }

    }
}
