/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

import model.common.Unit;
import model.field.Field;
import model.interfaces.ICommand;

/**
 *
 * @author sonrisa
 */
public class ActionCommand {
    
    private final ICommand executable;
    private final String name;
    
    public ActionCommand(ICommand executable, String name){
        this.executable = executable;
        this.name = name;
    }
    
    public boolean needTargetField() { return false; }
    
    public boolean needTargetUnit() { return false; }
   
    public void execute(Field targetField, Unit targetUnit) throws IllegalCommandException {
        this.executable.run(targetField,targetUnit);
    }
    
    @Override
    public String toString(){ return name; }
}
