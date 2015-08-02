
angular.module('roadrunnerApp', ['ngRoute', 'roadrunnerApp.controllers'])
        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/myJourneyCreation', {templateUrl: 'roadrunners/journies/myJourneyCreation.html', controller: 'MyJourneyCreationController'})
                $routeProvider.when('/myJourney/:id', {templateUrl: 'roadrunners/journies/myJourney.html', controller: 'MyJourneyController'})
                $routeProvider.when('/myJournies', {templateUrl: 'roadrunners/journies/myJournies.html', controller: 'MyJourniesController'});
                $routeProvider.otherwise({redirectTo: '/myJournies'});
            }]);


var daos = angular.module('DAOs', []);
daos.factory('$geodataDao', ['$http', function ($http) {
        var queryCity = function (cityName, okCallback, errorCallback) {
            if (cityName !== null && cityName !== 'undefined' && cityName.length > 1) {
                $http.defaults.useXDomain = true;
                var url = 'http://localhost:8182/geodata/cities/' + cityName;
                $http.get(url).success(okCallback);
            }
        };

        return {
            queryCity: queryCity
        };
    }]);

daos.factory('$journeyDao', ['$http', function($http){
        
        var saveJourney = function(journey, okCallback){
            var url = 'http://localhost:8080/myJourney/save';
            $http.post(url, journey).success(okCallback);
        };
        
        var addDestination = function(destination, okCallback){
            var url = 'http://localhost:8080/myJourney/addDestination';
            $http.post(url, destination).success(okCallback);
        };
        
        var deleteDestination = function(destination, okCallback){
            var url = 'http://localhost:8080/myJourney/deleteDestination';
            $http.post(url, destination).success(okCallback);
        };
        
        var createJourney = function(okCallback){
            var url = 'http://localhost:8080/myJourney/create';
            $http.get(url).success(okCallback);
        };
        
        var loadJournies = function(okCallback){
            var url = 'http://localhost:8080/myJournies/load';
            $http.get(url).success(okCallback);
        };
        
        var loadJourney = function(id, okCallback){
            var url = 'http://localhost:8080/myJourney/' + id;
            $http.get(url).success(okCallback);
        };
        
        var optimizeJourney = function(okCallback){
            var url = 'http://localhost:8080/myJourney/optimize';
            $http.get(url).success(okCallback);
        };
        
        return {
            saveJourney: saveJourney,
            addDestination: addDestination,
            deleteDestination: deleteDestination,
            createJourney: createJourney,
            loadJournies: loadJournies,
            loadJourney: loadJourney,
            optimizeJourney: optimizeJourney
        };
}]);