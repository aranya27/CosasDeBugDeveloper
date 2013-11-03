<%@page import="com.pasoobjetos.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Recibiendo objetos del servlet</h1>
        <%
            Product pop = new Product("Popo",10.0,true);
        %>
        ${pop.name}
        <br />
        <br />
        <jsp:useBean id="product" class="com.pasoobjetos.Product"  />
        
        <%=product.getName() %>__${product.name}
        <br />
        <jsp:getProperty name="product" property="name" />
        <br />
        <jsp:getProperty name="product" property="available" />
        
    </body>
</html>
