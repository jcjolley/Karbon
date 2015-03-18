<%-- 
    Document   : people
    Created on : Mar 17, 2015, 10:31:49 AM
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
        <c:forEach var="person" items="${people}">
            <div>
                <a href="DisplayPerson?personId=${person.id}">
                    <p>
                        ${person.firstname}><br/>
                        ${person.lastname}<br/>
                        ${person.birthday}<br/>

                    </p>
                </a>
            </div><br/>
        </c:forEach>

    </body>
</html>
