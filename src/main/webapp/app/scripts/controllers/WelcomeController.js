FirstApp.controller('WelcomeController', ['$scope', '$location','$route',
    function($scope, $location,$route) {
    
	$scope.redirectLogin = function(){

		$location.path('/login');
		
		//$route.reload();
	}
	
}]);
