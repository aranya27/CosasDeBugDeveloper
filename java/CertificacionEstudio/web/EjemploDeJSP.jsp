<%--
    Página JSP de ejemplo
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo de JSP</title>
    </head>
    <body>
        <h1>La fecha de hoy es <%=new Date() %></h1>
    </body>
</html>
