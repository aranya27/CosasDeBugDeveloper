package com.ejbs;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
@Local(YLocal.class)
@Remote(YRemote.class)
public class YBean implements YLocal,YRemote{
    
    @Resource
    SessionContext sctx;
    
    @Override
    public int suma(int a, int b) {
        YLocal yl = sctx.getBusinessObject(YLocal.class);
        
        System.out.println("¿Son iguales? = "+yl.equals(this));
        System.out.println("this = "+this);
        System.out.println("yl = "+yl);
        
        return a+b;
    }

    @Override
    public int resta(int a, int b) {
        return a - b;
    }
}
