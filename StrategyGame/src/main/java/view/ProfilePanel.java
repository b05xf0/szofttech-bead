/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPanel;
import model.common.Unit;
import model.field.Field;
import model.interfaces.IMovable;

/**
 *
 * @author laszl
 */
public class ProfilePanel extends JPanel {
    Unit unit = null;
    Field field = null;
    
    public ProfilePanel() {
        super();
        setOpaque(false);
    }
    
    public ProfilePanel(Unit unit){
        this();
        this.unit = unit;
        
    }
    
    public ProfilePanel(Field field){
        this();
        this.field = field;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(32, 32);
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        Point pos = new Point(0,0);
        int size = 32;
        
        
        if(unit != null){
            int playerIdx = unit.getPlayer().getIndex();
            switch(unit.getType()){
                case "Castle" -> TileSet.drawCastle(playerIdx,size, pos, g, this);
                case "Barracks" -> TileSet.drawBarracks(playerIdx,size, pos, g, this);
                case "Mine","Hut","Farm" -> TileSet.drawCamp(playerIdx,size, pos, g, this);
                default -> TileSet.drawUnit(playerIdx,((IMovable)unit).getRank(),size, pos, g, this);        
            }
        }
        
        if(field != null){
            int variant = field.getVariant();
            switch(field.getType()){
                    case GRASS -> TileSet.drawGrass(variant,size,  pos, g, this);
                    case GOLD -> TileSet.drawGold(variant,size,  pos, g, this);
                    case FOREST -> TileSet.drawForest(variant,size,  pos, g, this);
                    case WALL -> TileSet.drawWall(variant,field.getOrientation(),size,  pos, g, this);
                    case RIVER -> TileSet.drawRiver(variant,field.getOrientation(),size,  pos, g, this);
                    case BRIDGE -> TileSet.drawBridge(variant,field.getOrientation(),size,  pos, g, this);       
            }
        }
    }          
}
