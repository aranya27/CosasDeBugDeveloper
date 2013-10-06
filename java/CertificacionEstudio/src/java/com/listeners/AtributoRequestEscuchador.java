package com.listeners;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class AtributoRequestEscuchador implements ServletRequestAttributeListener{

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("Se ha creado un atributo en el request");
        System.out.println("Nombre = "+srae.getName());
        System.out.println("Valor = "+srae.getValue());
        
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("Se ha removido un atributo en el request");
        System.out.println("Nombre = "+srae.getName());
        System.out.println("Valor = "+srae.getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("Se ha reemplazado un atributo en el request");
        System.out.println("Nombre = "+srae.getName());
        System.out.println("Valor = "+srae.getValue());
    }
    
}
