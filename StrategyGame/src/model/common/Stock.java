/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.common;

/**
 *
 * @author sonrisa
 */
public class Stock {
    private int gold;
    private int lumber;
    private int food;
    
    public Stock(int gold, int lumber, int food){
        this.gold = gold;
        this.lumber = lumber;
        this.food = food;
    }
    
    public int getGold(){
        return this.gold;
    }
    
    public int getLumber(){
        return this.lumber;
    }
    
    public int getFood(){
        return this.food;
    }
    
    public Stock increment(Stock s){
        this.gold += s.getGold();
        this.lumber += s.getLumber();
        this.food += s.getFood();
        
        return this;
    }
    
    public Stock decrement(Stock s){
        this.gold -= s.getGold();
        this.lumber -= s.getLumber();
        this.food -= s.getFood();
        
        return this;
    }
    
    public Stock multiply(int amount){
        this.gold *= amount;
        this.lumber *= amount;
        this.food *= amount;
        
        return this;
    }
}
