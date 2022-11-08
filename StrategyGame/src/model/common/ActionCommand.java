/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.common;

import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.interfaces.ICommand;

/**
 *
 * @author sonrisa
 */
public class ActionCommand<T> implements ICommand {
    
    Callable _execute;
    Callable<Boolean> _canExecute;
    
    public ActionCommand(Callable execute, Callable<Boolean> canExecute){
        this._execute = execute;
        this._canExecute = canExecute;
    }
    
    @Override
    public T execute() {
        try {
            if(this._canExecute.call()){
                return (T) this._execute.call();
            }
        } catch (Exception ex) {
            Logger.getLogger(ActionCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            return null;
        }
    }

    @Override
    public boolean canExecute() {
        try {
            return this._canExecute.call();
        } catch (Exception ex) {
            Logger.getLogger(ActionCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            return false;
        }
    }
}
