package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
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
import model.GameManager;
import model.GameState;

public class MainWindow extends JFrame {

    private final GameManager game;
    
    private final MapPanel map;
    
    private final CardPanel gameInfo;
    private final CardPanel playerInfo;
    private final CardPanel fieldInfo;
    private final JButton endTurnButton;
    private final JPanel ctrl;
    
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
        map = createMapPanel();
        gameInfo = new CardPanel();
        playerInfo = new CardPanel();
        fieldInfo = new CardPanel();
        endTurnButton = new JButton("End Turn");
        ctrl = createControlPanel();
        getContentPane().add(ctrl, BorderLayout.LINE_START);
        getContentPane().add(map, BorderLayout.LINE_END);
        setExtendedState( JFrame.MAXIMIZED_BOTH );
        //setResizable(false);
        setMinimumSize(new Dimension(800,600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

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
        ctrl.setVisible(true);
        map.repaint();
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
                            //ctrl.refresh();
                        }
                        default -> {}
                    }

                }
            });
        return mapPanel;
    }
    
    private JPanel createControlPanel() {
        JPanel ctrlPanel = new JPanel();
        ctrlPanel.setVisible(false);
        ctrlPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        gameInfo.add(endTurnButton,BorderLayout.LINE_END);
        ctrlPanel.add(gameInfo);
        ctrlPanel.add(playerInfo);
        ctrlPanel.add(fieldInfo);
        endTurnButton.addActionListener((ActionEvent e) -> {
            game.endTurn();
            map.repaint();
            
        });
        return ctrlPanel;
    }
    
    public static void main(String[] args) throws IOException {
        MainWindow window = new MainWindow();
    }
}
