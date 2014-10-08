<%@page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Probando Objetos implicitos</title>
    </head>
    <body>
        <h1>Objetos implicitos en JSP(En total hay 9)</h1>
        <h2>requets</h2>
        <%=request.getParameter("txtParametro") %>
        <br />
        <hr />
        <h2>response</h2>
        <%
            response.setContentType("text/html");
            response.addCookie(new Cookie("miCookie","hola123"));
            for(Cookie c : request.getCookies()) out.println("Nombre del cookie="+c.getName()+". Valor de cookie="+c.getValue()+" <br />");
            
        %>
        <hr />
        <h2>out</h2>
        <%
            out.println("la clase de out es = "+out.getClass());
            
        %>
        <hr />
        <h2>session</h2>
        <%
            session.setAttribute("nombre", "Armando");
            out.println("Nombre = "+session.getAttribute("nombre"));
        %>
        <br />
        Si pongo session="false" en la directiva page entonces session no existe
        
    </body>
</html>
