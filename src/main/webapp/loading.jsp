<%-- 
    Document   : loading
    Created on : Mar 17, 2015, 3:44:34 PM
    Author     : jolley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/karbon.css" rel="stylesheet" type="text/css"/>
        <link href='http://fonts.googleapis.com/css?family=Revalia' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Loading</title>
    </head>
    <body onload="window.location.replace('steamAPIConnector')">
        <header>
            <h1>Karbon</h1>
            <span>What will you play tonight?</span>
            <hr/>
        </header>
        <div style="text-align: center">
            <br />
            <br />
            <br />
            <br />
            <h1> Loading Content</h1>
            <br />
            <img src="Loading.gif" alt="Loading..."/>
            <br />

            <br />
            <footer>
                <img src="Powered_by_steam.jpg" alt="Powered by Steam"/>
            </footer>
        </div>
    </body>
</html>
