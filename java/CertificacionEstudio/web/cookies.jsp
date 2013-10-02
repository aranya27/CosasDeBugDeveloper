<%-- 
    Document   : cookies
    Created on : 08-sep-2013, 19:55:58
    Author     : armando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%!
            public void jspInit(){
                //session.getAttribute("sdf"); Esto no compilara
            }
        %>
        
        
        <%
            response.addCookie(new Cookie("nombre","joe"));
            
        %>
        
        
        Nombre: ${cookie.nombre.value}
        <br />
    </body>
</html>
