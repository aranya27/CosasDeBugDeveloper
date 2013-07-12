<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/myTagLibrary" prefix="myFn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        Color aleatorio: ${myFn:randomColor()}
        <br />
        Fecha: <myFn:formatin formatoFecha="dd/MM/yyyy" />
    </body>
</html>
