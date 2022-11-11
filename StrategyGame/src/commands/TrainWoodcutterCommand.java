/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

import model.interfaces.ICommand;
import model.trainers.Castle;

/**
 *
 * @author laszl
 */
public class TrainWoodcutterCommand implements ICommand {
    private final Castle castle;
    
    public TrainWoodcutterCommand(Castle castle){
        this.castle = castle;
    }
    
    @Override
    public void execute() {
        this.castle.trainWoodcutter();
    }

    @Override
    public boolean needTarget(){
        return false;
    }  
    
    @Override
    public String toString(){
        return "Train Woodcutter";
    }
    
}
