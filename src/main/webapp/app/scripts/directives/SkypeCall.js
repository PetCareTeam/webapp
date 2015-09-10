FirstApp
.directive("skypeUi", function() {
    return {
        restrict: "E",
        template: "<div></div>",
        replace: true,
        scope: {
            participants: "="
        },
        link: function(scope, element, attrs){
            Skype.ui({
                "name": "dropdown",
                "element": attrs.id,
                "participants": scope.participants,
                "imageSize": 12
            });
        }
    };
});