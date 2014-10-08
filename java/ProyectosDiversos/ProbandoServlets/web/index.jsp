<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:forward page="/jsp1.jsp" >
            <jsp:param name="miParametro" value="12345" />
        </jsp:forward>
        <%
            int a = 0/0;
        %>
    </body>
</html>
