<%-- 
    Document   : apiTest
    Created on : Mar 3, 2015, 8:37:07 PM
    Author     : jolley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background:black; color:lightgrey; padding:100px;">
        <form action="steamAPIConnector" method="POST">
            <label>Input your steam64 key here: </label>
            <input type="text" name="steamAPIKey"/>
            <br/>
            <input type="submit" value="Check API key"/>
            <br/>
            <p> Try 76561197976892493 </p>
            <p>${steamAPIResults}</p>     
            
        </form>
    </body>
</html>
