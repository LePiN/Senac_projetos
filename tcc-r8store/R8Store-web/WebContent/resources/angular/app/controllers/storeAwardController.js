.controller("StoreAwardController", function($scope, $http, $location, $window){
  $scope.awardId = ($location.search()).id;
  $scope.awards = [];
  $scope.responseItem = {};
  $scope.resultClass = 'modal-success';

  $scope.canAdquire = true;
  $scope.showModal = false;
  $scope.resultModal = false;

  $http.get("/R8Store/v1/api/award/related/" + $scope.awardId).then(function(response) {
      $scope.awards = response.data;
  });

  $scope.adquireAward = function(id) {
    $http.get("/R8Store/v1/api/award/adquire?id=" + id).then(function(response) {
      $scope.showModal = false;
      $scope.resultModal = true;

      $scope.responseItem.code = response.data.code;
      $scope.responseItem.title = response.data.title;
      $scope.responseItem.message = response.data.message;
      $scope.responseItem.points = response.data.points;
      $scope.resultClass = 'modal-success';
      $scope.canAdquire = false;
    })
    .catch(function (error) {
      $scope.showModal = false;
      $scope.resultModal = true;

      $scope.responseItem.code = error.data.code;
      $scope.responseItem.title = error.data.title;
      $scope.responseItem.message = error.data.message;
      $scope.resultClass = 'modal-error';
    });

	}

  //MODAL
  $scope.openModal = function() {
      $scope.showModal = true;
  }
  $scope.closeModal = function() {
      $scope.showModal = false;
  }
  $scope.closeResult = function() {
      $scope.resultModal = false;
  }
  //FIM MODAL

})
