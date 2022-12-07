/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

import model.GameState;

/**
 *
 * @author laszl
 */
public class IllegalCommandException extends Exception{
    private final GameState errState;
    public IllegalCommandException(GameState state){
        super();
        errState = state;
    }
    
    public GameState getErrState() { return errState; }
}
