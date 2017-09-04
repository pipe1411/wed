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
    }).otherwise('/');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

})
    .controller('navigation', function($rootScope, $http, $location, $route) {

    var self = this;

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

        self.credentials = {};
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
    }

    })
    .controller('home', function($http) {
        var self = this;
        $http.get('/resource/').then(function(response) {
            self.greeting = response.data;
        })
    });