package com.interceptores;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.ExcludeDefaultInterceptors;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;


@Stateful
@LocalBean
@Interceptors(InterceptorA.class)
public class BeanInterceptado {
    
    //@ExcludeDefaultInterceptors
    //@ExcludeClassInterceptors
    @Interceptors(InterceptorB.class)
    public String metodo(){
        System.out.println("Invocacion de metodo de bean interceptado");
        
        return "String desde BeanInterceptado";
    }
    
    @AroundInvoke
    public Object metodoEnClaseInterceptador(InvocationContext inInvocationContext) throws Exception
    {
        System.out.println("4. InterceptorBean - Entering method: " + inInvocationContext.getMethod().getName());
        Object theResult = inInvocationContext.proceed();
        System.out.println("4. InterceptorBean - Entering method: " + inInvocationContext.getMethod().getName());
        return theResult;
    }
    
}
