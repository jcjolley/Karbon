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
        <title>Karbon</title>
        <style>
            .rightside {
                float: right;
            }
        </style>
    </head>
    <body style="background:black; color:lightgrey; padding:0px; margin:0px;
          font-family: 'Open Sans', sans-serif;">
        <header style="width:100%">
            <h1 style="padding:0; margin:0; margin-left: 20px; font-family: 'Revalia', cursive;
                font-size:250%;">Karbon</h1>
            <span style="margin-left:20px; font-style:italic;">What will you play tonight?</span>
            <hr/>
        </header>
        <div style="padding:20px 100px;">

            <c:if test="${user == null || user.steamId == null }">
                <a href="SteamOpenIDServlet">
                    <img src="http://steamcommunity-a.akamaihd.net/public/images/signinthroughsteam/sits_large_border.png"/>
                </a>
            </c:if>
            <c:if test="${user != null}">

                <div style="border: 1px solid lightgrey; padding:20px; width:400px;">
                    <img src="${user.avatar}" style="width:100px; float:left; margin-right: 20px;"/>
                    <h1> ${user.personaname}</h1>
                    <span>${user.profileURL}</span>
                </div>

                <form class="rightside" action="GetGameList" method="POST">
                    <input type="submit" value="Check these friends"/><br/>
                    <input type="checkbox" value="1" name="recent">
                    <label>Only if recently played</label><br/>
                    <input type="radio" name="buyOrPlay" value="buy"><label>buy</label>
                    <input type="radio" name="buyOrPlay" value="play"><label>play</label><br/><br/>
                    
                    <c:forEach var="friend" items="${user.friends}">
                        <input type="checkbox" value="${friend.steamId}" name="friendId"/>
                        <label>${friend.personaname}</label><br/>
                    </c:forEach>
                               
                </form>
                <c:if test="${gamesList != null}">
                    <c:forEach var="game" items="${gamesList}">
                        <p>${game.name} : ${game.instances}</p>    
                    </c:forEach>
                </c:if>
            </c:if>
        </div>
        <footer style="position:fixed; width:100%; height:50px; bottom:0; text-align:center;">
            <a href="http://steampowered.com">Powered by Steam</a>
        </footer>
    </body>
</html>
