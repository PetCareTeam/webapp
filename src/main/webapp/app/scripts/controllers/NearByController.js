FirstApp.controller("NearByController", [ 'nearBy','$scope', '$modal', '$log',
                                   
                                   function(nearBy, $scope, $modal, $log) {

                           
	
	  /* MODAL  */
    
	
    $scope.openModal = function (response) {
    	
    
    
    
			    	 var modalInstance = $modal.open({
			    	      animation: $scope.animationsEnabled,
			    	      templateUrl: 'views/nearbyModal.html',
			    	      controller: 'NearControllerInstance',
			    	      resolve: {
			    	          items: function () {
			    	            return response;
			    	            /* gi prakja komentarite na postot na koj sme kliknale, vo modal-ot */
			    	          	}
			    	      }
			    	 });
			 
    	
    	
			    	}
    
    /* MODAL */
    
    $scope.location = {
    		
    		lat : 0,
    		lng : 0
    };
    

	function showPosition(position) {
		   $scope.location = {
				   lat : position.coords.latitude,
				   lng :position.coords.longitude
			   }
		   console.log('show position ' + $scope.location.lat);
	  
	}
	
	 $scope.current = function(){
		 
		 if (navigator.geolocation) {
		        navigator.geolocation.getCurrentPosition(showPosition);
		        console.log('location is ' + $scope.location.lat);
		    } else { 
		        console.log('geolocation not provided');
		    }
		
     }
	 
	
	  
            $scope.retrivePetStores = function(){
            	
            	console.log('wiqhodhhwqoid');
            	nearBy.retrivePetStores($scope.location)
            	
            	.then(
            			
            			function success(response){
            				
            				console.dir('success, JSON is ' + response.results);
            				$scope.openModal(response.results);
            			},
            			
            			function failure(reason){
            				console.log('failure in retrieving pet stores / validation');
            			}
            	);
            	
            }    	   
                   
            
           
            
  $scope.retrivePharmacies = function(){
            	
            	console.log('wiqhodhhwqoid');
            	nearBy.retrivePharmacies($scope.location)
            	
            	.then(
            			
            			function success(response){
            				
            				console.dir('success, JSON is ' + response.results);
            				$scope.openModal(response.results);
            			},
            			
            			function failure(reason){
            				console.log('failure in retrieving pet stores / validation');
            			}
            	);
            	
            }    	   
  
  
  
  $scope.retriveParks = function(){
  	
  	console.log('wiqhodhhwqoid');
  	nearBy.retriveParks($scope.location)
  	
  	.then(
  			
  			function success(response){
  				
  				console.dir('success, JSON is ' + response.results);
  				$scope.openModal(response.results);
  			},
  			
  			function failure(reason){
  				console.log('failure in retrieving pet stores / validation');
  			}
  	);
  	
  }    	   
  
        
  $scope.retriveVet = function(){
	  	
	  	console.log('wiqhodhhwqoid');
	  	nearBy.retriveVet($scope.location)
	  	
	  	.then(
	  			
	  			function success(response){
	  				
	  				console.dir('success, JSON is ' + response.results);
	  				$scope.openModal(response.results);
	  			},
	  			
	  			function failure(reason){
	  				console.log('failure in retrieving pet stores / validation');
	  			}
	  	);
	  	
	  }  
            /* nearby locations */
            $scope.items = [];
                                	   
                                	   
                                	   
}]);



FirstApp.controller('NearControllerInstance', [ '$scope', '$modalInstance',  'Map', 'items', function ($scope, $modalInstance,Map, items) {

	 $scope.items = items;
	  
	    
	  $scope.ok = function () {
		    $modalInstance.dismiss('ok');
		  };

		
	  
	}]);