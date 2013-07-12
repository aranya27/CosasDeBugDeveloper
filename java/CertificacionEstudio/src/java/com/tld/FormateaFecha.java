package com.tld;

import javax.servlet.jsp.JspException;  
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormateaFecha extends SimpleTagSupport{
    private String formatoFecha;

    @Override
    public void doTag() throws JspException, IOException{ //this is the method that is called when the custom tag <mytaglibrary:randomnumber greaterthan="2"> is called from a JSP page 
        Date fecha = new Date();
        String fechaFormateada;
        try{
            SimpleDateFormat df = new SimpleDateFormat(formatoFecha);
           fechaFormateada = df.format(fecha);
        }catch( Exception e ){
            fechaFormateada = "hubo pedos";
        }

        getJspContext().getOut().write(fechaFormateada); 
    }
    
    public String getFormatoFecha() {
        return formatoFecha;
    }

    public void setFormatoFecha(String formatoFecha) {
        this.formatoFecha = formatoFecha;
    }

    
}
