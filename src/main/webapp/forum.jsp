<%-- 
    Document   : forum
    Created on : Mar 7, 2015, 5:32:09 PM
    Author     : jolley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body{
                margin: 0px;
                padding: 0px;
            }

            .aPost, .newPost{
                width: 100%;
                padding: 40px 10px;
                border-top: 1px solid black;
                border-bottom: 1px solid black;
            }

            .newPost input[type="text"]{
                width: 70%;
            }

            .addComment {
                margin-left:20px;

            }
            .addComment input[type="text"]{
                width:70%;
            }

            .topbar * {
                float:right;
                color:white;
                background: black;
                margin:10px
            }
            .topbar {
                background: black;
                height:40px;
                width:100%;
            }

        </style>
        <title>JSP Page</title>
    </head>
    <body>
        <form class="topbar" action="logout" method="POST">
            <p>Welcome ${username}</p>
            <input type="submit" value="Log Out"/>
        </form>
            
        <form class="newPost" action="AddPost" method="POST">
            <h2> Write a new post </h2>
            <input type="text" name="newPost" placeholder="Add a new post"/><br/>
            <input type="submit" value="Add a post"/> 
        </form>
            
        <c:forEach items="${posts}" var="post">
            <form class="aPost" action="AddComment" method="POST">
                <p><span style="color:lightgrey;">${post.timePosted}</span>
                    ${post.content} <span style="color:blue;">~${post.author}
                </p>
                <input id = "postId" style = "display:none" type = "text" name="postId" value = "${post.id}"/>
                <c:forEach items="${post.comments}" var="comment">
                    <p class="aComment" style="margin-left:20px; background:lightgrey;">
                        <span style="color:darkgrey;">${comment.timePosted}</span>
                        ${comment.content} <span style="color:blue;"> ~${comment.author}</span>
                    </p>
                </c:forEach>
                <div class="addComment">
                    <input type="text" name="newComment" placeholder="Add a comment"/>
                    <input type="submit" value="Add Comment"/>
                </div>
            </form>
        </c:forEach>
    </body>
</html>
