app.config(['$routeProvider',function($routeProvider) {
	$routeProvider
	.when("/", {
		templateUrl : "views/mainPage.html"
	})
	.when("/login", {
		templateUrl : "views/loginPage.html"
	})
	.otherwise({
		redirectTo : "/"
	});
}]);