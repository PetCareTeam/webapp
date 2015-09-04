FirstApp.factory("LoginService", [ '$http' , function($http) {

  return {
 
    login: function(username, password) {
    	var data;
    	$http.get('http://localhost:9966/petCareWeb/admin').success(function(data) {
        	alert(data);
        });
    }

  };
}]);