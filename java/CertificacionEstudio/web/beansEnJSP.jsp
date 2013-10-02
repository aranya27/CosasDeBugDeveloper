<%@page import="com.beans.Persona"%>
<%@page import="com.beans.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Beans en JSP</title>
    </head>
    <body>
        <%
            Empleado p = new Empleado();
            p.setIdEmpleado(10);
            p.setNombre("Pepito");
            request.setAttribute("empleado", p);
            
        %>
        <jsp:useBean id="pSession" class="com.beans.Empleado" type="com.beans.Persona" scope="session">
            <jsp:setProperty name="pSession" property="nombre" value="Armando session"  />
            <jsp:setProperty name="pSession" property="puesto" param="txtPuesto" />
        </jsp:useBean>
        <%
            out.println("Nombre de empleado session = "+pSession.getNombre());
            
        %>
        <br />
        <br />
        
        
        El nombre es:
        <jsp:getProperty name="empleado" property="nombre" />
        y su id de empleado es: <jsp:getProperty name="empleado" property="idEmpleado" />
        
        
        <br />
        <br />
        
        <jsp:useBean id="empleado2" class="com.beans.Empleado" type="com.beans.Persona" scope="request">
            <jsp:setProperty name="empleado2" property="nombre" value="Armando request"  />
            <jsp:setProperty name="empleado2" property="puesto" param="txtPuesto" />
        </jsp:useBean>
        El nombre es: <jsp:getProperty name="empleado2" property="nombre" />
        y su puesto es: <jsp:getProperty name="empleado2" property="puesto" />
        <br />
        <%
            Persona p2 = (Persona)request.getAttribute("empleado2");
            
            out.println("nombre: "+p2.getNombre());
        %>
        
        
        <br />
        <br />
        
        <jsp:useBean id="empleado3" class="com.beans.Empleado" type="com.beans.Persona" scope="application">
            <jsp:setProperty name="empleado3" property="nombre" value="Armando page (default)"  />
        </jsp:useBean>
        El nombre es: <jsp:getProperty name="empleado3" property="nombre" />
        <br />
        <%
            out.println("nombre: "+empleado3.getNombre());
        %>
        
        <br />
        <br />
        ${initParam.parametroContexto}
        <br />
        <%
            out.println(this.getServletContext().getInitParameter("parametroContexto"));
        %>
        
        <br /><br /> 
        
        
        
    </body>
</html>
