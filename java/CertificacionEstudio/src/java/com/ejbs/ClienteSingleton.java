package com.ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class ClienteSingleton {
    
    @EJB
    EJBDePruebaTransac myEJB;
    
    @PostConstruct
    public void llamarEJB() {
        
        myEJB.metodoBusiness();
        //myEJB.MetodoBusiness();
        
    }

    
}
