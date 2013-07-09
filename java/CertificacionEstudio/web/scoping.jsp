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
        <%
            String msg = "Hola mundo";
            application.setAttribute("msg", msg);
            
        %>
        
        
        
        <h1>Scoping en JSP</h1>
        link: http://strutscr.uw.hu/0059.html <br />
        link: http://javapapers.com/jsp/explain-the-scope-of-jsp-objects/
        <table border="1">
            <tr>
                <td>
                    application
                </td>
                <td>
                    Variables placed in this scope persist for the life of an application.
                </td>
            </tr>
            <tr>
                <td>
                    page
                </td>
                <td>
                    Variables placed in this scope persist until the current JSP’s service( ) method completes. Included JSPs cannot see page scope variables from the page including them. Also, this scope is exclusive to JSPs.
                </td>
            </tr>
            <tr>
                <td>
                    request
                </td>
                <td>
                    Variables placed in this scope persist until processing for the current request is completed. This scope differs from page scope because multiple servlets may be executed during the lifespan of a request. Page scope variables persist only for the execution of one servlet.
                </td>
            </tr>
            <tr>
                <td>
                    session
                </td>
                <td>
                    Variables placed in this scope persist until the current user’s session is invalidated or expires. This scope is valid only if the JSP or servlet in question is participating in a session.
                </td>
            </tr>
        </table>
        
        <br />
        Al entrar a esta pagina se establecio la variable msg con scope de application, la cual puede ser accedido
        por cualquiera que entre en el index.jsp
        
        <br /><br /><br />
        <h1>EL y JSTL</h1>
        link: http://www.informit.com/articles/article.aspx?p=30946&seqNum=7
        <br />
        Imprimiendo lo que tiene la variable de aplicacion msg usando EL: ${applicationScope.msg}
        
        <br />
        <br />
        
        <h2>Imprimiendo de varias maneras el parametro 'parametroContexto' que es un un parametro de inicializacion de contexto</h2>
        ${initParam.parametroContexto}
        <br />
        ${initParam['parametroContexto']}
        <br />
        
    </body>
</html>
