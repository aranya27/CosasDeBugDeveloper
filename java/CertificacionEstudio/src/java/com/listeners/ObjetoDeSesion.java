package com.listeners;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;


public class ObjetoDeSesion implements HttpSessionBindingListener{

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("Se ha agregado un objeto que implementa HttpSessionBindingListener");
        System.out.println("Nombre = "+event.getName());
        System.out.println("Valor = "+event.getValue());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("Se ha quitado un objeto que implementa HttpSessionBindingListener");
        System.out.println("Nombre = "+event.getName());
        System.out.println("Valor = "+event.getValue());
    }
    
}
