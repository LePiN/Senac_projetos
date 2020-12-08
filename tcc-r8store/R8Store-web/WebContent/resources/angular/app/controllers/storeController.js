.controller("StoreController", function($scope, $http, $location){
  $scope.storeId = ($location.search()).id;
  $scope.offers = [];
  $scope.awards = [];

  $http.get("/R8Store/v1/api/offer/offers/store/" + $scope.storeId).then(function(response) {
      $scope.offers = response.data;
  });

  $http.get("/R8Store/v1/api/award/awards/store/" + $scope.storeId).then(function(response) {
      $scope.awards = response.data;
  });

})
