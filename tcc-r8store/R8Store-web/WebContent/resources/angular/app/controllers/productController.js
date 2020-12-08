.controller("ProductController", function($scope, $http, $location){
    $scope.productId = ($location.search()).id;

    $scope.products = [];

    $http.get("/R8Store/v1/api/product/products/" + $scope.productId).then(function(response) {
        $scope.products = response.data;
    });

})
