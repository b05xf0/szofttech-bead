/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.interfaces;

import commands.IllegalCommandException;
import model.common.Unit;
import model.field.Field;

/**
 *
 * @author sonrisa
 */
public interface ICommand/*<T>*/ {
    //public /*T*/String execute();
    void run(Field targetField,Unit targetUnit) throws IllegalCommandException;
    //public boolean needTarget();
}
