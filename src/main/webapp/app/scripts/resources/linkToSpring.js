FirstApp.service('userResource', ['$resource',
	function($resource){

		
		var BASEURL = 'http://localhost:9966/petCareWeb/';

		return $resource(BASEURL + ':service/:action/:id',
		{  
			id: '@id'
		},
		{
			register: {

				method:'GET',
				params:{
					service:'register',
					action:'register'
				}
			},
			login: {

				method:'POST',
				params:{
					service:'accounts',
					action:'login'
				}
			},
			tempAccount:{
				method:'POST',
				params:{
					service:'players'
				}
			},
			selectBoards:{
				method:'GET',
				params:{
					service:'boards'
				}
			},
			selectBoard:{
				method:'GET',
				params:{
					service:'boards'
				}
			},
			createBoard:{
				method:'POST',
				params:{
					service:'boards'
				}
			},
			deleteBoard: {
				method:'DELETE',
				params:{
					service:'boards'
				}
			},
			authInfo: {
				method:'GET',
				params:{
					service:'accounts',
					action:'token'
				}
			},updateBoard: {
				method:'PUT',
				params:{
					service:'boards'
				}
			},
			authInfoPlayer: {
				method:'GET',
				params:{
					service:'players',
					action:'token'
				}
			}
		});


}]);