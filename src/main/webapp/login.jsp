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
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    </head>
    <body>
        <form action="ForumLogin" method="POST">
            <label> Username: </label>
            <input type="text" name="username"/><br/>
            <label> Password: </label>
            <input type="password" name="password"/><br/>
            <span>Need an account? </span><a href="#" onclick="$('#signup').show()"> Sign up</a><br/>
            <input type="submit" value="Login"/>
        </form>
        
        <p style="color:red">${loginError}<br/>${exceptionMsg}</p>
        <form id ="signup" action="SignUp" method="POST" style="display:none;">
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
