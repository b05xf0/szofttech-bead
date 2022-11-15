package model.common;

import commands.IllegalCommandException;
import model.GameState;

/**
 *
 * @author sonrisa
 */
public class Stock {

    private int gold;
    private int lumber;
    private int food;

    public Stock(int gold, int lumber, int food) {
        this.gold = gold;
        this.lumber = lumber;
        this.food = food;
    }

    public Stock(Stock s) {
        this(s.gold, s.lumber, s.food);
    }

    public void init(int gold, int lumber, int food) {
        this.gold = gold;
        this.lumber = lumber;
        this.food = food;
    }    
    
    public int getGold() {
        return this.gold;
    }

    public int getLumber() {
        return this.lumber;
    }

    public int getFood() {
        return this.food;
    }

    public Stock increase(Stock s) {
        this.gold += s.gold;
        this.lumber += s.lumber;
        this.food += s.food;
        return this;
    }

    public Stock decrease(Stock s) throws IllegalCommandException {
        if (gold < s.gold || lumber < s.lumber || food < s.food) {
            throw new IllegalCommandException(GameState.ERR_NOT_ENOUGH_RESOURCES);
        } else {
            this.gold -= s.gold;
            this.lumber -= s.lumber;
            this.food -= s.food;
        }
        return this;
    }

    public Stock multiply(int amount) {
        this.gold *= amount;
        this.lumber *= amount;
        this.food *= amount;
        return this;
    }

    @Override
    public String toString() {
        return String.format("Gold: %d Lumber: %d Food: %d", gold, lumber, food);
    }

}
