<%-- 
    Document   : index
    Created on : 26-jun-2021, 22:48:33
    Author     : Jose Manuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
    </head>
    <body>
        <%
        
            System.out.println(request.getRequestURI() + request.getSession().getId());
        %>
        <h1>Inicio</h1>
        <form action="/TFM/verificar" method="POST" autocomplete="off">
            <p>
                Usuario:
                <input id="usuario" name="usuario" type="text"/>
            </p>
            <p>
                Contraseña:
                <input id="contr" name="contr" type="text"/>
            </p>
            <button id="confirmar" name="confirmar" type="submit">Confirmar</button>
        </form>
        
    </body>
</html>
