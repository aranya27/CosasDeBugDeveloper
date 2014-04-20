package com.ejbs;

import com.interceptores.BeanInterceptado;
import com.xmlBeans.UnBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;


@Singleton
@Startup
public class ClienteSingleton2 {
    
    @EJB
    UnBean bean;
    
    @EJB
    BeanInterceptado beanInterceptado;
    
    
    @PostConstruct
    public void llamarEJB() {
        //System.out.println("La suma es = "+bean.suma(2, 2));
        
        String s = beanInterceptado.metodo();
        System.out.println("Resultado final = "+s);
        
    }
}
