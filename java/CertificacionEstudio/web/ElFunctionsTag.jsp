<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/myTagLibrary" prefix="myFn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            request.setAttribute("fecha", new java.util.Date() );
        %>
        Color aleatorio: ${myFn:randomColor(fecha)}
        <br />
        Fecha: <myFn:formatin formatoFecha="dd/MM/yyyy" />
        <br />
        
        <%
            HashMap mapa = new HashMap();
            mapa.put("Armando", "10");
            mapa.put("Fernando", "9");
            mapa.put("Luis", "8");
            
            pageContext.setAttribute ("x", 100);
        %>
        
        ${pageScope.x}
        
        
        <br />
        <br />
        <h1>Listita</h1>
        <%
            String cadena = "Java,Python,C#";
        %>
        <myFn:creaLista cadena="<%=cadena %>" delimitador="," >Colado</myFn:creaLista>
    </body>
</html>
