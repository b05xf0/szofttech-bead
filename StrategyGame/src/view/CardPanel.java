/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author laszl
 */
public class CardPanel extends JPanel{
    JLabel header, body, footer;
    public CardPanel(){
        setOpaque(true);
        setBackground(new Color(0,0,0,16));
        setLayout(new BorderLayout());
        this.header = new JLabel();
        this.body = new JLabel();
        this.footer = new JLabel();
        this.header.setBorder(new EmptyBorder(10,10,10,10));
        this.body.setBorder(new EmptyBorder(10,10,10,10));
        this.footer.setBorder(new EmptyBorder(10,10,10,10));
        this.header.setFont(new Font("Consolas", Font.PLAIN, 12));
        this.body.setFont(new Font("Consolas", Font.PLAIN, 24));
        this.footer.setFont(new Font("Consolas", Font.PLAIN, 12));
        add(this.header,BorderLayout.PAGE_START);
        add(this.body,BorderLayout.CENTER);
        add(this.footer,BorderLayout.PAGE_END);
    }
    public CardPanel(String header,String body, String footer){
        this();
        update(header,body,footer);
    }
    public final void update(String header,String body, String footer){

        this.header.setText(header);
        this.body.setText(body);
        this.footer.setText(footer);
        //.validate();
    }
    /*
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getParent().getHeight()/2, getParent().getHeight()/4);
    } 
    */

}


