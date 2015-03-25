app = angular.module('karbon', [])
app.controller('karbonCtrl', ['$scope', '$timeout', '$interval',\
              ($scope, $timeout, $interval) ->
  
  $.ajax
    type:"POST"
    url:"GetGameList"
    data: ""
    success: (result) ->
      $scope.user = JSON.parse(result)
      $scope.$apply()
    failure: ->
      alert("It failed")

  $scope.name = "Jolley"

  $scope.getGamesToPlay = ->
    gamesToPlay = []
    for friend in $scope.user.friends
      if friend.selected
        for game in friend.games
          i = gamesToPlay.indexOf(game)
          if i == -1
            gamesToPlay.push(game)
          else
            gamesToPlay[i].count = gamesToPlay[i].count + 1
    $scope.gameList = gamesToPlay


  ]) #End of AngularJS scope
