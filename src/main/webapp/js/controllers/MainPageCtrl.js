app.controller('MainPageCtrl', ['$scope', '$location', function($scope, $location){
	$scope.redirect = function () {
		$location.url('/login')
	}
}])