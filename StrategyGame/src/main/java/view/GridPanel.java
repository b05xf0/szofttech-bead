/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author laszl
 */
public class GridPanel extends JPanel {
 
    public GridPanel(int rows, int cols){
        super();
        setOpaque(false);
        setBorder(new EmptyBorder(5,5,5,5));
        setLayout(new GridLayout(rows,cols,5,5));
    }

}
