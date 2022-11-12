/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;


import model.interfaces.ICommand;

/**
 *
 * @author laszl
 */
public class AttackActionCommand extends ActionCommand {
   
    public AttackActionCommand(ICommand command, String name){
        super(command, name);
    }
    
    @Override
    public final boolean needTargetField(){
        return true;
    }    
    
    @Override
    public final boolean needTargetUnit(){
        return true;
    }    
    
}
