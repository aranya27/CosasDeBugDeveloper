/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejbs;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 *
 * @author armando
 */
@Stateful
public class MyStatefulSessionBean {
    private int contador=0;
    
    public String hola(String nombre){
        return "Hola "+nombre;
    }
    
    public void incrementa(){
        contador++;
    }
    
    @PostConstruct
    public void postConstruct(){
        System.out.println("=========Se crea el bean");
    }
    @PreDestroy
    public void preDestroy(){
        System.out.println("===========Se destruye el bean");
    }
    @PrePassivate
    public void prePassivate(){
        System.out.println("===========Se pasibea el bean");
    }
    @PostActivate
    public void postActivate(){
        System.out.println("===========Se activa el bean");
    }
    
    
    
    
    public int getContador() {
        incrementa();
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    
}
