package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;
import static java.lang.Math.min;
import javax.swing.JPanel;
import model.field.Field;
import model.GameManager;
import model.GameState;
import model.common.Unit;
import model.field.FieldType;

public class MapPanel extends JPanel {
    

    private final GameManager game;
    private int tileSize;
    private int offsetX;
    private int offsetY;

    public MapPanel(GameManager game) throws IOException{
        this.game = game;
        setOpaque(false);

    }
    
    
    public Point getPosition(int x, int y) {
        return new Point(min((x-offsetX) / tileSize,game.getMap().getSize()-1),min((y - offsetY) / tileSize,game.getMap().getSize()-1));
    }
    
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
        tileSize = (getHeight()) / (mapSize + 1);
        offsetX = (getWidth() - mapSize * tileSize) / 2;
        offsetY = (getHeight() - mapSize * tileSize) / 2;
        for (int i = 0; i < mapSize; ++i) {
            for (int j = 0; j < mapSize; ++j) {
                Field field = game.getMap().getField(new Point(i,j));
                
                
                Point pos = new Point(i * tileSize + offsetX,j * tileSize + offsetY); 
                Unit building;
                switch (field.getType()) {
                    case GRASS -> TileSet.drawGrass(field.getVariant(),tileSize,  pos, g, this);
                    case GOLD -> TileSet.drawGold(field.getVariant(),tileSize,  pos, g, this);
                    case FOREST -> TileSet.drawForest(field.getVariant(),tileSize,  pos, g, this);
                    case WALL -> TileSet.drawWall(field.getVariant(),field.getOrientation(),tileSize,  pos, g, this);
                    case RIVER -> TileSet.drawRiver(field.getVariant(),field.getOrientation(),tileSize,  pos, g, this);
                    case BRIDGE -> TileSet.drawBridge(field.getVariant(),field.getOrientation(),tileSize,  pos, g, this);
                }
                if ((building = field.getTrainer()) != null || (building = field.getExtractor()) != null ) switch(building.getType()){
                    case "Castle" -> TileSet.drawCastle(building.getPlayer().getIndex(),tileSize, pos, g, this);
                    case "Barracks" -> TileSet.drawBarracks(building.getPlayer().getIndex(),tileSize, pos, g, this);
                    default -> TileSet.drawCamp(building.getPlayer().getIndex(),tileSize, pos, g, this);
                }
                if (!field.getWorkers().isEmpty()) {
                    if(building == null && field.getType() == FieldType.GRASS)
                        TileSet.drawUnit(field.getWorkers().get(0).getPlayer().getIndex(),0,tileSize, pos, g, this);
                    else
                        TileSet.drawSmallUnit(field.getWorkers().get(0).getPlayer().getIndex(),0,tileSize, pos, g, this);
                }
                if (!field.getWarriors().isEmpty()) {
                    if(building == null && field.getType() == FieldType.GRASS)
                        TileSet.drawUnit(field.getWarriors().get(0).getPlayer().getIndex(),field.getHighestRank(),tileSize, pos, g, this);
                    else    
                        TileSet.drawSmallUnit(field.getWarriors().get(0).getPlayer().getIndex(),field.getHighestRank(),tileSize, pos, g, this);
                }                    

                if (field.equals(game.getSelectedField())) TileSet.drawSelection(tileSize,  pos, g, this);
                if (field.equals(game.getSelectedTargetField())) TileSet.drawTargetSelection(tileSize,  pos, g, this);
                
                if((game.getState() == GameState.MOVE_SELECT_TARGETFIELD && (game.getCurrentPlayer().getAPs() < field.getMovementCost() || !field.isValidTarget(game.getCurrentPlayer()) ) ) ||
                        (game.getState() == GameState.ATTACK_SELECT_TARGETFIELD && field.getMovementCost() == Integer.MAX_VALUE)){
                        g.setColor(new Color(0,0,0,127));
                        g.fillRect(pos.x,pos.y,tileSize,tileSize);
                }
                
            }
        }
        
    }
}


    