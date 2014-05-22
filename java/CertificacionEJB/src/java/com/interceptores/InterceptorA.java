package com.interceptores;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;


public class InterceptorA {
    
    public InterceptorA(){
        System.out.println("===============Se creo instancia de InterceptorA");
    }
    
    @AroundInvoke
    public Object logMethodEntryExit(InvocationContext inInvocationContext) throws Exception
    {
        System.out.println("2. InterceptorA - Entering method: " + inInvocationContext.getMethod().getName());
        Object theResult = inInvocationContext.proceed();
        System.out.println("2. InterceptorA - Entering method: " + inInvocationContext.getMethod().getName());
        return theResult;
    }
}
