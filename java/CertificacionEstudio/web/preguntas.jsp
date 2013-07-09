<%-- 
    Document   : preguntas
    Created on : 07-jul-2013, 20:02:45
    Author     : armando
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Pregunta 3</h2>
        <%
            request.setAttribute("vals", new String[]{"1","2","3","4"});
            request.setAttribute("index", "2");
        %>
        ${vals['2']}
        
        <br />
        <h2>Pregunta 5</h2>
        <%
            Map hobbies = new HashMap();
            hobbies.put("HIKING","Hiking");
            hobbies.put("SKING","Sking");
            hobbies.put("SCUBA","Scuba");
            application.setAttribute("hobbies", hobbies);
            
            
        %>
        <form>
            <input type = "radio" name = "hobbyEnum" value = "HIKING"> Hiking <br>
            <input type = "radio" name = "hobbyEnum" value = "SKING"> Sking <br>
            <input type = "radio" name = "hobbyEnum" value = "SCUBA"> SCUBA <br>
            <input type="submit" value="aceptar" />
        </form>
        Selecciono ${hobbies [paramValues.hobbyEnum[0]]}
        <br />
        
        ${hobbies.get(paramValues.hobbyEnum[0])} _ No es EL code snippet
        <br />
        
        <h2>Pregunta 6</h2>
        <% 
            int[] nums = {42,420,4200};
            request.setAttribute("foo", nums); 
        %> 
        ${5 + 3 lt 6} 
        ${requestScope['foo'][0] ne 10 div 0}
        ${10 div 0}
    </body>
</html>
