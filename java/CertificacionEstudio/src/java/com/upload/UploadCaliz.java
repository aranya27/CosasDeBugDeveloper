package com.upload;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author armando
 */
@WebServlet(name = "UploadCaliz", urlPatterns = {"/UploadCaliz"})
//@MultipartConfig(location = "/tmp")
@MultipartConfig
public class UploadCaliz extends HttpServlet {
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //getServletContext().getRequestDispatcher("/WEB-INF/views/fileUpload.jsp").forward(req, res);
         System.out.println("Hola");
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        Collection<Part> parts = req.getParts();
        for(Part part : parts) {
            System.out.println("Name:");
            System.out.println(part.getName());
            System.out.println("Header: ");
            for(String headerName : part.getHeaderNames()) {
                System.out.println(headerName);
                System.out.println(part.getHeader(headerName));
            }
            System.out.println("Size: ");
            System.out.println(part.getSize());
            part.write(part.getName() + "-down");
        }
        
        
        
        /*
        String txtNombre = req.getParameter("txtNombre");
        System.out.println("txtNombre = "+txtNombre);
        req.getPart("archivo").write(txtNombre);
        if(req.getPart("archivo")!=null){
            PrintWriter out = res.getWriter();
            out.write("<h3>File uploaded successfully</h3>");
        }
        */
        
        
        
    }
}
