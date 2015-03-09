<%-- 
    Document   : login
    Created on : Mar 3, 2015, 10:32:49 AM
    Author     : jolley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script src="/js/all.js" type="text/javascript" charset="utf-8" async  defer></script>
    </head>
    <body>
        <h1> Log in to join the discussion </h1>
        <form class="loginBox" action="ForumLogin" method="POST">
            <label> Username: </label>
            <input type="text" name="username"/><br/>
            <label> Password: </label>
            <input type="password" name="password"/><br/>
            <span>Need an account? </span><a href="#" onclick="$('#signup').fadeIn()"> Sign up</a><br/>
            <input type="submit" value="Login"/>
        </form>
        
        <p style="color:red">${loginError}<br/>${exceptionMsg}</p>
        <form class="loginBox" id ="signup" action="SignUp" method="POST" style="display:none;">
            <label> Username: </label>
            <input type="text" name="username"/><br/>
            <label> Password: </label>
            <input type="password" name="password"/><br/>
            <label> Retype password: </label>
            <input type="password" name="password2"/><br/>
            
            <input type="submit" value="Sign up"/>
        </form>
    </body>
</html>
