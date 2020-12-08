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
