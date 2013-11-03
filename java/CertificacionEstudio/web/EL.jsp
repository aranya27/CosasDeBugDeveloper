<%-- 
    Document   : EL
    Created on : 09-sep-2013, 20:19:11
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
        parametro = ${pageContext.request.getParameter("parametro")}
        <br />
        pageContext.request es igual a requestScope = ${pageContext.request eq requestScope}
        <br />
        
        <%
            ((HttpServletResponse)pageContext.getResponse()).addCookie(new Cookie("miCookie","cookieNice"));
        %>
        
        Valor de cookie = ${cookie.miCookie.value}
        <br />
        Valor de cookie = ${cookie["miCookie"].value}
        
    </body>
</html>
