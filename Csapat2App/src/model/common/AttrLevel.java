/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.common;

/**
 *
 * @author sonrisa
 */
public enum AttrLevel {
    LOWEST(1),
    LOW(10),
    MEDIUM(25),
    HIGH(50),
    HIGHEST(100);
    
    private int value;
    
    AttrLevel(int value){
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }
}
