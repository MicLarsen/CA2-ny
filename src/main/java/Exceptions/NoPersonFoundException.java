/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Michael
 */
public class NoPersonFoundException extends RuntimeException{
    
    public NoPersonFoundException(String message) {
        super(message);
    }
    
}
