/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Dimension;
import javax.swing.JButton;

/**
 *
 * @author laszl
 */
class CommandButton extends JButton{
    public CommandButton(String text){
        super(text);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getParent().getParent().getWidth()/6, getParent().getParent().getHeight()/12);
    }
}
