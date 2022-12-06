/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.interfaces;

import commands.IllegalCommandException;

/**
 *
 * @author sonrisa
 */
public interface IRunnableWithException {
    void run(Object o) throws IllegalCommandException;
}
