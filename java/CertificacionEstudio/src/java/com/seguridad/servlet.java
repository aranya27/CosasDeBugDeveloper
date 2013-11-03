package com.seguridad;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
        name="miServlet", 
        urlPatterns={"/servlet"},
        initParams={ 
                @WebInitParam(name="miParametro1",value="12345"),
                @WebInitParam(name="miParametro1",value="12345")
        }
)

//@ServletSecurity(value=@HttpConstraint(rolesAllowed = {"TutorialUser", "guest"}))

@ServletSecurity(
        httpMethodConstraints={ 
            //@HttpMethodConstraint(value="GET", transportGuarantee=TransportGuarantee.CONFIDENTIAL), 
            @HttpMethodConstraint("GET"),
            @HttpMethodConstraint(value="POST", rolesAllowed={"javaee"}, transportGuarantee=TransportGuarantee.NONE), 
            @HttpMethodConstraint(value="TRACE", emptyRoleSemantic=ServletSecurity.EmptyRoleSemantic.DENY) 
        }
)



/*
@DeclareRoles("aaaax")
@ServletSecurity(value = @HttpConstraint(value=EmptyRoleSemantic.PERMIT, rolesAllowed={"aaaa"}, transportGuarantee=TransportGuarantee.NONE),
        httpMethodConstraints = {@HttpMethodConstraint(value = "GET", emptyRoleSemantic = EmptyRoleSemantic.PERMIT)})
*/
public class servlet extends HttpServlet {
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servlet at " + request.getContextPath() + "</h1>");
            out.println("Mi parametro init = "+this.getInitParameter("miParametro1"));
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
