package com.ejbs;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.TimerService;


@Stateless
@LocalBean
public class MiTimer {
    @Resource
    TimerService service;
    
    @Schedule(second="10", minute="*",hour="*", persistent=false)
    public void imprimeFecha(){
        System.err.println("la Fecha = "+new java.util.Date());
    }
}
