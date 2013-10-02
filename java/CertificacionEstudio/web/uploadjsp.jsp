<%-- 
    Document   : uploadjsp
    Created on : 13-jul-2013, 18:32:55
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
        <form enctype="multipart/form-data" method="post" action="UploadCaliz">
            nombre: <input name="txtNombre" />
            <br />
            archivo: <input type="file" name="archivo" />
            <br />
            <input type="submit" value="Mandar" />
        </form>
    </body>
</html>
