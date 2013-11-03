package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
        urlPatterns={"/servletCosas/*"},
        initParams={
            @WebInitParam(name="parametroInitServlet",value="holaaa")
            }
        )
public class ServletCosas extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        doPost(req,res);
        this.getServletConfig().getServletContext();
    }
    
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        PrintWriter out = res.getWriter();
        res.setContentType("text/plain");
        
        //Obteniendo el parametro de contexto parametroContexto
        
        out.println(this.getInitParameter("parametroInitServlet"));
        out.println(this.getServletConfig().getInitParameter("parametroInitServlet"));
        
        //Obteniedo el parametro de servlet parametroContexto
        out.println(this.getServletContext().getInitParameter("parametroContexto"));
        out.println(req.getSession().getServletContext().getInitParameter("parametroContexto"));
        
        //Obteniedo el path del contexto
        out.println("req.getContextPath() = "+req.getContextPath());
        
        //Obteniendo el path de este servlet
        out.println("req.getServletPath() = "+req.getServletPath());
        
        //Obteniendo el path info
        out.println("req.getPathInfo() = "+req.getPathInfo());
        
        
        out.println("req.getRequestURL() = "+req.getRequestURL());
        out.println("req.getRequestURI() = "+req.getRequestURI());
        
        out.println("req.getRequestedSessionId() = "+req.getRequestedSessionId());
        out.println("req.getMethod() = "+req.getMethod());
        
        out.close();
    }
}
