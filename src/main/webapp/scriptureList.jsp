<%-- 
    Document   : scriptureList
    Created on : Mar 5, 2015, 10:09:41 AM
    Author     : jolley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Favorite Scriptures</h1>
        <div>
            <ul>
            <c:forEach items="${scriptures}" var="scripture">
                <li> <strong>${scripture.book}</strong> ${scripture.chapter}:${scripture.verse}</li>
            </c:forEach>
            </ul>
        </div>
    </body>
</html>
