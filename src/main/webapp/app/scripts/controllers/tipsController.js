FirstApp.controller('tipsController', [
		'$scope',
		'homeService',
		'$location',
		'$rootScope',
		'postService',
		'LoginService',
		function($scope, homeService, $location, $rootScope, postService,
				LoginService) {

			$scope.current = 1;
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
			document.getElementById('1').onclick = function() {
				 document.getElementById($scope.current).className = "";
				 document.getElementById(1).className = "active";
				 
				 document.getElementById('t1').className += 'active';
				 document.getElementById('t' + $scope.current).className = 'item';
				 $scope.current = 1;
				
//			    alert(this.id);
			};
			
			document.getElementById('2').onclick = function() {
				 document.getElementById($scope.current).className = "";
				 document.getElementById(2).className = "active";
				 
				 document.getElementById('t2').className += 'active';
				 document.getElementById('t' + $scope.current).className = 'item';
				 $scope.current = 2;
//			    alert(this.id);
			};

			document.getElementById('3').onclick = function() {
				 document.getElementById($scope.current).className = "";
				 document.getElementById(3).className = "active";
				 
				 document.getElementById('t3').className += 'active';
				 document.getElementById('t' + $scope.current).className = 'item';
				 $scope.current = 3;
//			    alert(this.id);
			};
			
			document.getElementById('4').onclick = function() {
				 document.getElementById($scope.current).className = "";
				 document.getElementById(4).className = "active";
				 
				 document.getElementById('t4').className += 'active';
				 document.getElementById('t' + $scope.current).className = 'item';
				 $scope.current = 4;
//			    alert(this.id);
			};
		} ]);
