angular.module("App", ['geolocation'], function($locationProvider) {
	$locationProvider.html5Mode({
		  enabled: true,
		  requireBase: false
	});
})
