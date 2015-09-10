
        /* Directives */
        FirstApp.
        directive('googleplace', function() {
            return {
                require: 'ngModel',
                link: function(scope, element, attrs, model) {
                    var options = {
                        types: [],
                        componentRestrictions: {}
                    };
                    scope.gPlace = new google.maps.places.Autocomplete(element[0], options);

                    google.maps.event.addListener(scope.gPlace, 'place_changed', function() {

                        scope.$apply(function() {
                        	
                            model.$setViewValue(element.val());
                            scope.comment.address=element.val();
                            scope.comment.latitude =  scope.gPlace.getPlace().geometry.location.lat();
                            scope.comment.longitude =  scope.gPlace.getPlace().geometry.location.lng();
                            scope.showLocation = true;
                            scope.toAddLocation = false;
                            scope.addressShow = scope.comment.address;
                            scope.collapsed = false;
                            console.log('kraj');
                        });
                    });
                }
            };
        });