.controller("HistoryAwardController", function($scope, $http, $location, $window){

  $scope.awards = [];

  $http.get("/R8Store/v1/api/award/history").then(function(response) {
      $scope.awards = response.data;
  });

})
