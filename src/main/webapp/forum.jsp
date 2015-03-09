<%-- 
    Document   : forum
    Created on : Mar 7, 2015, 5:32:09 PM
    Author     : jolley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script src="/js/all.js" type="text/javascript" charset="utf-8" async  defer></script>
        <title>JSP Page</title>
    </head>
    <body>
        <form class="topbar" action="logout" method="POST">
            <h1 class="title"> Discussion Forum</h1>
            <p>Welcome ${username}</p>
            <input type="submit" value="Log Out"/>
        </form>
            
        <div class ="master">
        <form class="newPost" action="AddPost" method="POST">
            <h2> Write a new post </h2>
            <input type="text" name="newPost" placeholder="Add a new post"/><br/>
            <input type="submit" value="Add a post"/> 
        </form>
            
        <c:forEach items="${posts}" var="post">
            <form class="aPost" action="AddComment" method="POST">
                <div class="postHeader"><h3> ${post.author}:</h3>
                    <div class="postTime">${post.timePosted.getDayOfWeek()}, 
                    ${post.timePosted.getHour() % 12}:<fmt:formatNumber value="${post.timePosted.getMinute()}" minIntegerDigits="2"/>
                    <c:if test="${post.timePosted.getHour() >= 12}">
                        PM
                    </c:if>
                    <c:if test="${post.timePosted.getHour() < 12}">
                        AM
                    </c:if>
                    </div>
                </div>
                <div class="mainPost">
                    <div class="postContent">${post.content} </div>
                </div>
                <input id = "postId" style = "display:none" type = "text" name="postId" value = "${post.id}"/>
                <div class="commentBox">
                    <c:forEach items="${post.comments}" var="comment">
                        <div class="aComment">
                            <div class="commentTime"> ${comment.timePosted}</div>
                            <div class="commentContent">${comment.content}</div>
                            <div class="commentAuthor">~${comment.author}</div>
                        </div>
                    </c:forEach>
                    <div class="addComment">
                        <input type="text" name="newComment" placeholder="Add a comment"/>
                        <input type="submit" value="Add Comment"/>
                    </div>
                </div>
            </form>
        </c:forEach>
        </div>
    </body>
</html>
