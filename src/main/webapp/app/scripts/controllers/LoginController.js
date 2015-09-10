FirstApp.controller('LoginController', ['$scope','LoginService', '$location', '$rootScope',
                                         function($scope, LoginService, $location, $rootScope) {
	
	
	$scope.alert = false;
	$scope.success = false;
	$scope.login = function() {	
		
		var user = {
				username : $scope.username,
				password : $scope.password
		};
		
		LoginService.login(user).then(
				
				function success(data){
					
					
					console.log('success: ', data);
					$scope.alert = false;
					$scope.success = true;
					setTimeout(function(){ 
						
					}, 10000);
					$location.url('/home');
				},
				function error(err){
					$scope.alert = true;
					$scope.username = '';
					$scope.password = '';
					console.log('error: ', err);
				});
		
		
	};
	

	
$scope.logout = function() {	
		
	
		
		LoginService.logout().then(
				
				function success(data){
					$locatin.url('/');
				},
				function error(err){
					
				});
		
		
	};
	
	
	
}]);
