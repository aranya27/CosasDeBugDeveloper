package com.hilos.productorConsumidor;



public class Consumidor implements Runnable{
    Recurso r;
    
    public Consumidor(Recurso r){
        this.r = r;
    }
    
    @Override
    public void run() {
        while(true){
            System.out.println(Thread.currentThread().getName()+" intenta consumir un valor de "+r.getNombre());
            r.quitar();
        }
    }
    
    
    
}
