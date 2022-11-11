package view;

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
import model.interfaces.ICommand;
import model.player.Player;
import model.trainers.Trainer;
import model.workers.Worker;
import view.InfoLabel.LabelSize;

public class MainWindow extends JFrame {

    private final GameManager game;
    
    private final MapPanel map;
   
    private final ControlPanel ctrl = new ControlPanel();
    
    private final CommandButton endTurnButton = new CommandButton("End Turn"),
                                executeCommandButton = new CommandButton("Execute");
    
    private final CardPanel playerPanel = new CardPanel(endTurnButton),
                            commandPanel = new CardPanel(executeCommandButton),
                            unitsPanel = new CardPanel(),
                            actionsPanel = new CardPanel(),
                            targetUnitsPanel = new CardPanel();
    
    private final GridPanel units = new GridPanel(0, 6),
                            actions  = new GridPanel(0, 6),
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
        
        game = new GameManager();        
        
        map = new MapPanel(game);
        map.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                switch(game.getState()) {
                    case SELECT_FIELD, SELECT_UNIT, SELECT_ACTION, EXECUTION -> game.selectField(map.getPosition(e.getX(),e.getY()));
                    case SELECT_TARGETFIELD, SELECT_TARGETUNIT -> game.selectTargetField(map.getPosition(e.getX(),e.getY()));
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
            game.getSelectedCommand().execute();
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
        
        ctrl.addPanel(playerPanel);
        ctrl.addPanel(commandPanel);
        ctrl.addPanel(unitsPanel);
        ctrl.addPanel(actionsPanel);
        
        getContentPane().add(ctrl, BorderLayout.CENTER);
        getContentPane().add(map, BorderLayout.LINE_END);
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
        
        switch(game.getState()) {
            case SELECT_FIELD -> updatePlayerPanel();
            case SELECT_UNIT -> updateUnitsPanel();
            case SELECT_ACTION -> updateActionsPanel();
            case SELECT_TARGETFIELD -> {}
            case SELECT_TARGETUNIT -> {}
            case EXECUTION -> updateActionsPanel();
            default -> {}
        }
        ctrl.setVisible(true);
    }
    
    private void updateCommandPanel(){
        commandElements.removeAll();
        //executeCommandButton.setVisible(false);
        executeCommandButton.setEnabled(false);
        if(game.getSelectedField() != null){
            CardPanel card = new CardPanel();
            card.add(new InfoLabel(LabelSize.S,"Field"),BorderLayout.PAGE_START);
            card.add(new InfoLabel(LabelSize.M,game.getSelectedField().toString()),BorderLayout.CENTER);
            card.setVisible(true);
            commandElements.add(card);
        }
        if(game.getSelectedUnit() != null){
            CardPanel card = new CardPanel();
            card.add(new InfoLabel(LabelSize.S,"Unit"),BorderLayout.PAGE_START);
            card.add(new InfoLabel(LabelSize.M,game.getSelectedUnit().toString()),BorderLayout.CENTER);
            card.setVisible(true);
            commandElements.add(card);
        }
        if(game.getSelectedCommand() != null){
            CardPanel card = new CardPanel();
            card.add(new InfoLabel(LabelSize.S,"Action"),BorderLayout.PAGE_START);
            card.add(new InfoLabel(LabelSize.M,game.getSelectedCommand().toString()),BorderLayout.CENTER);
            card.setVisible(true);
            commandElements.add(card);
        }
        executeCommandButton.setEnabled(game.getState() == GameState.EXECUTION);
        gameState.setText(game.getState().toString());
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
            CardPanel card = new CardPanel();
            card.add(new InfoLabel(LabelSize.S,String.format("Health: %d",u.getHealth())),BorderLayout.PAGE_START);
            card.add(new InfoLabel(LabelSize.M,u.toString()),BorderLayout.CENTER);
            card.add(new InfoLabel(LabelSize.S,u.getState().toString()),BorderLayout.PAGE_END);
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
        
        for(ICommand c : game.getSelectedUnit().getActions()){
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
/*   
    private void updatePlayerInfo() {
        ctrl.setVisible(false);
        playerInfo.update(
                game.getCurrentPlayer().toString(),
                game.toString(),
                game.getCurrentPlayer().getTreasury().toString());
        ctrl.setVisible(true);
    }
    
    private void updateFieldInfo() {
        ctrl.setVisible(false);
        fieldInfo.setVisible(false);
        unitCtrl.setVisible(false);
        actionCtrl.setVisible(false);
        units.removeAll();
        //actions.removeAll();
        if(game.getSelectedField() != null){
            fieldInfo.update(
                    "Fields",
                    game.getSelectedField().toString(),
                    "");
            if(game.getSelectedField().getTrainer() != null){
                CardPanel cardPanel = new CardPanel(
                        String.format("Health: %d", game.getSelectedField().getTrainer().getHealth()),
                        game.getSelectedField().getTrainer().getClass().getSimpleName(),
                        ""//game.getSelectedField().getTrainer().equals(game.getSelectedUnit()) ? "selected" : ""
                );
                if(game.getSelectedField().getTrainer().equals(game.getSelectedUnit())){
                    cardPanel.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.yellow));
                } else {
                    cardPanel.setBorder(new EmptyBorder(3,3,3,3));
                }
                cardPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        System.out.println("trainer clicked");
                        game.selectUnit(game.getSelectedField().getTrainer());
                        addTrainerActions(game.getSelectedField().getTrainer());
                        updateFieldInfo();
                    }
                });
                units.add(cardPanel);
                unitCtrl.setVisible(true);
                actionCtrl.setVisible(true);
            }
            if(!game.getSelectedField().getWorkers().isEmpty()){
                for(Worker w : game.getSelectedField().getWorkers()){
                    CardPanel cardPanel = new CardPanel(
                            String.format("Health: %d", w.getHealth()),
                            w.getClass().getSimpleName(),
                            ""//w.equals(game.getSelectedUnit()) ? "selected" : ""
                    );
                    if(w.equals(game.getSelectedUnit())){
                        cardPanel.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.yellow));
                    } else {
                        cardPanel.setBorder(new EmptyBorder(3,3,3,3));
                    }
                    cardPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        System.out.println("worker clicked");
                        game.selectUnit(w);
                        addWorkerActions(w);
                        updateFieldInfo();
                    }
                    });
                    units.add(cardPanel);
                }
                unitCtrl.setVisible(true);
                actionCtrl.setVisible(true);               
            }
            fieldInfo.setVisible(true);
        }
        ctrl.setVisible(true);
    }
    
    private void addTrainerActions(Trainer t){
        ctrl.setVisible(false);
        //actionCtrl.setVisible(false);
        actions.removeAll();
        
        actions.add(new CardPanel("", "Train Peasant", ""));
        actions.add(new CardPanel("", "Train Swordsman", ""));
        actions.add(new CardPanel("", "Train Knight", ""));
        actions.add(new CardPanel("", "Train Dragon", ""));
        
        for(ICommand c : t.getActions()){
            CardPanel command = new CardPanel("", c.toString(), "");
            actions.add(command);
            command.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        System.out.println(c.getClass().getSimpleName());
                        c.execute();
                    }
                    });
        }
        //actions.setVisible(true);
        actionCtrl.setVisible(true);
        ctrl.setVisible(true);
    }
    
    private void addWorkerActions(Worker w){
        ctrl.setVisible(false);
        //actionCtrl.setVisible(false);
        actions.removeAll();
        actions.add(new CardPanel("", "Attack", ""));
        actions.add(new CardPanel("", "Move", ""));
        actions.add(new CardPanel("", "Build", ""));
        actionCtrl.setVisible(true);
        ctrl.setVisible(true);
    }
    */
    public static void main(String[] args) throws IOException {
        MainWindow window = new MainWindow();
    }
}
