/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejbs;

import java.rmi.RemoteException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJBContext;
import javax.ejb.EJBException;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 *
 * @author armando
 */
@Stateful
@LocalBean
@TransactionManagement( TransactionManagementType.CONTAINER )
public class EJBDePruebaTransac implements javax.ejb.SessionSynchronization{//Solo los stateful pueden implementar javax.ejb.SessionSynchronization
    
    @Resource 
    private EJBContext ejbContext;
    
    @Resource
    private SessionContext sessionContext;
    
    @PostConstruct
    private void post(){
        
    }

    //@TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void metodoBusiness() {
        System.err.println("metodoBusiness invocado");
        //sessionContext.setRollbackOnly(); //Solo para container-managed transactions, si se ejecuta en un bean-managed transaction esta madre truena
        //System.out.println("sessionContext.getEJBObject() = "+sessionContext.getEJBObject());
        //System.out.println("ejbContext.getRollbackOnly() = "+ejbContext.getRollbackOnly());
        
        //sessionContext.getEJBLocalObject(); //Esto truena no se porque
        //sessionContext.getEJBObject();  //Esto truena no se porque
    }
    
    @Asynchronous
    //@Remove //Este remove solo jalaria si este fuera un Stateful, con los stateless no hace nada. Solo el cliente invoca esto, no se invoca automaticamente
    public void metodoAsincrono() throws Exception{
        try {
            Thread.sleep(5000);
            System.err.println("metodoAsincrono invocado");
        } catch (InterruptedException ex) {
            Logger.getLogger(EJBDePruebaTransac.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new Exception("XXX");
    }
    
    public Future<Integer> otroMetodoAsincrono() throws Exception{
        if(true) throw new Exception("XXXX");
        return new AsyncResult<Integer>(new Integer(404));
    }
    
    @PreDestroy
    public void avisoDeDestruccion(){
        System.out.println("===ADIOS===");
    }

    @Override
    public void afterBegin() throws EJBException, RemoteException {
        System.out.println("!---Una nueva transaccion a comenzado");
    }

    @Override
    public void beforeCompletion() throws EJBException, RemoteException {
        System.out.println("!---La transaccion esta a punto de ser commiteada");
    }

    @Override
    public void afterCompletion(boolean committed) throws EJBException, RemoteException {
        System.out.println("!---La transaccion ha sido terminada. ¿Hubo commit? R="+committed);
    }
    
}
