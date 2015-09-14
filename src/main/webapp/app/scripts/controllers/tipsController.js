FirstApp.controller('tipsController', [
		'$scope',
		'homeService',
		'$location',
		'$rootScope',
		'postService',
		'LoginService',
		function($scope, homeService, $location, $rootScope, postService,
				LoginService) {

		
			$scope.user;
			$scope.logout = function() {

				LoginService.logout().then(

				function success(response) {

					console.log('user is logged out: ');
					$location.path("/");

				}, function failure(error) {

					console.log('use is not logged out successfully');

				});

			};

			$scope.openProfile = function() {
				$location.path("/profile");
			}

			$scope.getUser = function() {
				homeService.getUser().then(

				function success(response) {

					console.log('loged in user is: ', response.username);
					$scope.user = response;
				}, function failure(error) {

					console.log('error USER NOT FOUND: ', error);
				});

			};
			
			$scope.home = function(){
				$location.path("/home");
			}
			
			
			 $scope.myInterval = 5000;
			  $scope.noWrapSlides = false;
			  var slides = $scope.slides = [];
			  $scope.addSlide = function() {
			    var newWidth = 600 + slides.length + 1;
			    slides.push({
			      image: '//placekitten.com/' + newWidth + '/300'
			    });
			  };
			  for (var i=0; i<4; i++) {
			    $scope.addSlide();
			  }
			  
			
			  
			  $scope.tips = function(){
				  
				  event.preventDefault();
			  }
		} ]);
