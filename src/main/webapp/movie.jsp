<%-- 
    Document   : movie
    Created on : Mar 10, 2015, 11:06:28 AM
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
        <h1>${movieMap.get("Title")}</h1><br/>
        <img src="${movieMap.get("Poster")}"/>
</body>
</html>
