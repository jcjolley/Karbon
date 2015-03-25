app = angular.module('karbon', [])
app.controller('karbonCtrl', ['$scope', '$timeout', '$interval',\
              ($scope, $timeout, $interval) ->
  
  $.ajax
    type:"POST"
    url:"GetGameList"
    data: ""
    success: (result) ->
      alert("The result is: " + result)
      $scope.user = JSON.parse(result)
    failure: ->
      alert("It failed")

  $scope.name = "Jolley"
  ]) #End of AngularJS scope
