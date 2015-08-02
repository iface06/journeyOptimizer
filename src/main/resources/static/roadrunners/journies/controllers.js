var CONTROLLERS = angular.module('roadrunnerApp.controllers', ['DAOs']);
CONTROLLERS.controller('MyJourniesController', function ($scope, $http, $location, $journeyDao) {
    $scope.journies = [];
    $scope.journey = {};
    $scope.loadJournies = function () {
        $journeyDao.loadJournies(function (journies) {
            $scope.journies = journies;
            
        });
    };

    $scope.createNewJourney = function () {
        $journeyDao.createJourney(function (journey) {
            $scope.journey = journey;
            $location.path('/myJourneyCreation');
        });
    };

    $scope.openJourney = function (id) {
        $location.path('/myJourney/' + id);
    };

    $scope.loadJournies();
});

CONTROLLERS.controller('MyJourneyController', function ($scope, $routeParams, $http, $log, $location, $geodataDao, $journeyDao) {

    $scope.cities = [];

    $journeyDao.loadJourney($routeParams.id, function (journey) {
        $scope.journey = journey;
        var date = new Date();
        date.setTime(journey.createdDate);
        $scope.journey.createdDate = date;
    });

    $scope.getCity = function (query) {
        if (query !== null && query !== 'undefined' && query.length > 1) {
            $http.defaults.useXDomain = true;
            var url = 'http://localhost:8182/geodata/cities/' + query;
            $http.get(url).success(function (data) {
                $scope.cities = data;
                return data;
            });
        }
    };

    $scope.addToJourney = function (index) {
         var city = $scope.cities[index];
        var destination = {
            name: city.name,
            state: city.state.name,
            county: city.county.name,
            geodataEntityId: city.id,
            lat: city.lat,
            lng: city.lng
        };
        $journeyDao.addDestination(destination, function (data) {
            if (data.successful) {
                $scope.journey.destinations.push(data.city);
            }
        });
    };
    
    $scope.deleteFromJourney = function (index) {
        if (index < $scope.journey.destinations.length) {
            var destination = $scope.journey.destinations[index];
            $journeyDao.deleteDestination(destination, function (data) {
                if (data.successful) {
                    $scope.journey.destinations.splice(index, 1);
                }
            });
        }

    };
    

    $scope.deleteCityList = function () {
        $scope.journey.destinations = [];
    };

    $scope.saveJourney = function () {
        $journeyDao.saveJourney($scope.journey, function (data) {
            $scope.journey = data;
            var date = new Date();
            date.setTime(data.createdDate);
            $scope.journey.createdDate = date;
        });
    };

    $scope.optimiseJourney = function () {
        $journeyDao.optimizeJourney(function(journey){
            $scope.journey = journey;
            var date = new Date();
            date.setTime(journey.createdDate);
            $scope.journey.createdDate = date;
        });
    };

});

CONTROLLERS.controller('MyJourneyCreationController', function ($scope, $routeParams, $http, $log, $location, $geodataDao, $journeyDao) {
    $scope.journey = {title: "", createdDate: new Date(), destinations: []};

    $scope.getCity = function (query) {
        $geodataDao.queryCity(query, function (data) {
            $scope.cities = data;
            return data;
        });
    };

    $scope.addToJourney = function (index) {
        var city = $scope.cities[index];
        var destination = {
            name: city.name,
            state: city.state.name,
            county: city.county.name,
            geodataEntityId: city.id,
            lat: city.lat,
            lng: city.lng
        };
        $journeyDao.addDestination(destination, function (data) {
            if (data.successful) {
                $scope.journey.destinations.push(data.city);
            }
        });
    };

    $scope.deleteFromJourney = function (index) {
        if (index < $scope.journey.destinations.length) {
            var destination = $scope.journey.destinations[index];
            $journeyDao.deleteDestination(destination, function (data) {
                if (data.successful) {
                    $scope.journey.destinations.splice(index, 1);
                }
            });
        }

    };

    $scope.deleteCityList = function () {
        $scope.journey = [];
    };

    $scope.saveJourney = function () {
        $journeyDao.saveJourney($scope.journey, function (data) {
            $scope.journey = data;
            var date = new Date();
            date.setTime(data.createdDate);
            $scope.journey.createdDate = date;
        });
    };

    $scope.optimiseJourney = function () {
        $journeyDao.optimizeJourney(function(journey){
            $scope.journey = journey;
            var date = new Date();
            date.setTime(journey.createdDate);
            $scope.journey.createdDate = date;
        });
    };
});


