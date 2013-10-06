package com.servletDinamico;

import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;


//@WebListener Comento esto porque ya lo puse en el DD
public class AgregaServletDinamico implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Contexto inicializado");
        final ServletContext servletContext = sce.getServletContext();
        final ServletRegistration.Dynamic dynamic = servletContext.addServlet("Example Servlet", ServletDin.class);
        dynamic.addMapping("/ServletDin");
 
        final Map<String, ? extends ServletRegistration> map = servletContext.getServletRegistrations();
        for (String key : map.keySet()) {
            servletContext.log("Registered Servlet: " + map.get(key).getName());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Contexto destruido");
    }
    
}
