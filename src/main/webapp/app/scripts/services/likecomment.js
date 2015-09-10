FirstApp.service("likecomment", [ 'crudResource' , '$q', 
                                   
                                   function(  crudResource, $q ) {

		this.like = function(userid,postid){
			crudResource.updateLike(userid,postid);
		}

}]);