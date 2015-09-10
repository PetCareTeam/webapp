FirstApp
		.controller(
				'RegisterController',
				[
						'$scope',
						'LoginService',
						'$location',
						function($scope, LoginService, $location) {

							$scope.alert;
									

							$scope.denied = false;
						
							$scope.closeAlert = function(index) {
								$scope.alerts.splice(index, 1);
							};

							$scope.user = {

							};

							$scope.base64imageFileStr = '';

							$scope.register = function() {
								var uploadUrl = "http://localhost:9966/petCareWeb/add";
								if ($scope.userForm.$valid) {
									LoginService
											.register(uploadUrl, $scope.user)
											.then(
													function success(response) {
														$location
																.path('/login');
														// console.log('success.status
														// = ' .
														// response.status);
														console
																.log('response status is: '
																		+ response.status);
													},
													function failure(error) {
														/* status greshka */
														/*
														 * 403, vekje postoi
														 * takov user
														 */
														/*
														 * 500 validacija * /
														 * 
														 */
														$scope.alert = "There is an existing account with this username, choose other username.";
														$scope.denied = true;
														console.log('denied');
																
													});

								} else {
									console.log('form is not valid');
								}

							};

							$scope.uploadFile = function() {

								var file = $scope.user.profilePicture;
								console.log('file is ');
								console.dir(file);

							}

							$scope.fileNameChanged = function(data) {
								console.log("select file " + data.value);
							}

							$("#inputImg")
									.change(
											function() {
												console
														.log("my file changed and now is: "
																+ this);

											});

							var handleFileSelect = function(evt) {
								var files = evt.target.files;
								var file = files[0];

								if (files && file) {
									var reader = new FileReader();

									reader.onload = function(readerEvt) {
										var binaryString = readerEvt.target.result;
										/*
										 * tuka go zemam stringot base64, kodot
										 * od file-ot
										 */
										// $scope.base64imageFileStr =
										// btoa(binaryString);
										// document.getElementById("base64textarea").value
										// = btoa(binaryString);
										document.getElementById("previewImg").src = "data:image/jpeg;base64,"
												+ btoa(binaryString);

									};

									reader.readAsBinaryString(file);
								}
							};

							/*
							 * ova ni e za handle-anje na eventot koga birame
							 * slika
							 */
							if (window.File && window.FileReader
									&& window.FileList && window.Blob) {
								document.getElementById('file-upload')
										.addEventListener('change',
												handleFileSelect, false);
							} else {
								alert('The File APIs are not fully supported in this browser.');
							}

							/* validation */

							$scope.vidi = function() {
								console
										.log("************************************* "
												+ $scope.userForm);
							}

							$scope.$watch('userForm.username.$dirty',
									function() {
										console.log('dirty');

									});

						} ]);