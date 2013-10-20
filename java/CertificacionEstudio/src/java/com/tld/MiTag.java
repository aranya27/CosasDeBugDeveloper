package com.tld;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class MiTag extends SimpleTagSupport{
    private String cadena;
    private String delimitador;
    
    
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        if (delimitador == null ) delimitador = ",";
        
        String[] items = cadena.split(delimitador);
        
        out.print("<ul>");
        for(String item : items){
            out.print("<li>"+item+"</li>");
        }
        
        StringWriter stringWriter = new StringWriter();
        this.getJspBody().invoke(stringWriter);
        out.print("<li>"+stringWriter.getBuffer()+"</li>");
        
        out.print("</ul>");
        
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String getDelimitador() {
        return delimitador;
    }

    public void setDelimitador(String delimitador) {
        this.delimitador = delimitador;
    }
    
    
    
    
}
