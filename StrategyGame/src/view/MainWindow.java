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
import model.trainers.Trainer;
import model.workers.Worker;

public class MainWindow extends JFrame {

    private final GameManager game;
    
    private final MapPanel map;
    
    private final CardPanel playerInfo;
    private final CardPanel fieldInfo;
    private final CardPanel unitCtrl;
    private final CardPanel actionCtrl;
    private final JButton endTurnButton;
    private final ControlPanel ctrl;
    private final JPanel units;
    private JPanel actions;
    
    public MainWindow() throws IOException {
        game = new GameManager();

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
	JLabel label = new JLabel("3rd milestone");
        
        map = createMapPanel();
        playerInfo = new CardPanel();
        fieldInfo = new CardPanel();
        unitCtrl = new CardPanel("Units","","");
        actionCtrl = new CardPanel("Actions","","");

        playerInfo.setVisible(false);
        fieldInfo.setVisible(false);
        unitCtrl.setVisible(false);
        actionCtrl.setVisible(false);

        endTurnButton = new JButton("End Turn");
        units = createUnitsPanel();
        actions = createActionsPanel();
        ctrl = createControlPanel();        

        unitCtrl.add(units,BorderLayout.CENTER);
        actionCtrl.add(actions,BorderLayout.CENTER);
        
        ctrl.addPanel(playerInfo);
        ctrl.addPanel(fieldInfo);
        ctrl.addPanel(unitCtrl);
        ctrl.addPanel(actionCtrl);
        
        getContentPane().add(ctrl, BorderLayout.CENTER);
        getContentPane().add(map, BorderLayout.LINE_END);
        ctrl.setVisible(true);

        setExtendedState( JFrame.MAXIMIZED_BOTH );
        //setResizable(false);
        setMinimumSize(new Dimension(800,600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        startGame();
        
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
        playerInfo.setVisible(true);
        map.repaint();
        updatePlayerInfo();
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
    
    private MapPanel createMapPanel() throws IOException{
        MapPanel mapPanel = new MapPanel(game);
        mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                switch(game.getState()) {
                        case SELECTFIELD -> {
                            game.selectField(map.getPosition(e.getX(),e.getY()));
                            map.repaint();
                            updateFieldInfo();
                        }
                        default -> {}
                    }
                }
            });
        return mapPanel;
    }
    
    private ControlPanel createControlPanel() {
        ControlPanel ctrlPanel = new ControlPanel();
        JPanel btnContainer = new JPanel();
        btnContainer.setOpaque(false);
        btnContainer.setBorder(new EmptyBorder(5,5,5,5));
        btnContainer.setLayout(new GridLayout(0,1,5,5));
        btnContainer.add(endTurnButton);
        playerInfo.add(btnContainer,BorderLayout.LINE_END);
        endTurnButton.addActionListener((ActionEvent e) -> {
            game.endTurn();
            map.repaint();
            updatePlayerInfo();
            updateFieldInfo();
            
        });
        return ctrlPanel;
    }
    private JPanel createUnitsPanel() {
        JPanel unitsPanel = new JPanel();
        unitsPanel.setOpaque(false);
        unitsPanel.setVisible(false);
        unitsPanel.setBorder(new EmptyBorder(5,5,5,5));
        unitsPanel.setLayout(new GridLayout(0,3,5,5));
        unitsPanel.setVisible(true);
        return unitsPanel;
    }
    
    private JPanel createActionsPanel(){
        JPanel actionsPanel = new JPanel();
        actionsPanel.setOpaque(false);
        actionsPanel.setVisible(false);
        actionsPanel.setBorder(new EmptyBorder(5,5,5,5));
        actionsPanel.setLayout(new GridLayout(0,4,5,5));
        actionsPanel.setVisible(true);
        return actionsPanel;
    }

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
        /*
        actions.add(new CardPanel("", "Train Peasant", ""));
        actions.add(new CardPanel("", "Train Swordsman", ""));
        actions.add(new CardPanel("", "Train Knight", ""));
        actions.add(new CardPanel("", "Train Dragon", ""));
        */
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
    
    public static void main(String[] args) throws IOException {
        MainWindow window = new MainWindow();
    }
}
