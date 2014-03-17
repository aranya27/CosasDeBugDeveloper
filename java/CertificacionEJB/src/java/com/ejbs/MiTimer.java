package com.ejbs;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;


@Singleton
@LocalBean
@Startup
public class MiTimer {
    @Resource
    TimerService timerService;
    
    private @Resource SessionContext ctx;
    
    //@Schedule(second="10", minute="*",hour="*", persistent=false)
    public void imprimeFecha(){
        System.err.println("la Fecha = "+new java.util.Date());
    }
    
    
    public void imprimeFecha2(){
        //timerService.createTimer(new Date(new Date().getTime() + 5000), "Hello World");
        //ctx.getTimerService().createTimer(new Date(new Date().getTime() + 5000), "Hello World2");
    }
    
    
    @PostConstruct
    public void iniciandoConScheduledExpression(){
        ScheduleExpression expression = new ScheduleExpression();
        expression.second("*/3").minute("*").hour("*");
        Timer t = timerService.createCalendarTimer(expression);
        System.out.println("getNextTimeout = "+t.getNextTimeout());
    }
    /*
    @Timeout
    public void execute(){
        System.out.println("----Invoked: " + new java.util.Date());
    }
    */
    @Timeout
    public void timeoutHandler(javax.ejb.Timer timer)
    {
       System.out.println("---------------------");
       System.out.println("* Received Timer event: " + timer.getInfo());
       System.out.println("---------------------");

       //timer.cancel();
    }
}
