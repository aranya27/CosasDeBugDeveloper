package com.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SesionEscuchador implements HttpSessionListener{

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Se ha creado una sesion");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Se ha destruido una sesion");
    }
    
}
