FirstApp.controller('ReplyController', [ '$scope', '$modalInstance', 'items', '$rootScope', 'likecomment',

function($scope, $modalInstance, items, $rootScope, likecomment) {

	$scope.comments = items.comment;
	
	$scope.post = items.post;
	
	$scope.newComments = [];
	
	$scope.user = {};
	
	$scope.initModal = function() {
		$scope.user = $rootScope.user;
	}
	$scope.ok = function() {
		$modalInstance.close($scope.selected.item);
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};

	$scope.convertTimestampToDate = function(timestamp) {
		return (new Date(timestamp)).toUTCString().split("GMT")[0];
	};
	
	
	/* reply on post */
	$scope.replyComment = {
			message : '',
			post : {}
	};
	
	$scope.clearPostingReplySection = function() {
		$scope.replyComment = {
				message : '',
				post : {}
		}
		
	};
	/* reply on post */
	
	
	/* leave comment */
	$scope.reply = function() {
		
			var data = {
			post : $scope.post,
			message : $scope.replyComment.message
		}
			
		likecomment
				.comment(data)
				.then(

						function success(response) {
							console.log('success in replying ');
							$scope.newComments.push(response);
							$scope.clearPostingReplySection();
						},
						function failure(error) {
							console
									.log(
											'error in posting comment: ',
											error);
						});
	}
	
	
	
	/* reply on post end */

} ]);