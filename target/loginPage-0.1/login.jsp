<%-- 
    Document   : login
    Created on : Mar 3, 2015, 10:32:49 AM
    Author     : jolley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="validateLogin" method="POST">
            <label> Username: </label>
            <input type="text" name="username"/>
            <label> Password: </label>
            <input type="password" name="password"/>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>
