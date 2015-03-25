app = angular.module('karbon', [])
app.controller('karbonCtrl', ['$scope', '$timeout', '$interval',\
              ($scope, $timeout, $interval) ->
  
  $.getJSON "GetGameList", (data) ->
    $scope.user = data

  $scope.name = "Jolley"
  ]) #End of AngularJS scope
