
package com.productoConsumidor;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Recurso {
    Queue<Integer> recurso = new ArrayBlockingQueue(10);
    
    public synchronized Integer consumir(){
        
        while( recurso.isEmpty() ){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Recurso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Integer i = recurso.poll();
        print();
        notifyAll();
        return i;
    }
    
    public synchronized void producir(Integer i){
        
        while(recurso.size() >= 10){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Recurso.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        recurso.add(i);
        print();
        notifyAll();
        
    }
    
    
    private void print(){
        System.out.print("Datos: ");
        for (Integer i : recurso) {
            System.out.print(" "+i);
        }
        System.out.println("");
    }
    
    
    
    
    
    public static void main(String[] args){
        Recurso r = new Recurso();
        new Thread(new Consumidor(r)).start();
        new Thread(new Productor(r)).start();
        
    }
}
