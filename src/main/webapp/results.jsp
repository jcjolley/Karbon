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
        <link href='http://fonts.googleapis.com/css?family=Revalia' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/results.css"/>
        <script src="js/results.js"></script>
    </head>
    <body ng-controller="karbonCtrl">
        <header>
            <h1 id="headerTitle">Karbon</h1>
            <span>What will you play tonight?</span>
            <hr/>
        </header>
        <div class="master">
            <h1 class="title">{{title}}</h1>
            <div class="sidebar">
                <div>
                    <input type="radio" ng-model="fPlay" ng-value="true" ng-click="getGameList()"/>
                    Games to play<br/>
                    <input type="radio" ng-model="fPlay" ng-value="false"ng-checked="true" ng-click="getGameList()"/>
                    Games to buy
                    <br/>
                    <input type="checkbox" ng-model="recent" ng-click="getGameList()"/> Only show recently played games<br/>
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
                            <p>
                                <ng-pluralize count="game.count"
                                when="{'1': 'You only have one friend with this game.',
                                'other': 'You have {{game.count}} friends with this game.'}"
                                </ng-pluralize><br/>
                                <ul class="flist">
                                    <li ng-repeat="f in game.flist">{{f}}</li>
                                </ul>
                            </p>
                        </div>
                    </a>
                </div>
            </div>
        </div>
        <footer>
            <img src="Powered_by_steam.png" alt="Powered by Steam"/>
        </footer>
    </body>
</html>
