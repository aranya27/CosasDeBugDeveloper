<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:declaration>
            String cadena = "hola";
        </jsp:declaration>
        <jsp:scriptlet>
            cadena += " mundo";
        </jsp:scriptlet>
        El valor de a es:
        <jsp:expression>cadena</jsp:expression>
        <br />
        
        ${24 / 6 * 4}
    </body>
</html>
