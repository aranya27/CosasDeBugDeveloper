    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.interceptores;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;


public class DefaultInterceptor {
    
    
    public Object metodito(InvocationContext inInvocationContext) throws Exception
    {
        System.out.println("1. DefaultInterceptor - Entering method: " + inInvocationContext.getMethod().getName());
        /* Invoke the intercepted method on the EJB and save the result. */
        Object theResult = inInvocationContext.proceed();
        System.out.println("1. DefaultInterceptor - Exiting method: " +inInvocationContext.getMethod().getName());
        /* Return the result from the intercepted method. */
        return theResult;
    }
}
