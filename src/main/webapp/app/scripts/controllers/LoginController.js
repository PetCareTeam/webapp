FirstApp.controller('LoginController', ['$scope', 'LoginService', 
    function($scope, LoginService) {
	
	$scope.mydata= [];

	$scope.login = function(){
		
		$scope.mydata = LoginService.login($scope.username, $scope.password);
	}
	
	
}]);
