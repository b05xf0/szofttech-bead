package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
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
    private final ControlPanel ctrl;

    
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
        setJMenuBar(menuBar);

        getContentPane().setLayout(new BorderLayout());
        map = new MapPanel(game,this);
        ctrl = new ControlPanel(game);
        getContentPane().add(map, BorderLayout.EAST);
        getContentPane().add(ctrl, BorderLayout.WEST);
        
        setExtendedState( JFrame.MAXIMIZED_BOTH );
        setResizable(false);
        //pack();
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
    }
    
    public void refresh() {
        map.repaint();
        ctrl.refresh();
    }
    
    public static void main(String[] args) throws IOException {
        MainWindow window = new MainWindow();
    }
}


/*
public class AspectRatio {
    public static void main(String[] args) {
        final JPanel innerPanel = new JPanel();
        innerPanel.setBackground(Color.YELLOW);

        final JPanel container = new JPanel(new GridBagLayout());
        container.add(innerPanel);
        container.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizePreview(innerPanel, container);
            }
        });
        final JFrame frame = new JFrame("AspectRatio");
        frame.getContentPane().add(container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    private static void resizePreview(JPanel innerPanel, JPanel container) {
        int w = container.getWidth();
        int h = container.getHeight();
        int size =  Math.min(w, h);
        innerPanel.setPreferredSize(new Dimension(size, size));
        container.revalidate();
    }
}

*/

/*import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

    MainWindow() {
        super("GridLayout");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container contenant = getContentPane();
        contenant.setLayout(new GridLayout(32, 32));

        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                contenant.add(new CaseEchiquier(i, j));
            }
        }

        pack();
        setVisible(true);
    }

    class CaseEchiquier extends JPanel {

        private int lin, col;

        CaseEchiquier(int i, int j) {
            lin = i;
            col = j;
            setPreferredSize(new Dimension(80, 75));
            setBackground((i + j) % 2 == 0 ? Color.WHITE : Color.GRAY);
            addMouseListener(new MouseAdapter() {
                private Color background;

                @Override
                public void mousePressed(MouseEvent e) {
                    background = getBackground();
                    setBackground(Color.RED);
                    repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    setBackground(background);
                }
            });
//            addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent evt) {
//                    System.out.println((char) ('a' + col) + "" + (8 - lin));
//
//                }
//            });
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //JFrame.setDefaultLookAndFeelDecorated(true);
                MainWindow window = new MainWindow();
            }
        });
    }
}
*/
/*
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class YouAreSoSquare {

    private static JPanel createPanel() {
        // GBL is important for the next step..
        JPanel gui = new JPanel(new GridBagLayout());
        JPanel squareComponent = new JPanel() {
            private static final long serialVersionUID = 1L;
            @Override
            public Dimension getPreferredSize() {
                // Relies on being the only component
                // in a layout that will center it without
                // expanding it to fill all the space.
                Dimension d = this.getParent().getSize();
                int newSize = d.width > d.height ? d.height : d.width;
                newSize = newSize == 0 ? 100 : newSize;
                return new Dimension(newSize, newSize);
            }
        };
        squareComponent.setBackground(Color.RED);
        gui.add(squareComponent);
        return gui;
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception useDefault) {
                }
                JFrame mainFrame = new JFrame("..So Square");
                mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                mainFrame.setLocationByPlatform(true);
                mainFrame.add(createPanel());
                mainFrame.pack();
                mainFrame.setMinimumSize(mainFrame.getSize());
                mainFrame.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}

*/