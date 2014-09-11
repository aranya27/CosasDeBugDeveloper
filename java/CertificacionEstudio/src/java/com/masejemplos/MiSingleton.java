
package com.masejemplos;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;


@Singleton
public class MiSingleton {
    @PostConstruct
    public void postConstruct(){
       System.out.println("Este EJB se ha creado");
    }

    @PreDestroy
    public void preDestroy(){
       System.out.println("Este EJB esta a punto de ser removido");
    }

    public String UnMetodoDelBean(){
        return "Esto es un metodo cualquiera";
    }
}
