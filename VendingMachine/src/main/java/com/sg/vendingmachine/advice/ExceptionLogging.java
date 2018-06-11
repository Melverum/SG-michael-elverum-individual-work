/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.advice;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.servicelayer.VendingMachineInsufficientFundsException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author Melve
 */
public class ExceptionLogging {
    VendingMachineDao dao;
 
    public ExceptionLogging(VendingMachineDao dao) {
        this.dao = dao;
    }
 
    public void createAuditEntry(JoinPoint jp, Exception ex) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            dao.writeAuditEntry(ex.toString() + " " + auditEntry);
        } catch (VendingMachineDaoException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
//    public void fundsExceptionLog(JoinPoint jp, VendingMachineInsufficientFundsException ex){
//        
//        String auditEntry = "";
//        
//        Object[] args = jp.getArgs();
//        auditEntry = jp.getSignature().toString() + ": ";
//        for(Object currentArg: args){
//            auditEntry += currentArg;
//        }
//        
//        try{
//            dao.writeAuditEntry("InsufficientFundsException: " + auditEntry);
//        }catch (VendingMachineDaoException e){
//            System.err.println(
//                    "Error: Could not create audit entry in MachineLoggingAdvice.");
//        }
//    }
    
}
