<%--
Document : apiTest
Created on : Mar 3, 2015, 8:37:07 PM
Author : jolley
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link href="css/karbon.css" rel="stylesheet" type="text/css"/>
<link href='http://fonts.googleapis.com/css?family=Revalia' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Karbon</title>
</head>
<body>
<header>
<h1>Karbon</h1>
<span>What will you play tonight?</span>
<hr/>
</header>
<div class="mainContent">
<c:if test="${user == null || user.steamId == null }">
<a href="SteamOpenIDServlet">
<img src="http://steamcommunity-a.akamaihd.net/public/images/signinthroughsteam/sits_large_border.png"/>
</a>
</c:if>
<c:if test="${user != null}">
<div class="userList">
<img src="${user.avatar}" class="userAvatar"/>
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
<footer>
<a href="http://steampowered.com">Powered by Steam</a>
</footer>
</body>
</html>