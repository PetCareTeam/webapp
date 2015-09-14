FirstApp.service("nearBy", [ '$q', '$rootScope', '$resource','$http', 'crudResource',
                                   
                                   function($q, $rootScope, $resource,$http, crudResource) {
	
	this.retrivePetStores = function(lat, lng){
		
		var dfd = $q.defer();
	
		
//		 var res = $http.jsonp( 'https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=42.0013%2C21.41069&radius=500&types=pet_store&callback=JSON_CALLBACK&key=AIzaSyA_BDNP4tTFTBGo0iCRhFePgnYNz2GL3xM')
//			   .then( function ( response ) {
//				  console.log('response is is is isi s');
//			  });
//		 
//		 console.dir('reds ' + res);
//		
		crudResource.nearBy(
			
				function success(response){
					
					
					dfd.resolve(response);
				},
				
				function reject(reason){
					
					dfd.reject(reason);
				}
		
		);
		
	
		
		
//		$http
//	    .jsonp("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=42.0013%2C21.41069&radius=500&types=pet_store&key=AIzaSyA_BDNP4tTFTBGo0iCRhFePgnYNz2GL3xM&callback=JSON_CALLBACK"
//	    	, {
//	      params: {
//	      
//	        fields: "oembed"
//	      }
//	    })
//	    .success(function(res) {
//	      // never displayed
//	      console.log("success", res);
//	    })
//	    .error(function(data, status, header, config) {
//	      console.log("error data", data); // undefined
//	      console.log("error status", status); // 0
//	      console.log("error header", header); // function (c){a||(a=Nb(b));return c?a[E(c)]||null:a}
//	      console.log("error config", config); // Object {method:"JSONP", params:{access_token:"my_token", fields:"oembed"}, url:"https://api.singly.com/v0/types/news" }
//	    });
		
		
//	var result= $http
//	
//	.jsonp("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=42.0013%2C21.41069&radius=500&types=pet_store&key=AIzaSyA_BDNP4tTFTBGo0iCRhFePgnYNz2GL3xM&callback=JSON_CALLBACK")
//		
//					.success(function(data){
//						console.log('success  wojdowod ' + data);
//						dfd.resolve(data);
//					});
//		
//				console.dir('result   ' + result);	
//				
	
	
//		 
//		var JSON = $resource('http://maps.googleapis.com/maps/api/place/nearbysearch/json?location=42.0013%2C21.41069&radius=500&types=pet_store&key=AIzaSyA_BDNP4tTFTBGo0iCRhFePgnYNz2GL3xM');
//		
//		var result = JSON.get(
//			function success (response){
//				
//				dfd.resolve(response);
//			},
//			
//			function failure (reason){
//				
//				dfd.reject(reason);
//			}
//		);
		
//		var config = {headers:  {
//	        'Access-Control-Allow-Origin': '*'
//	    }
//	};
//		
//		  var url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=42.0013%2C21.41069&radius=500&types=pet_store&key=AIzaSyA_BDNP4tTFTBGo0iCRhFePgnYNz2GL3xM";
//	        var result = $http.get(url, config).success(function (data, status, headers, config) {
//	                console.log('successssaxsasa ' + data);
//	                dfd.resolve(data);
//	            }).error(function (data, status, headers, config) {
//	                //this always gets called
//	                console.log('failure ' + data);
//	                dfd.reject(status);
//	            });
//		
//	        console.log('result is ' + result);
//		
		
		
		/* YOUTUBE JSON-P (PADDING) */
		
		
//		var xhr = new XMLHttpRequest();
//		xhr.open('GET', 'https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=42.0013%2C21.41069&radius=500&types=pet_store&key=AIzaSyA_BDNP4tTFTBGo0iCRhFePgnYNz2GL3xM');
//		xhr.send();
		
		return dfd.promise;
	}
 
}]);