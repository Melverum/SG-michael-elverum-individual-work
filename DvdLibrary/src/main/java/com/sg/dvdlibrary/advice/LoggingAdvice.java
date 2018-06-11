/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.advice;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author Melve
 */
public class LoggingAdvice {
    
    DvdLibraryDao dao;
 
    public LoggingAdvice(DvdLibraryDao dao) {
        this.dao = dao;
    }
 
    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            dao.writeAuditEntry(auditEntry);
        } catch (DvdLibraryDaoException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
}