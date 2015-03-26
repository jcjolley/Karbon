<%-- 
    Document   : movies
    Created on : Mar 10, 2015, 10:35:17 AM
    Author     : jolley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="SearchMovies" method="GET">
            <label>Search for a movie:</label>
            <input type="text" name="search"/><br/>
            <input type="submit" value="search"/>
        </form>
        <c:if test="${list != null}">
            <c:forEach var="map" items="${list}">
                    <h2>${map.get("Title")}</h2>
                    <p><a href="SearchMovie?searchMovie=${map.get("imdbID")}">More Info for ${map.get("imdbID")}</a>
            </c:forEach>
        </c:if>
    </body>
</html>
