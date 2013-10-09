<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Recibiendo objetos del servlet</h1>
        
        <jsp:useBean id="product" type="com.pasoobjetos.Product" scope="session" />
        
        <%=product.getName() %>
        <br />
        <jsp:getProperty name="product" property="name" />
        <br />
        <jsp:getProperty name="product" property="available" />
        
    </body>
</html>
