/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

import java.util.Objects;
import model.GameState;
import model.common.Unit;
import model.field.Field;
import model.interfaces.IRunnableWithException;

/**
 *
 * @author sonrisa
 */
public class ActionCommand {
    
    protected final IRunnableWithException executable;
    private final String name;
    private final GameState nextState;
    private Field targetField;
    private Unit targetUnit;
    
    public ActionCommand(IRunnableWithException executable, String name, GameState nextState){
        this.executable = executable;
        this.name = name;
        this.nextState = nextState;
    }
    
    public void execute() throws IllegalCommandException {
        this.executable.run(targetUnit != null
                            ? targetUnit
                            : targetField != null
                                ? targetField
                                : null
        );
    }
    
     public void setTargetField(Field targetField){
        this.targetField = targetField;
    }
    
    public Field getTargetField(){
        return targetField;
    }
    
    public void setTargetUnit(Unit targetUnit){
        this.targetUnit = targetUnit;
    }
    
    public Unit getTargetUnit(){
        return targetUnit;
    }

    public GameState getNextState(){ return nextState; }
    
    @Override
    public String toString(){ return name; }
    
}
