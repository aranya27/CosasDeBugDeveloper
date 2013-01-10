package com.hilos.productorConsumidor;

import java.util.Random;
import java.util.Stack;


public class Productor implements Runnable{
    Recurso r;
    Random random;
    
    public Productor(Recurso r){
        this.r = r;
        random = new Random();
    }
    
    @Override
    public void run() {
        while(true){
            Integer i = random.nextInt(10);
            System.out.println(Thread.currentThread().getName()+" intenta agregar el valor "+i+" a "+r.getNombre());
            r.agregar(i);
        }
    }
    
}
