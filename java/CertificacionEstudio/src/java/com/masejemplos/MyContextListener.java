package com.masejemplos;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyContextListener implements ServletContextListener {

        @Override
        public void contextDestroyed(ServletContextEvent event) {
        }

        @Override
        public void contextInitialized(ServletContextEvent event) {
                ServletContext context = event.getServletContext();

                Dynamic dynamic = context.addServlet("MiServlet", MiServlet.class);
                dynamic.addMapping("/MiServlet");
        }
}





