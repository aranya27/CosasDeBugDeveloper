<%-- 
    Document   : index
    Created on : 04-jul-2013, 20:29:55
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
        Valor de la variable msg establecida en scoping.jsp: 
        <%
            String msg = (String)application.getAttribute("msg");
            out.println(msg);
        %>
    </body>
</html>