FirstApp.controller('HomeController', ['$scope','homeService', '$location','$rootScope','postService','LoginService', 'likecomment',
                                         function($scope, homeService, $location, $rootScope, postService, LoginService, likecomment) {
	
	$scope.participants = ["mike"];
	
	$scope.phoneptrn = "[0-9]{9}"
	$scope.gPlace;
	
	$scope.comment = {
			message : '',
			skypeId : '',
			address: '',
			longitude: 0,
			latitude: 0,
			type : 'sell'
	}
	
	
	$scope.showType = false;
	
	$scope.showContact = false;
	
	$scope.comments = [];
	
	$scope.newComments = [];
	
	$scope.toAddLocation = false;
	
	$scope.toAddContactPhone=false;

	$scope.user;
	
	$scope.toAddType=false;
	
	$scope.showLocation = false;
	
	$scope.addressShow = '';
	
	$scope.error=false;
	
	$scope.showImage=false;
	
	
	$scope.like=function(id){
		
		likecomment.like(id);
	};
	
	$scope.convertTimestampToDate = function(timestamp){
		return (new Date(timestamp)).toUTCString().split("GMT")[0];
	};
	
	var handleFileSelect = function(evt) {
	    var files = evt.target.files;
	    var file = files[0];

	    if (files && file) {
	        var reader = new FileReader();

	        reader.onload = function(readerEvt) {
	            var binaryString = readerEvt.target.result;
	           
	            $scope.base64imageFileStr = btoa(binaryString);          
	            document.getElementById("previewImg").src = "data:image/jpeg;base64," + btoa(binaryString);
	          
	        };

	        reader.readAsBinaryString(file);
	    }
	};
	
	if (window.File && window.FileReader && window.FileList && window.Blob) {
		document.getElementById('file-upload').addEventListener('change', handleFileSelect, false);
		$scope.showImage=true;
	} else {
	    alert('The File APIs are not fully supported in this browser.');
	}

	$scope.loadComments = function() {	
		
	
		homeService.loadComments().then(
				
				function success(response){
					
					console.log('loaded comments: ', response);
					$scope.comments = response.posts;
				},
				function failure(error){
					
					console.log('error in loading comments: ', error);
				});
		
		
	};
	
	$scope.loadLikes = function(user_id,post_id) {	
		
		
		likecomment.like(user_id, post_id);
		
		
	};
	
	
	
	
	
	
	
	$scope.post = function() {	
		
		var uploadUrl="http://localhost:9966/petCareWeb/posts/post";
		postService.postComment(uploadUrl, $scope.comment).then(
				
				function success(response){
					
					$scope.newComments.push(response);
					
					clearPostingCommentSection();
				},
				function failure(error){
					$scope.error=true;
					console.log('error in posting comment: ', error);
				});
	
	};
	
	$scope.getUser = function() {	
		homeService.getUser().then(
				
				function success(response){
					
					console.log('loged in user is: ', response.username);
					$scope.user = response;
				},
				function failure(error){
					
					console.log('error USER NOT FOUND: ', error);
				});
		
		
	};
	$scope.logout = function() {	
		
		
		LoginService.logout().then(
				
				function success(response){
					
					console.log('user is logged out: ');
					$location.path("/");
				
				},
				function failure(error){
					
					console.log('use is not logged out successfully');
					
				});
		
		
	};
	
	
	
		
	clearPostingCommentSection = function(){
		
		$scope.comment = [];
		
		$scope.comment = {
				message : '',
				type  : "buy"
		}
		$scope.showType="";
		$scope.showLocation=false;
		$scope.showContact=false;
		$scope.showType=false;
		$scope.showImage=false;
		$scope.addressShow="";
	}
	
	$scope.openProfile = function(){
		$location.path("/profile");
	}
	
	$scope.addLocation = function(){
		
		if (!$scope.toAddLocation) {
			$scope.toAddContactPhone = false;
			$scope.toAddType = false;
		}
		$scope.toAddLocation = !$scope.toAddLocation;
	}
	
	$scope.addPhone= function(){
		if (!$scope.toAddContactPhone) {
			$scope.toAddLocation = false;
			$scope.toAddType = false;
			$scope.showContact=true;
		}
		$scope.toAddContactPhone=!$scope.toAddContactPhone;
	}
	
	$scope.addType= function(){
		if (!$scope.toAddType) {
			$scope.toAddContactPhone = false;
			$scope.toAddLocation = false;
			$scope.showType=true;
		}
		$scope.toAddType=!$scope.toAddType;
	}
    
	$scope.sell = function(){
		$scope.showType = "to sell";
		$scope.comment.type = "sell"; /* postiram deka prodavam kuce */
	}
	
	$scope.adopt = function(){
		$scope.showType = "to adopt";
		$scope.comment.type = "adopt"; /* postiram deka davam kuce za vdomuvanje */
	}
	
	$scope.lost = function(){
		$scope.showType = "lost";
		$scope.comment.type = "found/lost"; /* postiram deka kuceto mi se izgubi ili postiram deka najdov kuce - civo e */
	}
	/* ui bootstrap */
	 $scope.isCollapsed = true;
	/* ui bootstrap */
	 
	 $scope.tips = function(){
		 $location.path('/tips');
	 }
	 
	 
	
}]);