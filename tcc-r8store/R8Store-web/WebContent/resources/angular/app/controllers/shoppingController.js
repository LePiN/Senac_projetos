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
