package com.masejemplos;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;

@Stateful
public class MiSFSB {

    @PostConstruct
    public void postConstruct(){
       System.out.println("Este EJB se ha creado ");
    }
    
    @PrePassivate
    public void prePassivate(){
        System.out.println("Este EJB esta a punto de ser pasivado");
    }
    
    @PostActivate
    public void postActivate(){
        System.out.println("Este EJB se encontraba pasivado pero ha sido activado");
    }

    @PreDestroy
    public void preDestroy(){
       System.out.println("Este EJB esta a punto de ser removido");
    }
    
    public String UnMetodoDelBean(){
        return "Esto es un metodo cualquiera";
    }
}




