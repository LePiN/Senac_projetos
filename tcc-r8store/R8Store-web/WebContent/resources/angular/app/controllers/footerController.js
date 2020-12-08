.controller("FooterController", function($scope, $http, $location, geolocation){
    $scope.showCheckIn = false;
    $scope.showModal = false;
    $scope.shopping = {};
    $scope.offers = [];
    $scope.resultClass = 'modal-success';
    $scope.resultModal = false;

    geolocation.getLocation().then(function(data){
        $scope.coords = {lat:data.coords.latitude, long:data.coords.longitude};

        $http.get("/R8Store/v1/api/store/checkin?lat=" + $scope.coords.lat + "&lng=" + $scope.coords.long).then(function(response) {
            if (response.data) {
                $scope.shopping = response.data;
                $scope.showCheckIn = true;
                $http.get("/R8Store/v1/api/offer/offers/" + $scope.shopping.id).then(function(response) {
              	   $scope.offers = response.data;
                });
            }
        });
    });

    $scope.confirmCheckIn = function() {
        $http.get("/R8Store/v1/api/store/checkin/confirm/" + $scope.shopping.id).then(function(response) {
            $scope.showModal = false;
            $scope.resultModal = true;

            $scope.responseData = response.data;
            $scope.resultClass = 'modal-success';
            $scope.showCheckIn = false;
        })
        .catch(function (error) {
          $scope.showModal = false;
          $scope.resultModal = true;

          $scope.responseData = error.data
          $scope.resultClass = 'modal-error';
        });
    }

    $scope.openModal = function() {
        $scope.showModal = true;
    }

    $scope.closeModal = function() {
        $scope.showModal = false;
    }

    $scope.closeResult = function() {
        $scope.resultModal = false;
    }

})
