angular.module('hello', [ 'ngRoute' ])
    .config(function($routeProvider, $httpProvider) {

    $routeProvider.when('/', {
        templateUrl : 'home.html',
        controller : 'home',
        controllerAs : 'controller'
    }).when('/login', {
        templateUrl : 'login.html',
        controller : 'navigation',
        controllerAs : 'controller'
    }).when('/registration', {
        templateUrl : 'registration.html',
        controller : 'navigation',
        controllerAs : 'controller'
    }).when('/createWedding', {
        templateUrl : 'weddingForm.html',
        controller : 'navigation',
        controllerAs : 'controller'
    }).when('/myWeddings', {
        templateUrl : 'myWeddings.html',
        controller : 'navigation',
        controllerAs : 'controller'
    }).when('/myWedding/:id', {
        templateUrl : 'wedding.html',
        controller : 'navigation',
        controllerAs : 'controller'
    })
        .otherwise('/');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

})
    .controller('navigation', function($rootScope, $http, $location, $routeParams ,$route) {

    var self = this;

    var sendWedding = function (form) {
        var data = {
            name:form.name,
            location : {
                address:{
                    city:form.city,
                    state:form.state,
                    country:form.country,
                    zipCode:form.zipCode
                }
            },

            date:form.date
        };
        $http.post('addWedding',data).then(function (response) {
            console.log(response);
        },function () {
            console.log("something happened with wedding");
        });
    };

    var getMyWeddings = function () {
        $http.get('getWeddings').then(function (response) {
            console.log(response.data);
            $rootScope.weddings = response.data;
        }, function() {
            console.log("Something happened when retrieving weddings");
        });
    };

    var getMyWedding = function (guuid) {
        $http.get('getWedding',{guuid:guuid}).then(function (response) {
            $rootScope.myWedding = response.data;
        },function () {
            console.log("Something happened retrieving a wedding");
        })
    };

    if($routeParams.id) {
        getMyWedding($routeParams.id);
    }

    var newAccount = function (credentials) {
        var username = credentials.username;
        var password = credentials.password;
        var data = {
            username:username,
            password:password
        };
        $http.post('register',data).then(function (response) {
            console.log(response);
        },function () {
            console.log("something happened");
        });
    };

    var authenticate = function(credentials, callback) {

        var headers = credentials ? {authorization : "Basic "
            + btoa(credentials.username + ":" + credentials.password)
            } : {};

        $http.get('user', {headers : headers}).then(function(response) {
            if (response.data.name) {
                $rootScope.authenticated = true;
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback();
        }, function() {
            $rootScope.authenticated = false;
            callback && callback();
        });

    }

        authenticate();
        getMyWeddings();

        self.credentials = {};
        self.wedding = {};
        self.login = function() {
            authenticate(self.credentials, function() {
                if ($rootScope.authenticated) {
                    console.log("Login succeeded")
                    $location.path("/");
                    self.error = false;
                    $rootScope.authenticated = true;
                } else {
                    console.log("Login failed")
                    $location.path("/login");
                    self.error = true;
                    $rootScope.authenticated = false;
                }
            })
        };
        self.register = function () {
            newAccount(self.credentials);
        };
        self.logout = function() {
        $http.post('logout', {}).finally(function() {
            $rootScope.authenticated = false;
            $location.path("/");
        });
    };
        self.addWedding = function () {
            sendWedding(self.wedding);
        };
        self.getWeddings = function () {
            getMyWeddings();
        };
        self.getWedding = function (guuid) {
            getMyWedding(guuid);
        }

    })
    .controller('home', function($http) {
        var self = this;
        $http.get('/resource/').then(function(response) {
            self.greeting = response.data;
        })
    });