/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

import model.interfaces.ICommand;
import model.trainers.Castle;
import model.workers.Miner;

/**
 *
 * @author laszl
 */
public class TrainMinerCommand implements ICommand {
    private final Castle castle;
    
    public TrainMinerCommand(Castle castle){
        this.castle = castle;
    }
    
    @Override
    public void execute() {
        this.castle.trainMiner();
    }
    
    @Override
    public String toString(){
        return "Train Miner";
    }
    
}
