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
		
	/*ova e updateUserPosts*/
		$scope.updateUserComments = function(id){							
			
			var Post = $resource('/petCareWeb/posts/get/:userId', {userId:'@id'});
			// Create an instance of User resource
			var post = Post.get({userId:id}, function(){
				$scope.comments=post.posts;
				console.log(post.posts);
				$scope.pictureNo=post.pictureNo;
				$scope.locationNo=post.locationNo;
				$scope.commentsNo=post.posts.length;
				
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