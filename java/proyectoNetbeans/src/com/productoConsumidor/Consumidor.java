/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.productoConsumidor;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author armando
 */
public class Consumidor implements Runnable  {
    Recurso r;
    public Consumidor(Recurso r){
        this.r = r;
    }
    @Override
    public void run() {
        Random random = new Random();
        while(true){
            int i = r.consumir();
            System.out.println("Se consumio "+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
