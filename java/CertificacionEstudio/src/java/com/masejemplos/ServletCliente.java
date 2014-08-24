package com.masejemplos;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletCliente", urlPatterns = {"/ServletCliente"})
public class ServletCliente extends javax.servlet.http.HttpServlet {
    
    @EJB
    BeanDeEjemplo myEJB;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        java.io.PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cliente EJB</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Mensaje desde EJB = " + myEJB.HolaMundo() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
}




