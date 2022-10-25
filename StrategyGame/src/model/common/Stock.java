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
    
    public static final int DEFAULT_GOLD = 100;
    public static final int DEFAULT_LUMBER = 100;
    public static final int DEFAULT_FOOD = 100;
    
    
    private int gold;
    private int lumber;
    private int food;
    
    public Stock(int gold, int lumber, int food){
        this.gold = gold;
        this.lumber = lumber;
        this.food = food;
    }

    public Stock() {
        this(DEFAULT_GOLD,DEFAULT_LUMBER,DEFAULT_FOOD);
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
    
    public void increment(Stock s){
        this.gold += s.getGold();
        this.lumber += s.getLumber();
        this.food += s.getFood();
    }
    
    public void decrement(Stock s){
        this.gold -= s.getGold();
        this.lumber -= s.getLumber();
        this.food -= s.getFood();
    }
    
    public void multiply(int amount){
        this.gold *= amount;
        this.lumber *= amount;
        this.food *= amount;
    }
    
    public void init(){
        this.gold = DEFAULT_GOLD;
        this.lumber = DEFAULT_LUMBER;
        this.food = DEFAULT_FOOD;    
    }
    
    @Override
    public String toString(){
        return String.format("Gold: %d Lumber: %d Food: %d", gold, lumber, food); 
    }
    
}
