package com.hilos.productorConsumidor;



public class Main {
    public static void main(String[] args){
        Recurso r = new Recurso("R1");
        
        Productor p1 = new Productor(r);
        Consumidor c1 = new Consumidor(r);
        
        Thread tp1 = new Thread(p1,"P1");
        Thread tc1 = new Thread(c1,"C1");
        
        tp1.start();
        tc1.start();
        
    }
}
