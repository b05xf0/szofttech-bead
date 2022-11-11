/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author laszl
 */
public class InfoLabel extends JLabel {

    public enum LabelSize{
        S,M,L,XL
    }
    //private String info;
    public InfoLabel(LabelSize size, String info){
        super(info);
        setBorder(new EmptyBorder(3,3,3,3));
        switch(size){
            case S -> setFont(new Font("Consolas", Font.PLAIN, 10));
            case M -> setFont(new Font("Consolas", Font.PLAIN, 12));
            case L -> setFont(new Font("Consolas", Font.PLAIN, 16));
            case XL -> setFont(new Font("Consolas", Font.PLAIN, 24));
            default -> {}
        }
    }
}
