
package view;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel{
    private final JLabel label;
    
    public InfoPanel() {
        label = new JLabel();
        add(label);
        label.setFont(new Font("Consolas", Font.PLAIN, 60));
    }
    
    public void update(String info){
        label.setText(info);
    }

}
