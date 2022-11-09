/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author laszl
 */
public class ControlPanel extends JPanel{
    private int rowNum = 0;
    public ControlPanel(){
        setVisible(false);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
	JLabel label = new JLabel("3rd milestone");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.ipady = 0;       //reset to default
	c.weighty = 1.0;   //request any extra vertical space
	c.anchor = GridBagConstraints.PAGE_END; //bottom of space
	c.insets = new Insets(10,10,10,10);
	c.gridx = 0;
	c.gridy = 10; 
	add(label, c);        
    }
    
    public void addPanel(CardPanel p){
    	GridBagConstraints c = new GridBagConstraints();
	c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10,10,0,0);
	c.weightx = 0.5;
        c.gridx = 0;
	c.gridy = rowNum++;
        add(p, c);
    }

    /*
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getParent().getHeight()/2, getParent().getHeight()/4);
    } 
    */

}


