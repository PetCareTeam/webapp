FirstApp
		.controller(
				'HomeController',
				[
						'$scope',
						'homeService',
						'$location',
						'$rootScope',
						'postService',
						'LoginService',
						'likecomment',
						'$resource',
						'c3SimpleService',
						'$interval',
						'$timeout',
						 '$modal', 
						 '$log',
						 'Map',
						 'reply',
						 '$rootScope',
						function($scope, homeService, $location, $rootScope,
								postService, LoginService, likecomment,
								$resource, c3SimpleService,$interval,$timeout, $modal, $log, Map, reply, $rootScope) {

							$scope.phoneptrn = "[0-9]{9}"
							$scope.gPlace;

							$scope.comment = {
								message : '',
								skypeId : '',
								address : '',
								longitude : 0,
								latitude : 0,
								type : 'sell'
							}

							$scope.showType = false;

							$scope.showContact = false;

							$scope.comments = [];

							$scope.newComments = [];

							$scope.toAddLocation = false;

							$scope.toAddContactPhone = false;

							$scope.user;

							$scope.toAddType = false;

							$scope.showLocation = false;

							$scope.addressShow = '';

							$scope.error = false;

							$scope.showImage = false;

							$scope.flagsLike = [];

							/* za pitata */
							
							$scope.lostNum = 0;
							
							$scope.injuredNum = 0;
							
							$scope.foundNum = 0;
							
							$scope.sellingNum = 0;
							
							$scope.adoptingNum = 0;
							
							/* za pitata */
							
							/*
							 * dobivam pochetna lista od falses i trues -
							 * sostojba od baza
							 */

							$scope.convertTimestampToDate = function(timestamp) {
								return (new Date(timestamp)).toUTCString()
										.split("GMT")[0];
							};

							var handleFileSelect = function(evt) {
								var files = evt.target.files;
								var file = files[0];

								if (files && file) {
									var reader = new FileReader();

									reader.onload = function(readerEvt) {
										var binaryString = readerEvt.target.result;

										$scope.base64imageFileStr = btoa(binaryString);
										document.getElementById("previewImg").src = "data:image/jpeg;base64,"
												+ btoa(binaryString);

									};

									reader.readAsBinaryString(file);
								}
							};

							if (window.File && window.FileReader
									&& window.FileList && window.Blob) {
								document.getElementById('file-upload')
										.addEventListener('change',
												handleFileSelect, false);
								$scope.showImage = true;
							} else {
								alert('The File APIs are not fully supported in this browser.');
							}

							
							$scope.updatePita = function(){
								$scope.chart.data.columns =    [							     
														          ['Lost pets',$scope.lostNum ],
														          ['Injured pets', $scope.injuredNum],
														          ['Pets given for selling', $scope.sellingNum],
														          ['Pets given for adopting', $scope.adoptingNum],
														          ['Found pets', $scope.foundNum]
														        ];
							}
							/* za pitata */
							
							$scope.initPita = function(){
								
								for (var int = 0; int < $scope.comments.length; int++) {
									switch ($scope.comments[int].type) {
										case 'found':{
											$scope.foundNum  = $scope.foundNum + 1;
											break;										
										};
										case 'lost':{
											$scope.lostNum  = $scope.lostNum+ 1;
											break;										
										};
										case 'adopt':{
											$scope.adoptingNum  = $scope.adoptingNum + 1;
											break;										
										};
										case 'sell':{
											$scope.sellingNum = $scope.sellingNum + 1;
											break;										
										};
										case 'injured':{
											$scope.injuredNum = $scope.injuredNum + 1;
											break;										
										};
										
										default:
											break;
									}
								}
								
								$scope.updatePita();
								console.log("STATISTIC: " + $scope.sellingNum);
							}
							
							
							$scope.loadComments = function() {

								homeService
										.loadComments()
										.then(

												function success(response) {

													
													$scope.comments = response.posts;
													$scope.initPita();
													$scope.initFlagsLike(); /* od baza chita permisii za lajk */
													
												},
												function failure(error) {

													console
															.log(
																	'error in loading comments: ',
																	error);
												});

							};

							$scope.loadLikes = function(user_id, post_id) {

								likecomment.like(user_id, post_id);

							};

							$scope.post = function() {
console.log('validation ' + $scope.comment.message.length);
								
								if (($scope.comment.message.length > 0)) {
									
									$scope.error = false;
									$scope.toAddLocation = false;
									$scope.toAddContactPhone = false;
									var uploadUrl = "http://localhost:9966/petCareWeb/posts/post";
									postService
											.postComment(uploadUrl, $scope.comment)
											.then(

													function success(response) {

														$scope.newComments
																.push(response);

														/* updating pita */
														switch ($scope.comment.type) {
															case 'found':{
																$scope.foundNum  = $scope.foundNum + 1;
																break;										
															};
															case 'lost':{
																$scope.lostNum  = $scope.lostNum+ 1;
																break;										
															};
															case 'adopt':{
																$scope.adoptingNum  = $scope.adoptingNum + 1;
																break;										
															};
															case 'sell':{
																$scope.sellingNum = $scope.sellingNum + 1;
																break;										
															};
															case 'injured':{
																$scope.injuredNum = $scope.injuredNum + 1;
																break;										
															};
															
															default:
																break;
														}
														
														$scope.updatePita();
														
														clearPostingCommentSection();
													},
													function failure(error) {
														$scope.error = true;
														console
																.log(
																		'error in posting comment: ',
																		error);
													});
									
								} else {
									
									$scope.error = true;
								}
								
								
								

							};

							
							$scope.getUser = function() {
								homeService.getUser().then(

										function success(response) {

											console.log('loged in user is: ',
													response.username);
											$scope.user = response;
											$rootScope.user = response;
										},
										function failure(error) {

											console.log(
													'error USER NOT FOUND: ',
													error);
										});

							};
							$scope.logout = function() {

								LoginService
										.logout()
										.then(

												function success(response) {

													console
															.log('user is logged out: ');
													$location.path("/");

												},
												function failure(error) {

													console
															.log('user is not logged out successfully');

												});

							};

							clearPostingCommentSection = function() {

								$scope.comment = [];

								$scope.comment = {
										message : '',
										skypeId : '',
										address : '',
										longitude : 0,
										latitude : 0,
										type : 'sell'
								}
								
								$scope.showType = "";
								$scope.showLocation = false;
								$scope.showContact = false;
								$scope.showType = false;
								$scope.showImage = false;
								$scope.addressShow = "";
							}

							$scope.openProfile = function() {
								$location.path("/profile");
							}

							$scope.addLocation = function() {

								if (!$scope.toAddLocation) {
									$scope.toAddContactPhone = false;
									$scope.toAddType = false;
								}
								$scope.toAddLocation = !$scope.toAddLocation;
							}

							$scope.addPhone = function() {
								if (!$scope.toAddContactPhone) {
									$scope.toAddLocation = false;
									$scope.toAddType = false;
									$scope.showContact = true;
								}
								$scope.toAddContactPhone = !$scope.toAddContactPhone;
							}

							$scope.addType = function() {
								if (!$scope.toAddType) {
									$scope.toAddContactPhone = false;
									$scope.toAddLocation = false;
									$scope.showType = true;
								}
								$scope.toAddType = !$scope.toAddType;
							}

							$scope.sell = function() {
								$scope.showType = "sell";
								$scope.comment.type = "sell"; 
							}

							$scope.adopt = function() {
								$scope.showType = "adopt";
								$scope.comment.type = "adopt"; 
							}

							$scope.lost = function() {
								$scope.showType = "lost";
								$scope.comment.type = "lost";
							}
							
							$scope.found = function() {
								$scope.showType = "found";
								$scope.comment.type = "found"; 
							}
							
							
							$scope.injure = function() {
								$scope.showType = "injured";
								$scope.comment.type = "injured"; 
							}
							

							$scope.tips = function() {
								$location.path('/tips');
							}

						

							$scope.changeHrefForCall = function(index, skypeId,
									flag) {
								if (flag === 0) {
									// new comments
									$('#skype-new-' + index).attr('href',
											'skype:' + skypeId + '?call');

								} else {
									// old comments
									$('#skype-' + index).attr('href',
											'skype:' + skypeId + '?call');
								}

							}

							$scope.likedPosts;
							$scope.initFlagsLike = function() {

								/* gi zemam site postovi koi sum gi lajknala */
								likecomment.getLikedPosts().then(
										
										function success(response){
//											console.log('succ');
											$scope.likedPosts = response.likes;
//											console.log('comments length is ' + $scope.comments.length);
//											console.log('liked posts length is ' + $scope.likedPosts.length);
											
											for (var int = 0; int < $scope.comments.length; int++) {
												for (var int2 = 0; int2 < $scope.likedPosts.length; int2 ++) {
//													console.log('checking id ' + $scope.comments[int].id + ' and ' + $scope.likedPosts[int2].post.id);
													if($scope.comments[int].id == $scope.likedPosts[int2].post.id){
//														console.log('found and index that should change is ' + ($scope.comments.length - 1 - int) );
														$('#post-' + ($scope.comments.length - 1 - int)).attr('class',
																'fa fa-heart');
														break;
													}
												}
											}
											
										},
										function failure(reason){
											console.log('failure in getting liked posts ' + reason);
										}
								);
								
							}
							
							$scope.like = function(post, index) {
								
								
						
							//	console.log('likes for this post were ' + $scope.comments[$scope.comments.length - 1 - index].message);
							
								likecomment.like(post).then(function success(response){
									
									// like
									$('#post-' + index).attr('class',
											'fa fa-heart');
									
									console.log('likes for this post were ' + $scope.comments[$scope.comments.length - 1 - index].likes);
									$scope.comments[$scope.comments.length - 1 - index].likes = $scope.comments[$scope.comments.length - 1 - index].likes + 1;
									console.log('and now they are ' + $scope.comments[$scope.comments.length - 1 - index].likes);
									
								
									
								}, function failure(error){
									
									if(error.status == 409){
										// unlike
										$('#post-' + index).attr('class',
										'fa fa-heart-o');
										console.log('likes for this post were ' + $scope.comments[$scope.comments.length - 1 - index].likes);
										$scope.comments[$scope.comments.length - 1 - index].likes = $scope.comments[$scope.comments.length - 1 - index].likes - 1;
										console.log('and now they are ' + $scope.comments[$scope.comments.length - 1 - index].likes);
									}
									
									console.log('error ' + error.status);
									
								})

							};
							
						
							
							
							
							/* new comments */

							$scope.likeNew = function(post, index) {
								
								
								
								//	console.log('likes for this post were ' + $scope.comments[$scope.comments.length - 1 - index].message);
								
									likecomment.like(post).then(function success(response){
										
										// like
										$('#post-new-' + index).attr('class',
												'fa fa-heart');
										
										console.log('likes for this post were ' + $scope.newComments[$scope.newComments.length - 1 - index].likes);
										$scope.newComments[$scope.newComments.length - 1 - index].likes = $scope.newComments[$scope.newComments.length - 1 - index].likes + 1;
										console.log('and now they are ' + $scope.newComments[$scope.newComments.length - 1 - index].likes);
										
									
										
									}, function failure(error){
										
										if(error.status == 409){
											// unlike
											$('#post-new-' + index).attr('class',
											'fa fa-heart-o');
											console.log('likes for this post were ' + $scope.newComments[$scope.newComments.length - 1 - index].likes);
											$scope.newComments[$scope.newComments.length - 1 - index].likes = $scope.newComments[$scope.newComments.length - 1 - index].likes - 1;
											console.log('and now they are ' + $scope.newComments[$scope.newComments.length - 1 - index].likes);
										}
										
										console.log('error ' + error.status);
										
									})

								};
								
							

							
							
							/* C3-D3 data */

							 $scope.chart = {
								      data : {
								        type: 'donut',
								       
								        columns: [							     
								          ['Lost pets',$scope.lostNum ],
								          ['Injured pets', $scope.injuredNum],
								          ['Pets given for selling', $scope.sellingNum],
								          ['Pets given for adopting', $scope.adoptingNum],
								          ['Found pets', $scope.foundNum]
								        ],
								        colors: {
								            'Lost pets': '#c49cde',
								           'Injured pets': '#f0776c',
								            'Pets given for selling': '#fecf71',
								            	'Pets given for adopting': '#62c2e4',
									            'Found pets': '#c4e17f'
								        },
								      
								      },
								     
								      legend: {
								        show: true
								      },
								      donut: {
								          title: "Pet Care Statistics"
								      }
								    };
							
							 $scope.home = function(){
								 event.preventDefault();
							 }
							/* end C3 - D3 DATA */
							 
							 /*ladda button */
							  $scope.loadingWithProgress = function () {
							        // Set progress 0;
							        $scope.laddaLoadingBar = 0;
							        // Run in every 30 milliseconds
							        var interval = $interval(function () {
							            // Increment by 1; 
							            $scope.laddaLoadingBar++;
							            if ($scope.laddaLoadingBar >= 100) {
							                // Cancel interval if progress is 100
							                $interval.cancel(interval);
							                //Set ladda loading false
							                $scope.laddaLoadingBar = false;
							            }
							        }, 30);
							    };
							 
							 /*ladda button*/

							    
							    
							    /* MODAL REPLY */
							    
							    $scope.openReplyModal = function (post_id, size) {
							    	
							    
							    
							    	reply.getReplies(post_id).then(
							    			
							    			function success(response){
							    				console.log('success getting replies');
							    			
							    				
										    	 var modalInstance = $modal.open({
										    	      animation: $scope.animationsEnabled,
										    	      templateUrl: 'views/replyModal.html',
										    	      controller: 'ReplyController',
										    	      size: size,
										    	      resolve: {
										    	          items: function () {
										    	            return response;
										    	            /* gi prakja komentarite na postot na koj sme kliknale, vo modal-ot */
										    	          	}
										    	      }
										    	    });
							    			},
							    			
							    			function failure(reason){
							    				console.log('failure getting replies');
							    			}
							    	);
							    	
							    	
								   
								  };
							    
							    /* MODAL */
								  
								
								  
								  /* MODAL MAPA */
								  
								  $scope.openMaps = function (size) {

									    var modalInstance = $modal.open({
									      animation: $scope.animationsEnabled,
									      templateUrl: 'myModalContent.html',
									      controller: 'ModalInstanceCtrl',
									      size: size,
							    	      resolve: {
							    	          items: function () {
							    	            return $scope.comments;
							    	            /* gi prakja komentarite na postot na koj sme kliknale, vo modal-ot */
							    	          	}
							    	      }
									    });

									    modalInstance.result.then(function () {
									    
									    	
									    }, function () {
									      $log.info('Modal dismissed at: ' + new Date());
									    });
									  };
									  
								  /* MODAL MAPA */
								  
							
						} ]);

FirstApp.controller('ModalDemoCtrl', ['$scope', '$modal', '$log', 'Map', function ($scope, $modal, $log, Map) {

	 
	  $scope.open = function (size) {

	    var modalInstance = $modal.open({
	      animation: $scope.animationsEnabled,
	      templateUrl: 'views/modalMaps.html',
	      controller: 'ModalInstanceCtrl',
	      size: size
	    });

	    modalInstance.result.then(function (selectedItem) {
	    }, function () {
	      $log.info('Modal dismissed at: ' + new Date());
	    });
	  };


	}]);

FirstApp.controller('ModalInstanceCtrl', [ '$scope', '$modalInstance',  'Map', 'items', function ($scope, $modalInstance,Map, items) {

	 
	  $scope.init = function(){
		  Map.init(41.9940956,21.4411543);
		  
		  console.log('init2');
	  }
	    
	  $scope.ok = function () {
		    $modalInstance.dismiss('ok');
		  };

		
	  
	}]);