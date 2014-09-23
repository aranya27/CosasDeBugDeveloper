package com.masejemplos;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@WebFilter(
        filterName="MiFiltro",
        urlPatterns="/MiServlet"
)
public class FiltroRequest implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws javax.servlet.ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws java.io.IOException, javax.servlet.ServletException {
        HttpServletRequestWrapper requestModificado = 
        new HttpServletRequestWrapper( (HttpServletRequest)request ) {
            @Override
            public String getParameter(String name) {
                String resultado = super.getParameter(name);
                return resultado == null ? "" : resultado;
            }
        };
        chain.doFilter(requestModificado, response);
    }

    @Override
    public void destroy() {}
}



