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
        <title>Karbon</title>
        <link rel="stylesheet" href="css/results.css"/>
        <script src="js/results.js"></script>
    </head>
    <body ng-controller="karbonCtrl">
        <div class="master">
            <h1 class="title">{{title}}</h1>
            <div class="sidebar">
                <div>
                    <input type="radio" ng-model="fPlay" ng-value="true" ng-click="getGameList()"/>
                    Games to play
                    <input type="radio" ng-model="fPlay" ng-value="false"ng-checked="true" ng-click="getGameList()"/>
                    Games to buy
                    <br/>
                    <input type="checkbox" ng-model="recent" ng-click="getGameList()"/> Only show recently played games
                </div><br/>
                
                <div>
                    <input type="checkbox" ng-model="all" ng-click="getGameList()" ng-checked="all"/> All friends
                </div><br/>
                <div ng-repeat="friend in user.friends">
                    <input type="checkbox" name="selectedFriends[]" value="{{friend.steamId}}" ng-model="friend.selected" ng-click="getIndGameList()"/>
                    {{friend.personaname}}
                </div>
            </div>
            <div class="theList">
                <div ng-repeat="game in gameList">
                    <a href="http://store.steampowered.com/app/{{game.appid}}/">
                        <div class="game">
                            <img src="{{game.img_logo_url}}"/>
                            <p> {{game.name}} Friends with this game: {{game.count}}
                                <ul class="flist">
                                    <li ng-repeat="f in game.flist">{{f}}</li>
                                </ul>
                            </p>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </body>
</html>
