/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;
import resources.ResourceLoader;

/**
 *
 * @author laszl
 */
public class BackgroundPanel extends JPanel {
private final static Image bgImage = ResourceLoader.loadImage("resources/background.png");
    
    public BackgroundPanel(){
        super();
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;
        super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, null);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getParent().getWidth(), getParent().getHeight());
    }
}
