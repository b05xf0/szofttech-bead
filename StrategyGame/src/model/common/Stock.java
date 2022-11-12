/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.common;

import commands.IllegalCommandException;
import model.GameState;

/**
 *
 * @author sonrisa
 */
public class Stock {
    
    public static final int DEFAULT_GOLD = 1000;
    public static final int DEFAULT_LUMBER = 1000;
    public static final int DEFAULT_FOOD = 1000;
    
    
    private int gold;
    private int lumber;
    private int food;
    
    public Stock(int gold, int lumber, int food){
        this.gold = gold;
        this.lumber = lumber;
        this.food = food;
    }
    public Stock(Stock s) {
        this(s.gold,s.lumber,s.food);
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
    
    public Stock increment(Stock s){
        this.gold += s.gold;
        this.lumber += s.lumber;
        this.food += s.food;
        
        return this;
    }
    
    public Stock decrement(Stock s) throws IllegalCommandException {
        if(gold < s.gold || lumber < s.lumber || food < s.food){
            throw new IllegalCommandException(GameState.ERR_NOT_ENOUGH_RESOURCES);
        } else {
            this.gold -= s.gold;
            this.lumber -= s.lumber;
            this.food -= s.food;
        }
        return this;
    }
    
    public Stock multiply(int amount){
        this.gold *= amount;
        this.lumber *= amount;
        this.food *= amount;
        
        return this;
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
