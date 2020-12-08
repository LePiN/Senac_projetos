angular.module("App", ['geolocation'], function($locationProvider) {
	$locationProvider.html5Mode({
		  enabled: true,
		  requireBase: false
	});
})

.directive('ngEnterKey', function() {
    return function(scope, element, attrs) {

        element.bind("keydown keypress", function(event) {
            var keyCode = event.which || event.keyCode;

            if (keyCode === 13) {
                scope.$apply(function() {
                    scope.$eval(attrs.ngEnterKey);
                });

                event.preventDefault();
            }
        });
    };
})

.controller("AwardController", function($scope, $http, $location){

  $scope.token = ($location.search()).success;
  $scope.showModal = false;
  $scope.rating = {};

  if ($scope.token) {
      $scope.showModal = true;
  }

  $scope.closeResult = function() {
      $scope.showModal = false;
  }

  $http.get("/R8Store/v1/api/award/awards").then(function(response) {
	  $scope.myData = response.data;
  });

  $scope.adquireAward = function(id) {
    $http.get("/R8Store/v1/api/award/adquire?id=" + id).then(function(response) {
    	$scope.myData = response.data;
    });
	}

  $scope.enableCamera = function() {
      console.log('aqui');
      let scanner = new Instascan.Scanner({ video: document.getElementById('preview') });
      scanner.addListener('scan', function (content) {
        console.log(content);
        $.ajax({
          url: "/R8Store/v1/api/rate?token=" + content,
          success: function(result){
              window.location.href = '/R8Store/user/rating/avaliarLoja.xhtml?token=' + content;
          },
          error: function(result){
            PF('idMessage').renderMessage({
              "summary":"Ops",
              "detail":"Token indisponível",
              "severity":"warn"
            });
          }
        });
      });
      Instascan.Camera.getCameras().then(function (cameras) {
        if (cameras.length > 0) {
          scanner.start(cameras[cameras.length - 1]);
        } else {
          console.error('No cameras found.');
        }
      }).catch(function (e) {
        console.error(e);
      });
  }
})

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

.controller("HistoryAwardController", function($scope, $http, $location, $window){

  $scope.awards = [];

  $http.get("/R8Store/v1/api/award/history").then(function(response) {
      $scope.awards = response.data;
  });

})

.controller("IndexController", function($scope){
  $scope.titulo = "Teste";
})

.controller("ProductController", function($scope, $http, $location){
    $scope.productId = ($location.search()).id;

    $scope.products = [];

    $http.get("/R8Store/v1/api/product/products/" + $scope.productId).then(function(response) {
        $scope.products = response.data;
    });

})

.controller("ShoppingController", function($scope, $http, geolocation){
  $scope.markers = [];
  $scope.shopName = "";

  geolocation.getLocation().then(function(data){
      $scope.coords = {lat:data.coords.latitude, long:data.coords.longitude};
  });

	var options = {
			center: new google.maps.LatLng(-27.5875449, -48.5778756),
			zoom: 12
	}

	$scope.map = new google.maps.Map(
			document.getElementById("map"), options
	);

  $http.get("/R8Store/v1/api/store/shoppings").then(function(response) {
      $scope.myData = response.data;
			angular.forEach(response.data, function (item) {
          createMarker(item);
      });
  });

	var createMarker = function (info) {
    var iconImage = {
            path: 'M185,0L185,0C82.7,0,0,82.7,0,185c0,35.7,11.4,69.8,27.3,97.8l120.6,208.7c7.6,13.6,22.8,20.5,37.2,20.5 c14.4,0,28.9-6.8,37.2-20.5L342.8,283c16-28,27.3-61.5,27.3-97.8C370.1,82.8,287.4,0,185,0z M185,239.7 c-37.2,0-67.5-30.4-67.5-67.5s30.4-67.5,67.5-67.5s67.5,30.4,67.5,67.5C252.6,209.5,222.2,239.7,185,239.7z',
            fillColor: "#126CA3",
            fillOpacity: 1,
            scale: 0.07,
            strokeColor: '#126CA3',
            anchor: new google.maps.Point(185, 500) // seta a posicao corretamente do icone no mapa
          }
      var marker = new google.maps.Marker({
          map: $scope.map,
          position: new google.maps.LatLng(info.coordinateX, info.coordinateY),
					icon: iconImage
      });

			$scope.markers[info.id] = marker;
  }

	$scope.mouseOver = function(id) {
    var iconImage = {
			      path: 'M185,0L185,0C82.7,0,0,82.7,0,185c0,35.7,11.4,69.8,27.3,97.8l120.6,208.7c7.6,13.6,22.8,20.5,37.2,20.5 c14.4,0,28.9-6.8,37.2-20.5L342.8,283c16-28,27.3-61.5,27.3-97.8C370.1,82.8,287.4,0,185,0z M185,239.7 c-37.2,0-67.5-30.4-67.5-67.5s30.4-67.5,67.5-67.5s67.5,30.4,67.5,67.5C252.6,209.5,222.2,239.7,185,239.7z',
			      fillColor: "#FFBC00",
			      fillOpacity: 1,
			      scale: 0.07,
			      strokeColor: '#FFBC00',
			      anchor: new google.maps.Point(185, 500) // seta a posicao corretamente do icone no mapa
			    }
		$scope.markers[id].setIcon(iconImage);
	}

	$scope.mouseOut = function(id) {
    var iconImage = {
			      path: 'M185,0L185,0C82.7,0,0,82.7,0,185c0,35.7,11.4,69.8,27.3,97.8l120.6,208.7c7.6,13.6,22.8,20.5,37.2,20.5 c14.4,0,28.9-6.8,37.2-20.5L342.8,283c16-28,27.3-61.5,27.3-97.8C370.1,82.8,287.4,0,185,0z M185,239.7 c-37.2,0-67.5-30.4-67.5-67.5s30.4-67.5,67.5-67.5s67.5,30.4,67.5,67.5C252.6,209.5,222.2,239.7,185,239.7z',
			      fillColor: "#126CA3",
			      fillOpacity: 1,
			      scale: 0.07,
			      strokeColor: '#126CA3',
			      anchor: new google.maps.Point(185, 500) // seta a posicao corretamente do icone no mapa
			    }
		$scope.markers[id].setIcon(iconImage);
	}

  $scope.findShop = function() {
    $scope.clearMarkers();
    $scope.markers = [];
    $http.get("/R8Store/v1/api/store/shoppings?name=" + $scope.shopName).then(function(response) {
        $scope.myData = response.data;
  			angular.forEach(response.data, function (item) {
            createMarker(item);
        });
    });
	}

  $scope.findByCoord = function() {
    if ($scope.coords) {
      $scope.clearMarkers();
      $scope.markers = [];
      $http.get("/R8Store/v1/api/store/shoppings?lat=" + $scope.coords.lat + "&long=" + $scope.coords.long).then(function(response) {
        $scope.myData = response.data;
        angular.forEach(response.data, function (item) {
          createMarker(item);
        });
      });
    } else {
      alert('É necessário habilitar sua localização');
      geolocation.getLocation().then(function(data){
          $scope.coords = {lat:data.coords.latitude, long:data.coords.longitude};
      });
    }
  }

  $scope.findByRate = function() {
    $scope.clearMarkers();
    $scope.markers = [];
    $http.get("/R8Store/v1/api/store/shoppings?rating=true").then(function(response) {
        $scope.myData = response.data;
        angular.forEach(response.data, function (item) {
          createMarker(item);
        });
    });
  }

  $scope.clearMarkers = function() {
  	angular.forEach($scope.markers, function (m) {
        m.setMap(null);
    });
  }

})

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
