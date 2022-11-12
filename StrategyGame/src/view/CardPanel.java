/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.common.Unit;
import model.common.UnitState;
import model.field.Field;


/**
 *
 * @author laszl
 */

public class CardPanel extends JPanel{
    public CardPanel(){
        super();
        setVisible(false);
        setOpaque(true);
        setBackground(new Color(255,255,255,127));
        setLayout(new BorderLayout());
    }
    public CardPanel(JButton button){
        this();
        //add((new GridPanel(0,1)).add(button),BorderLayout.LINE_END);
        add(button,BorderLayout.LINE_END);
    }
    public CardPanel(Unit unit){
        this();
        
        switch(unit.getState()){
            case READY -> setBackground(new Color(0,255,0,63));
            case BUSY -> setBackground(new Color(255,255,0,63));
            default -> setBackground(new Color(0,0,0,32));
        }
            
        add(new ProfilePanel(unit),BorderLayout.LINE_START);
    }
    
    public CardPanel(Field field){
        this();
       
        add(new ProfilePanel(field),BorderLayout.LINE_START);
    }
}


