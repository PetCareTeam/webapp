FirstApp.controller('ProfileController', ['$scope','profileService', '$resource','$location', '$rootScope', 'homeService', 'LoginService',
                                         function($scope, profileService, $resource,$location, $rootScope,homeService, LoginService) {
	
	
	$scope.comments = [];
	$scope.user;
	$scope.pictureNo;
	$scope.locationNo;
	$scope.commentsNo;
	$scope.loadComments = function(id) {	
		
		console.log("kontrolerot e povikan");
		profileService.loadComments(id).then(
				
				function success(response){
					
					console.log('loaded comments: ', response.comments);
					console.log('user id e: ', id);
					$scope.comments = response.comments;
					console.log("komentari", $scope.comments);
				},
				function failure(error){
					
					console.log('error in loading comments: ', error);
				});
		
		
	};
	
	$scope.tips = function(){
		$location.path('/tips');
	}
	$scope.getUser = function() {	
		
			homeService.getUser().then(
					
					function success(response){
						
						console.log('loged in user is od profile: ', response.id);
						$scope.user = response;
						$scope.updateUserComments($scope.user.id);
						
					},
					function failure(error){
						
						console.log('error USER NOT FOUND: ', error);
					});
			
			
		};
		
		$scope.convertTimestampToDate = function(timestamp){
			return (new Date(timestamp)).toUTCString().split("GMT")[0];
		};
		
	
		$scope.updateUserComments = function(id){							
			
			var Comments = $resource('/petCareWeb/comments/get/:userId', {userId:'@id'});
			// Create an instance of User resource
			var comments = Comments.get({userId:id}, function(){
				$scope.comments=comments.comments;
				$scope.pictureNo=comments.pictureNo;
				$scope.locationNo=comments.locationNo;
				$scope.commentsNo=comments.comments.length;
				
				console.log("Komnatriiiii beee:" + $scope.comments);
			});	
		
		};
		
		$scope.logout = function(){
			
			LoginService.logout().then(
					
					function success(data){
						$location.path('/');
					},
					function error(err){
						
					});
		}
		
}]);