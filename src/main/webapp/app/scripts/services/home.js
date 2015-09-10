FirstApp.factory('homeService',['crudResource', '$q',
                             
       function(crudResource, $q) {

             return { 
					loadComments : function(){
						
						var dfd = $q.defer();
						crudResource.getComments(
								function successs (response){
									
									dfd.resolve(response);
								},
								function failure (error){
									
									dfd.reject(error);
								}
								
						);
						
						return dfd.promise;
					},
					
					getUser: function(){
						var dfd=$q.defer();
						crudResource.auth(
								function success(response){
									
									dfd.resolve(response);
								},
								function failure(error){
									dfd.reject(error);
								}
					);
					return dfd.promise;
             },
					
					post: function(comment){
						
						var dfd = $q.defer();
						/* with utf-8 message transfer encoding */
//						new String (s.getBytes ("iso-8859-1"), "UTF-8");
//						$scope.comment.message = ($scope.comment.message.getBytes("iso-8859-1"), "UTF-8");
//						console.log('comment message is: ' + $scope.commnet.message);
						crudResource.postComment(comment,
								function successs (response){
									
									dfd.resolve(response);
								},
								function failure (error){
									
									dfd.reject(error);
								}
								
						);
						
						return dfd.promise;
					}
					
					
					
					
             };
}]);