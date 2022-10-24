/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.common;

/**
 *
 * @author sonrisa
 */
public class Position {
    private int x;
    private int y;
    
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public Position(Position p){
        this.x = p.GetX();
        this.y = p.GetY();
    }
    
    public int GetX(){
        return this.x;
    }
    
    public int GetY(){
        return this.y;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        
        if(!(o instanceof Position)) return false;
        
        Position p = (Position)o;
        
        return Integer.compare(p.GetX(), this.x) == 0
                && Integer.compare(p.GetY(), this.y) == 0;
    }
}
