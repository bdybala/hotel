app.config(['$routeProvider',function($routeProvider) {

	$routeProvider
	.when("/", {
		templateUrl : "views/mainPage.html",
		controller : 'MainPageCtrl'
	})
	.when("/login", {
		templateUrl : "views/loginPage.html",
		controller : 'LoginPageCtrl'
	})
	.when("/client", {
		templateUrl : "views/client/clientPage.html"
	})
	.otherwise({
		redirectTo : "/"
	});

}]);