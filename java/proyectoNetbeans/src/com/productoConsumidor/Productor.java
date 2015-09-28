
package com.productoConsumidor;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Productor implements Runnable {
    Recurso r;
    public Productor(Recurso r){
        this.r = r;
    }
    @Override
    public void run() {
        Random random = new Random();
        while(true){
            int i = random.nextInt(100);
            r.producir(i);
            System.out.println("Se produjo "+i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
