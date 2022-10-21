package view;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import model.GameManager;

public class ControlPanel extends JPanel{
    private final GameManager game;
    
    //private final InfoPanel gameInfo;
    private final InfoPanel mapInfo;
    
    public ControlPanel(GameManager game) {
        this.game = game;
        
        setLayout(new FlowLayout());
       
        //gameInfo = new InfoPanel();
        mapInfo = new InfoPanel();
        //add(gameInfo);
        add(mapInfo);
        add(new InfoPanel());
        
    }
    
    //public InfoPanel getGameInfoPanel() { return gameInfo; }
    public InfoPanel getMapInfoPanel() { return mapInfo; }
    public void refresh() {
        mapInfo.update(game.getSelectedField().toString());
    }
/*
    @Override
    public Dimension getPreferredSize() {
        //int dim = map.getSize() * DIM * 3 / 2;
        int dim = getParent().getWidth() - getParent().getHeight();
        return new Dimension(dim, 300);
    }    
*/    
}
