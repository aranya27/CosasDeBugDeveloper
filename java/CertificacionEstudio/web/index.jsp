    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ taglib uri="/WEB-INF/tlds/myTagLibrary" prefix="myFn" %>

    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Ejemplo custom tags</title>
        </head>
        <body>
            <c:set var="unNumero" value="3" />
            Suma = <myFn:sumador operando1="${unNumero}" operando2="5" />
        </body>
    </html>

    
    
    