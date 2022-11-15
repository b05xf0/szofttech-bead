package view;

import commands.ActionCommand;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import model.GameManager;
import model.GameState;
import model.common.Unit;
import model.player.Player;
import view.InfoLabel.LabelSize;


public class MainWindow extends JFrame {

    private final GameManager game;
    
    private final BackgroundPanel bg = new BackgroundPanel();
    
    private final MapPanel map;
   
    private final ControlPanel ctrl = new ControlPanel();
    
    private final CommandButton endTurnButton = new CommandButton("End Turn"),
                                executeCommandButton = new CommandButton("Execute");
    
    private final CardPanel playerPanel = new CardPanel(endTurnButton),
                            commandPanel = new CardPanel(executeCommandButton),
                            unitsPanel = new CardPanel(),
                            actionsPanel = new CardPanel(),
                            targetUnitsPanel = new CardPanel();
    
    private final GridPanel units = new GridPanel(0, 5),
                            actions  = new GridPanel(0, 5),
                            targetUnits  = new GridPanel(0, 5),
                            commandElements = new GridPanel(0,5);
    
    private final InfoLabel playerInfo = new InfoLabel(LabelSize.XL," "),
                            gameInfo = new InfoLabel(LabelSize.L," "),
                            treasuryInfo = new InfoLabel(LabelSize.L," "),
                            gameState = new InfoLabel(LabelSize.M," ");
    
    public MainWindow() throws IOException {

        setTitle("Strategy Game");

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showExitConfirmation();
            }
        });
        //URL url = MainWindow.class.getClassLoader().getResource("view/logo_1982.png");
        //setIconImage(Toolkit.getDefaultToolkit().getImage(url));

        setJMenuBar(createMenuBar());

        getContentPane().setLayout(new BorderLayout());
        //getContentPane().setBackground(new Color(205, 133, 63));
        
        game = new GameManager();        
        
        map = new MapPanel(game);
        map.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                switch(game.getState()) {
                    case SELECT_FIELD, SELECT_UNIT, SELECT_ACTION, EXECUTION -> game.selectField(map.getPosition(e.getX(),e.getY()));
                    case MOVE_SELECT_TARGETFIELD,ATTACK_SELECT_TARGETFIELD,SELECT_TARGETUNIT -> game.selectTargetField(map.getPosition(e.getX(),e.getY()));
                    default -> {}
                }
                updateWindow();
            }
                
        });

        endTurnButton.addActionListener((ActionEvent e) -> {
            game.endTurn();
            updateWindow();
        });
        
        executeCommandButton.addActionListener((ActionEvent e) -> {
            game.executeCommand();
            updateWindow();
        });    

        playerPanel.add(gameInfo,BorderLayout.PAGE_START);
        playerPanel.add(playerInfo,BorderLayout.CENTER);
        playerPanel.add(treasuryInfo,BorderLayout.PAGE_END);
        
        commandPanel.add(new InfoLabel(LabelSize.M,"Command"),BorderLayout.PAGE_START);
        commandPanel.add(commandElements,BorderLayout.CENTER);
        commandPanel.add(gameState,BorderLayout.PAGE_END);
       
        unitsPanel.add(new InfoLabel(LabelSize.M,"Units"),BorderLayout.PAGE_START);
        unitsPanel.add(units,BorderLayout.CENTER);

        actionsPanel.add(new InfoLabel(LabelSize.M,"Actions"),BorderLayout.PAGE_START);
        actionsPanel.add(actions,BorderLayout.CENTER);

        targetUnitsPanel.add(new InfoLabel(LabelSize.M,"Target Units"),BorderLayout.PAGE_START);
        targetUnitsPanel.add(targetUnits,BorderLayout.CENTER);
        
        ctrl.addPanel(playerPanel);
        ctrl.addPanel(commandPanel);
        ctrl.addPanel(unitsPanel);
        ctrl.addPanel(actionsPanel);
        ctrl.addPanel(targetUnitsPanel);
        
        //getContentPane().add(ctrl, BorderLayout.CENTER);
        //getContentPane().add(map, BorderLayout.LINE_END);
        bg.add(ctrl, BorderLayout.CENTER);
        bg.add(map, BorderLayout.LINE_END);
        
        getContentPane().add(bg, BorderLayout.CENTER);
        ctrl.setVisible(true);

        setExtendedState( JFrame.MAXIMIZED_BOTH );
        //setResizable(false);
        setMinimumSize(new Dimension(1280,720));
        //pack();
        setLocationRelativeTo(null);
        setVisible(true);
        startGame();
        
    }

    private void updateWindow(){
        map.repaint();
        ctrl.setVisible(false);
        playerPanel.setVisible(false);
        commandPanel.setVisible(false);
        unitsPanel.setVisible(false);
        actionsPanel.setVisible(false);
        targetUnitsPanel.setVisible(false);
        
        switch(game.getState()) {
            case SELECT_FIELD -> updatePlayerPanel();
            case SELECT_UNIT -> updateUnitsPanel();
            case SELECT_ACTION -> updateActionsPanel();
            case MOVE_SELECT_TARGETFIELD, ATTACK_SELECT_TARGETFIELD -> updateActionsPanel();
            case SELECT_TARGETUNIT -> updateTargetUnitsPanel();
            case EXECUTION -> {if(game.getSelectedTargetUnit()!=null) updateTargetUnitsPanel(); else updateActionsPanel();}
            default -> updateActionsPanel();
        }
        ctrl.setVisible(true);
    }
    
    private void updateCommandPanel(){
        commandElements.removeAll();
        //executeCommandButton.setVisible(false);
        executeCommandButton.setEnabled(false);
        
        if(game.getSelectedField() != null){
            CardPanel card = new CardPanel(game.getSelectedField());
            card.add(new InfoLabel(LabelSize.S,"Field"),BorderLayout.PAGE_START);
            card.add(new InfoLabel(LabelSize.M,game.getSelectedField().toString()),BorderLayout.CENTER);
            card.add(new InfoLabel(LabelSize.S,game.getSelectedField().getPosDisplay()),BorderLayout.PAGE_END);
            card.setVisible(true);
            commandElements.add(card);
        }
        if(game.getSelectedUnit() != null){
            CardPanel card = new CardPanel(game.getSelectedUnit());
            card.add(new InfoLabel(LabelSize.S,"Unit"),BorderLayout.PAGE_START);
            card.add(new InfoLabel(LabelSize.M,game.getSelectedUnit().toString()),BorderLayout.CENTER);
            card.add(new InfoLabel(LabelSize.S," "),BorderLayout.PAGE_END);
            card.setVisible(true);
            commandElements.add(card);
        }
        if(game.getSelectedCommand() != null){
            CardPanel card = new CardPanel();
            card.add(new InfoLabel(LabelSize.S,"Action"),BorderLayout.PAGE_START);
            card.add(new InfoLabel(LabelSize.M,game.getSelectedCommand().toString()),BorderLayout.CENTER);
            card.add(new InfoLabel(LabelSize.S," "),BorderLayout.PAGE_END);
            card.setVisible(true);
            commandElements.add(card);
        }
        if(game.getSelectedTargetField() != null){
            CardPanel card = new CardPanel(game.getSelectedTargetField());
            card.add(new InfoLabel(LabelSize.S,"Target Field"),BorderLayout.PAGE_START);
            card.add(new InfoLabel(LabelSize.M,game.getSelectedTargetField().toString()),BorderLayout.CENTER);
            card.add(new InfoLabel(LabelSize.S,game.getSelectedTargetField().getPosDisplay()),BorderLayout.PAGE_END);
            card.setVisible(true);
            commandElements.add(card);
        }
        if(game.getSelectedTargetUnit() != null){
            CardPanel card = new CardPanel(game.getSelectedTargetUnit());
            card.add(new InfoLabel(LabelSize.S,"Target Unit"),BorderLayout.PAGE_START);
            card.add(new InfoLabel(LabelSize.M,game.getSelectedTargetUnit().toString()),BorderLayout.CENTER);
            card.add(new InfoLabel(LabelSize.S,(game.getSelectedTargetUnit().canStrikeBack() ? "Can Strike Back" : "")),BorderLayout.PAGE_END);
            card.setVisible(true);
            commandElements.add(card);
        }        
        gameState.setText(game.getState().toString());
        executeCommandButton.setEnabled(game.getState() == GameState.EXECUTION);
        commandPanel.setVisible(true);
    }
    
    private void updatePlayerPanel(){
        Player p = game.getCurrentPlayer();
        playerInfo.setText(game.getCurrentPlayer().toString());
        gameInfo.setText(game.toString());
        treasuryInfo.setText(game.getCurrentPlayer().getTreasury().toString());
        playerPanel.setVisible(true);
        updateCommandPanel();
    }

    private void updateUnitsPanel(){
        updatePlayerPanel();
        units.removeAll();
        for(Unit u : game.getSelectedField().getUnits()){
            CardPanel card = new CardPanel(u);
            card.add(new InfoLabel(LabelSize.S,String.format("Health: %d",u.getHealth())),BorderLayout.PAGE_START);
            card.add(new InfoLabel(LabelSize.M,u.toString()),BorderLayout.CENTER);
            card.add(new InfoLabel(LabelSize.S,u.getStateWithTimer()),BorderLayout.PAGE_END);
            card.setToolTipText(u.getStats());
            if(u.equals(game.getSelectedUnit())){
                card.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.yellow));
            } else {
                card.setBorder(new EmptyBorder(3,3,3,3));
            }
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    game.selectUnit(u);
                    updateWindow();
                }
            });
            card.setVisible(true);
            units.add(card);
        }
        unitsPanel.setVisible(true);
    }
    
    private void updateActionsPanel(){
        updateUnitsPanel();
        actions.removeAll();
        
        for(ActionCommand c : game.getSelectedUnit().getActions()){
            CardPanel card = new CardPanel();
            card.add(new InfoLabel(LabelSize.S," "),BorderLayout.PAGE_START);
            card.add(new InfoLabel(LabelSize.M,c.toString()),BorderLayout.CENTER);
            card.add(new InfoLabel(LabelSize.S," "),BorderLayout.PAGE_END);
            if(c.equals(game.getSelectedCommand())){
                card.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.yellow));
            } else {
                card.setBorder(new EmptyBorder(3,3,3,3));
            }
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    game.selectCommand(c);
                    updateWindow();
                }
            });
            card.setVisible(true);
            actions.add(card);    
        }
        actionsPanel.setVisible(true);
    }
    private void updateTargetUnitsPanel(){
        updateActionsPanel();
        targetUnits.removeAll();
        for(Unit u : game.getSelectedTargetField().getUnits()){
            CardPanel card = new CardPanel(u);
            card.add(new InfoLabel(LabelSize.S,String.format("Health: %d",u.getHealth())),BorderLayout.PAGE_START);
            card.add(new InfoLabel(LabelSize.M,u.toString()),BorderLayout.CENTER);
            card.add(new InfoLabel(LabelSize.S,u.getStateWithTimer()),BorderLayout.PAGE_END);
            card.setToolTipText(u.getStats());
            if(u.equals(game.getSelectedTargetUnit())){
                card.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.yellow));
            } else {
                card.setBorder(new EmptyBorder(3,3,3,3));
            }
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    game.selectTargetUnit(u);
                    updateWindow();
                }
            });
            card.setVisible(true);
            targetUnits.add(card);
        }
        targetUnitsPanel.setVisible(true);
    }    

    
    private void showExitConfirmation() {
        int n = JOptionPane.showConfirmDialog(this,
                "Do you really want to exit?",
                "Exit confirmation",
                JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void showNewGameConfirmation() {
        int n = JOptionPane.showConfirmDialog(this,
                "Do you really want to start a new game?",
                " confirmation",
                JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            startGame();
        }
    }    
    
    private void showSettings() {
        JTextField nameInput1 = new JTextField(12);
        JTextField nameInput2 = new JTextField(12);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Player #1:"));
        panel.add(nameInput1);
        panel.add(new JLabel("Player #2:"));
        panel.add(nameInput2);
        String name1 = "";
        String name2 = "";
        nameInput1.setText(game.getPlayer(0).getName());
        nameInput2.setText(game.getPlayer(1).getName());

        while ("".equals(name1) || "".equals(name2) || name1.equals(name2)) {
            JOptionPane.showMessageDialog(this, panel, "Enter names", JOptionPane.QUESTION_MESSAGE);
            name1 = nameInput1.getText();
            name2 = nameInput2.getText();
        }
        game.getPlayer(0).setName(name1);
        game.getPlayer(1).setName(name2);
    }
    
    private void startGame(){
        showSettings();
        game.start();
        updateWindow();
    }
    
    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Options");
        JMenuItem menuNewGame = new JMenuItem(new AbstractAction("New Game") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(game.getState() == GameState.OVER || game.getState() == GameState.SETUP) startGame();
                else showNewGameConfirmation();
            }
        });
        JMenuItem menuGameExit = new JMenuItem(new AbstractAction("Exit Game") {
            @Override
            public void actionPerformed(ActionEvent e) {
                showExitConfirmation();
            }
        });
        menuGame.add(menuNewGame);
        menuGame.add(menuGameExit);
        menuBar.add(menuGame);
        return menuBar;
    }

    public static void main(String[] args) throws IOException {
        MainWindow window = new MainWindow();
    }
}
