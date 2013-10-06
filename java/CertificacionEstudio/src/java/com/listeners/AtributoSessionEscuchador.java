package com.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;


@WebListener
public class AtributoSessionEscuchador implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("Se ha agregado un atributo en la Sesion");
        System.out.println("Nombre = "+event.getName());
        System.out.println("Valor = "+event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("Se ha removido un atributo en la Sesion");
        System.out.println("Nombre = "+event.getName());
        System.out.println("Valor = "+event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("Se ha reemplazado un atributo en la Sesion");
        System.out.println("Nombre = "+event.getName());
        System.out.println("Valor = "+event.getValue());
    }
    
}
