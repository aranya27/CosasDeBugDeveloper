<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <c:set var="salary" value="100.80" scope="session" />
        
        El valor de salary es <c:out value="${salary}" />
        <br />
        El valor de salary es <%=session.getAttribute("salary") %>
        <br />
        El valor de salary es ${sessionScope['salary']}
        <br />
        <br />
        <br />
        <jsp:useBean id="personita" class="com.beans.Empleado" scope="page" />
        
        El nombre de la persona es: <jsp:getProperty name="personita" property="nombre" /><br />
        <c:set target="${personita}" property="nombre" value="Armando" />
        El nombre de la persona es: <jsp:getProperty name="personita" property="nombre" /><br />
        El nombre de la persona es: <c:out value="${personita.nombre}" /><br />
        <c:set target="${personita}" property="nombre" >Fernando</c:set>        
        El nombre de la persona es: <c:out value="${personita.nombre}" /><br />
        <c:set target="${personita}" property="nombre" >
            <jsp:getProperty name="personita" property="nombre" />
        </c:set>
        El nombre de la persona es: <c:out value="${personita.nombre}" /><br />
        <c:set target="${personita}" property="nombre" value="Ernesto" />
        El nombre de la persona es: <c:out value="${personita.nombre}" /><br />
        
        
        <br />
        <br />
        <br />
        
        <c:import var="dataString" url="stringConComas.jsp" scope="session" />
        dataString de sesion = ${sessionScope.dataString} <br />
        dataString de sesion = ${sessionScope['dataString']} <br />
        
        <c:import var="dataString" url="stringConComas.jsp" scope="page">
            <c:param name="otroLenguaje" value="Objective-C" />
        </c:import>
        
        dataString de page = ${dataString} <br />
        
        <ul>
        <c:forTokens items="hola,mundo" varStatus="status" delims="," var="lenguage"  >
            <ol>${status.count}. ${lenguaje}</ol>
        </c:forTokens>
        </ul>
        <br />
        <br />
        <%
            ArrayList<String> arr = new ArrayList<String>();
            
            arr.add("Violin");
            arr.add("Guitarron");
            arr.add("Vihuela");
            request.setAttribute("arrayInstrumentos", arr);
        %>
        <ul>
        <c:forEach  items="${arrayInstrumentos}" var="instr" varStatus="status" >
            <ol>${status.count}. ${instr}</ol>
        </c:forEach>
        </ul>
        
        <c:forEach var="i" begin="0" end="10" step="2" >${i} - </c:forEach>
        
        <br />
        <br />
        
        <c:url value="/"></c:url>
        <br />
        <c:url value="www.google.com"></c:url>
        <br />
        ${empty 0}<br />
        ${empty null}<br />
        
        
        <%
            //response.sendRedirect("http://www.google.com");
        %>
        
        <br />
        <c:catch var ="catchException">
           <% int x = 5/0;%>
        </c:catch>

        <c:if test = "${catchException != null}">
           <p>The exception is : ${catchException} <br />
           There is an exception: ${catchException.message}</p>
        </c:if>
         
        <br /><br />
        
        <c:import varReader="miVar" url="http://www.google.com" />
        ${miVar.close()}
        
    </body>
</html>
