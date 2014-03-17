/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejbs;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author armando
 */
@Stateless
@LocalBean
public class EJBDePruebaTransac {

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void metodoBusiness() {
        System.err.println("metodoBusiness invocado");
    }
    
    @Asynchronous
    public void metodoAsincrono(){
        try {
            Thread.sleep(5000);
            System.err.println("metodoAsincrono invocado");
        } catch (InterruptedException ex) {
            Logger.getLogger(EJBDePruebaTransac.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
