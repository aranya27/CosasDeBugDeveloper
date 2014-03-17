package com.ejbs;

import javax.ejb.Asynchronous;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author armando
 */
@Stateful
public class EJBDePruebaTransac {

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void metodoBusiness() {
        System.out.println("MetodoBusiness invocado");
    }
    
    

    
}
