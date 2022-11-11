/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 *
 * @author laszl
 */

public class CardPanel extends JPanel{
    public CardPanel(){
        super();
        setVisible(false);
        setOpaque(true);
        setBackground(new Color(0,0,0,16));
        setLayout(new BorderLayout());
    }
    public CardPanel(JButton button){
        this();
        //add((new GridPanel(0,1)).add(button),BorderLayout.LINE_END);
        add(button,BorderLayout.LINE_END);
    }
}


