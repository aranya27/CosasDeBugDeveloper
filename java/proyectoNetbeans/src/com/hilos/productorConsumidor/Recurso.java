package com.hilos.productorConsumidor;

import java.util.Stack;


public class Recurso {
    private Stack<Integer> s;
    private final int CANTIDADMAXIMA = 3;
    private final String nombre;
    
    
    public Recurso(String nombre){
        this.nombre = nombre;
        s = new Stack();
        printStatusRecurso();
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void printStatusRecurso(){
        StringBuffer sb = new StringBuffer("");
        for(Integer i : s){
            if(sb.toString().equals(""))
                sb.append(i);
            else
                sb.append(","+i);
        }
        System.out.println(this.getNombre()+" = "+sb);
    }
    
    
    public synchronized void agregar(Integer i){
        Thread t = Thread.currentThread();
        if(s.size()>=CANTIDADMAXIMA){
            try{
                System.out.println(t.getName()+" se detiene porque esta lleno el recurso "+this.getNombre());
                wait();
            }catch(InterruptedException e){}
        }
        s.push(i);
        printStatusRecurso();
        notifyAll();
        
    }
    
    public synchronized Integer quitar(){
        Thread t = Thread.currentThread();
        if(s.size()==0){
            try{
                System.out.println(t.getName()+" se detiene porque esta vacío el recurso "+this.getNombre());
                wait();
            }catch(InterruptedException e){}
        }
        Integer i = s.pop();
        printStatusRecurso();
        notifyAll();
        return i;
        
    }
    
}
