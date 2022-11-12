/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

/**
 *
 * @author laszl
 */
class CommandButton extends JButton{
    public CommandButton(String text){
        super(text);
        setBackground(new Color(162, 102, 42));
        setForeground(new Color(245, 230, 214));
        setFont(new Font("Consolas", Font.PLAIN, 18));
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getParent().getParent().getWidth()/6, getParent().getParent().getHeight()/12);
    }
}
