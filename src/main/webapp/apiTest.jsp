<%-- 
    Document   : apiTest
    Created on : Mar 3, 2015, 8:37:07 PM
    Author     : jolley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <link href='http://fonts.googleapis.com/css?family=Revalia' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background:black; color:lightgrey; padding:0px; margin:0px;
          font-family: 'Open Sans', sans-serif;">
        <header style="width:100%">
            <h1 style="padding:0; margin:0; margin-left: 20px; font-family: 'Revalia', cursive;
                font-size:250%;">Karbon</h1>
            <span style="margin-left:20px; font-style:italic;">What will you play tonight?</span>
            <hr/>
        </header>
        <form action="steamAPIConnector" method="POST" style="padding:20px 100px;">
            <label style="font-weight: bold">Input your steam64 key here: </label>
            <input type="text" name="steamAPIKey" style="margin-left:20px; width:175px"/>
            <br/>
            <input type="submit" value="Check API key" style="float:right; margin-top:20px"/>
            <br/>
            <p> Try 76561197976892493 </p>
            <p>Results will appear below</p>
            <div style="border: 1px solid lightgrey; padding:20px; width:400px;">
                <img src="${avatar}" style="width:100px; float:left; margin-right: 20px;"/>
                <h1> ${playerName}</h1>
                <span>${profileURL}</span>

            </div>
            <c:if test="${games != null}">
                <h2> Your games:</h2>
                    <c:forEach var="game" items="${games}">
                        <div>    
                            <img style="float:left; margin-right:20px" src="${game.img_logo_url}"/>
                            <h3 style="margin-left:20px;">${game.name}</h2>
                            
                        </div><br/>
                    </c:forEach>-->
                </c:if>
        </form>
    </body>
</html>
