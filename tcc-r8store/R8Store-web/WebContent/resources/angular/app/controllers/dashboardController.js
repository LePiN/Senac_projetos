.controller("DashboardController", function($scope, $http, $location){
    $scope.shopId = ($location.search()).shop;

    $scope.stores = [];
    $scope.offers = [];
    $scope.awards = [];

    $http.get("/R8Store/v1/api/store/stores/" + $scope.shopId).then(function(response) {
        $scope.stores = response.data;
    });

    $http.get("/R8Store/v1/api/offer/offers/" + $scope.shopId).then(function(response) {
  	  $scope.offers = response.data;
    });

    $http.get("/R8Store/v1/api/award/awards/" + $scope.shopId).then(function(response) {
  	  $scope.awards = response.data;
    });
})
