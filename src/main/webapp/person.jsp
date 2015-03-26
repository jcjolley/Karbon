<%-- 
    Document   : person
    Created on : Mar 17, 2015, 10:51:49 AM
    Author     : jolley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Person Info</title>
    </head>
    <body>
        <h1>Person Info</h1>
        <p>
            ${person.firstname} <br/>
            ${person.lastname} <br/>
            ${person.birthday} <br/>
            ${person.id} <br/>
        </p>
    </body>
</html>
