package com.filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter(filterName="MiFiltro1", urlPatterns="/*")
public class MiFiltro1 implements Filter {
    FilterConfig fc;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        fc = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Invocado "+fc.getFilterName()+" !!!!");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }
    
}
