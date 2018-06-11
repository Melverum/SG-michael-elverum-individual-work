/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.servicelayer;

/**
 *
 * @author Melve
 */
public class VendingMachineValidAmountException extends Exception{
     public VendingMachineValidAmountException(String message) {
        super(message);
    }
    
    public VendingMachineValidAmountException(String message,
            Throwable cause) {
        super(message, cause);
    }
    
}
