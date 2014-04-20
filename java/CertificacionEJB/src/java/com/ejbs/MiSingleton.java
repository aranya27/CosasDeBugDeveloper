/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ejbs;

import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author armando
 */
@Singleton
@LocalBean
public class MiSingleton {
    
    public int entero;
    
    @Lock(LockType.WRITE)
    public void procesoLargo() {
        try{
            Thread.sleep(5000);
            System.out.println("Proceso largo de MiSingleton invocado");
        }catch(InterruptedException e){
            
        }
    }

    public void procesoCorto() {
    }

    
}
