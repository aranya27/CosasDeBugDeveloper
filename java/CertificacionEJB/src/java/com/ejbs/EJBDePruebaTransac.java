/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejbs;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author armando
 */
@Stateful
@LocalBean
public class EJBDePruebaTransac {

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void metodoBusiness() {
        System.err.println("metodoBusiness invocado");
    }
    
    @Asynchronous
    @Remove //Este remove solo jalaria si este fuera un Stateful, con los stateless no hace nada. Solo el cliente invoca esto, no se invoca automaticamente
    public void metodoAsincrono(){
        try {
            Thread.sleep(5000);
            System.err.println("metodoAsincrono invocado");
        } catch (InterruptedException ex) {
            Logger.getLogger(EJBDePruebaTransac.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @PreDestroy
    public void avisoDeDestruccion(){
        System.out.println("===ADIOS===");
    }
    
}
