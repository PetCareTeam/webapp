/**
 * @ngdoc here we are configuring the module exposed through the FirstApp
 *        variable. The method receives an array that has a function as a last
 *        argument. Here, the angular inject the dependencies defined as strings
 *        in the array to the corresponding elements in the function. <br/> The
 *        $routeProvider is used to configure the routes. It maps templateUrl
 *        and optionally a controller to a given path. This is used by the
 *        ng-view directive. It replaces the content of the defining element
 *        with the content of the templateUrl, and connects it to the controller
 *        through the $scope.
 * @see https://docs.angularjs.org/guide/di
 */
FirstApp

  .config(['$routeProvider',  function($routeProvider) {
  
	$routeProvider.when('/', {
	    templateUrl: 'views/welcome.html',
	    controller: 'WelcomeController'
  });

  $routeProvider.when('/404', {
    templateUrl: '404.html'
  });

  $routeProvider.when('/login', {
	  
	    templateUrl: 'views/login.html',
	    controller: 'LoginController',
	    reloadOnSearch: false
	   
	  });
  $routeProvider.when('/register', {
	  
	    templateUrl: 'views/register.html',
	    controller: 'RegisterController'
	  });
  $routeProvider.otherwise({
    redirectTo: '/'
  });
  
  }])

   
  .run(['$rootScope', function($rootScope){
	  
	  $rootScope.$on("$locationChangeSuccess", function(){
		
	  });
	  
	  $rootScope.$on("$locationChangeStart", function(){
			 
		  });
	  
  }]);
  
