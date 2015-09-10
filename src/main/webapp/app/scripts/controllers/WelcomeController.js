FirstApp.controller('WelcomeController', ['$scope', '$location','$route',
    function($scope, $location,$route) {
    
	$scope.comment = {};
	
	$scope.redirectLogin = function(){

		$location.path('/login');
	};
	
	$scope.redirectRegister = function(){

		$location.path('/register');
	}
	
}]);
