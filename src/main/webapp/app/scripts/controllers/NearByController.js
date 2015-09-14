FirstApp.controller("NearByController", [ 'nearBy','$scope',
                                   
                                   function(nearBy, $scope) {

                                	   
            $scope.retrivePetStores = function(){
            	
            	console.log('wiqhodhhwqoid');
            	nearBy.retrivePetStores()
            	
            	.then(
            			
            			function success(response){
            				console.log('success, JSON is ' + response.results);
            			},
            			
            			function failure(reason){
            				console.log('failure in retrieving pet stores / validation');
            			}
            	);
            	
            }    	   
                                	   
                                	   
                                	   
                                	   
}]);