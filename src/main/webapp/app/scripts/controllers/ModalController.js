FirstApp.controller('ModalDemoCtrl', function ($scope, $modal, $log, Map) {

 
  $scope.open = function (size) {

    var modalInstance = $modal.open({
      animation: $scope.animationsEnabled,
      templateUrl: 'myModalContent.html',
      controller: 'ModalInstanceCtrl',
      size: size,
      resolve: {
        items: function () {
          return $scope.items;
        }
      }
    });

    modalInstance.result.then(function (selectedItem) {
      $scope.selected = selectedItem;
    }, function () {
      $log.info('Modal dismissed at: ' + new Date());
    });
  };


});

// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $modal service used above.

FirstApp.controller('ModalInstanceCtrl', function ($scope, $modalInstance, items, Map) {

 
  $scope.init = function(){
	  Map.init(41.9940956,21.4411543);
	  
	  console.log('init2');
  }
    
  $scope.ok = function () {
	    $modalInstance.dismiss('ok');
	  };

	
  
});