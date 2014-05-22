/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.interceptores;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

public class InterceptorB {
    
    public InterceptorB(){
        System.out.println("===============Se creo instancia de InterceptorB");
    }
    
    @AroundInvoke
    public Object logMethodEntryExit(InvocationContext inInvocationContext) throws Exception
    {
        //return "String desde InterceptorB";
        System.out.println("3. InterceptorB - Entering method: " + inInvocationContext.getMethod().getName());
        Object theResult = inInvocationContext.proceed();
        System.out.println("3. InterceptorB - Entering method: " + inInvocationContext.getMethod().getName());
        return theResult;
    }
}
