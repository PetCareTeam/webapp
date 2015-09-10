FirstApp.controller('WelcomeController', ['$scope', '$location','$route','anchorSmoothScroll',
    function($scope, $location,$route,anchorSmoothScroll) {
    
	 $scope.gotoElement = function (eID){
	      // set the location.hash to the id of
	      // the element you wish to scroll to.
	      $location.hash('services');
	 
	      // call $anchorScroll()
	      anchorSmoothScroll.scrollTo(eID);
	      
	    };
	$scope.comment = {};
	
	$scope.redirectLogin = function(){

		$location.path('/login');
	};
	
	$scope.redirectRegister = function(){

		$location.path('/register');
	}
	
}]);
