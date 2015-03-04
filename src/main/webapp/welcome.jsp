<%-- 
    Document   : welcome
    Created on : Mar 3, 2015, 10:44:00 AM
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
        <form action="logout" method="POST">
            <p>Welcome ${username}</p>
            <input type="submit" value="Log Out"/>
        </form>
    </body>
</html>
