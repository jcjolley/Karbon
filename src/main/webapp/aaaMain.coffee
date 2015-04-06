app = angular.module('karbon', [])
app.controller('karbonCtrl', ['$scope', '$timeout', '$interval',\
              ($scope, $timeout, $interval) ->
  
  Array::remove = (e) -> @[t..t] = [] if (t = @indexOf(e)) > -1

  $.ajax
    type: "POST"
    url: "GetGameList"
    data: ""
    success: (result) ->
      $scope.user = JSON.parse(result)
      $scope.all = true
      initializeGames()
      $scope.getGameList()
      $scope.$apply()
    failure: ->
      alert("It failed")

  sortBy = (a, b) ->
    if (a.count < b.count)
      return 1
    if (a.count > b.count)
      return -1
    aName = a.name.toLowerCase()
    bName = b.name.toLowerCase()
    if (aName < bName)
      return -1
    if (aName > bName)
      return 1
    return 0

  initializeGames = ->
    for game in $scope.user.games
      game.count = 1
      game.flist = []
    for friend in $scope.user.friends
      for game in friend.games
        game.count = 1
        game.flist = []

  addGame = (games, newGame, friend) ->
    found = false
    for game in games
      if game? and newGame? and game.name is newGame.name
        found = true
        game.count++
        if game.flist.indexOf(friend.personaname) is -1
          game.flist.push(friend.personaname)
    if !found and newGame? and newGame.appid != 205790 and newGame.appid != 223530
      newGame.flist.push(friend.personaname)
      games.push newGame
    
    return games

  buyOrPlay = (games) ->
        #games to buy or games to play?
    if $scope.fPlay
      $scope.title = "Games to Play"
      newGameList = []
      for game in games
        for myGame in $scope.user.games
          if game? and myGame? and ((game.name is myGame.name) or (game.appid is myGame.appid))
            newGameList.push(game)
      games = newGameList
    else
      newBuyList = []
      $scope.title="Games to Buy"
      for myGame in $scope.user.games
        for game in games
          if game? and myGame? and ((game.name is myGame.name) or (game.appid is myGame.appid))
            games.remove(game)
            break
    return games

  getGames = ->
    games = []
    for friend in $scope.user.friends
      #are we getting games from all friends, or just one?
      if $scope.all
        for game in friend.games
          #are we only getting recently played games?
          if $scope.recent
            if game? and parseInt(game.playtime_2weeks) > 1
              #add the game if it isn't in the list
              games = addGame(games, game, friend)
          else
            games = addGame(games, game, friend)
      else
        if friend.selected
          for game in friend.games
            #are we only getting recently played games?
            if $scope.recent
              if game? and parseInt(game.playtime_2weeks) > 1
                #add the game if it isn't in the list
                games = addGame(games, game, friend)
            else
              games = addGame(games, game, friend)
    return games

  getRecentGames = (games) ->
    recentGames = []
    for game in games
      if parseInt(game.playtime_2weeks) > 1
        recentGames.push(game)
    return recentGames

  $scope.getGameList = ->
    games = []
    newGames = []
    longGames = []
    initializeGames()
    games = getGames()
    games = buyOrPlay(games)
    
    if $scope.all
      for friend in $scope.user.friends
        friend.selected = false
    
    if $scope.all and !$scope.recent
      for game in games
        newGames.push game
      games = newGames
    games.sort sortBy
    
    if games.length > 25
      for game in games
        if game.flist.length > 2
          longGames.push game
      $scope.gameList = longGames
    else
      $scope.gameList = games
    
  $scope.getIndGameList = ->
    $scope.all = false
    $scope.getGameList()

  $scope.getMyGames = ->
    $scope.gameList = $scope.user.games
]) #End of AngularJS scope

app = angular.module('loading', [])
app.controller('loadingCtrl', ['$scope', '$timeout', '$interval',\
              ($scope, $timeout, $interval) ->
  $scope.loadingMsg = "Gathering Games"
]) #End of AngularJS scope
