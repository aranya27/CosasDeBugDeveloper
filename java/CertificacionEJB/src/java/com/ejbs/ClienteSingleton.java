/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ClienteSingleton {
    
    @EJB
    EJBDePruebaTransac myEJB;
    
    @PostConstruct
    public void llamarEJB() {
        //myEJB.metodoAsincrono();
        myEJB.metodoBusiness();
    }    
}