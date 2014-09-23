package com.masejemplos;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(
        filterName="MiFiltro",
        urlPatterns="/MiServlet"
)
public class MiFiltro implements javax.servlet.Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        System.out.println("Se ha interceptado la petición");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}




