<%-- 
    Document   : results
    Created on : Mar 24, 2015, 8:04:57 PM
    Author     : jolley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="karbon">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/results.css"/>
        <script src="js/results.js"></script>
    </head>
    <body ng-controller="karbonCtrl">
        <h1>Your friends</h1>
        <div class="sidebar">
            <div ng-repeat="friend in user.friends">
                <input type="checkbox" name="selectedFriends[]" value="{{friend.steamId}}" ng-model="friend.selected" ng-click="getGamesToPlay()"/> {{friend.personaname}}
            </div>
        </div>
        <div class="theList">
            <div ng-repeat="game in gameList">
                {{game.name}} : {{game.count}}
            </div>
        </div>
    </body>
</html>
