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
    
    public ActionCommand(Callable execute){
        this._execute = execute;
    }
    
    @Override
    public T execute() {
        try {
            return (T) this._execute.call();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        finally{
            return null;
        }
    }
}
