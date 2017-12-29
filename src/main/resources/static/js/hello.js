angular.module('hello', [ 'ngRoute' ,'ui.bootstrap'])
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
    }).when('/landing', {
            templateUrl : 'landing.html',
            controller : 'navigation',
            controllerAs : 'controller'
    }).when('/viewsurveys', {
        templateUrl: 'survey.html',
        controller: 'navigation',
        controllerAs: 'controller'
    }).otherwise('/');

    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

})
    .factory('weddingGuuid', function() {
    var savedData = {}
    function set(guuid) {
        savedData = guuid;
    }
    function get() {
        return savedData;
    }

    return {
        set: set,
        get: get
    }
})

    .controller('navigation', ['$rootScope', '$http', '$location', '$routeParams' ,'$route','$uibModal','weddingGuuid', function($rootScope, $http, $location, $routeParams ,$route,$uibModal,weddingGuuid) {

    var self = this;
    self.guestPost = {};
    self.wedGuuid = "";
    self.registrationSuccess = false;

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

    var getSurveys = function () {
        $rootScope.surveys = null;
        $http.get('surveys').then(function (response) {
            console.log(response.data);
            $rootScope.surveys = response.data;
        }, function() {
            console.log("Something happened when retrieving surveys");
        });
    };

    getSurveys();

    var getMyWeddings = function () {
        $rootScope.myWedding = null;
        $http.get('getWeddings').then(function (response) {
            console.log(response.data);
            $rootScope.weddings = response.data;
        }, function() {
            console.log("Something happened when retrieving weddings");
        });
    };

    var getMyWedding = function (guuid) {
        self.wedGuuid = guuid;
        weddingGuuid.set(guuid);
        console.log(typeof guuid);
        var data = {
            guuid:guuid
        }
        $http.post('/getWedding/',data).then(function (response) {
            $rootScope.myWedding = response.data;
        },function () {
            console.log("Something happened retrieving a wedding");
        })
    };

    $rootScope.attending = function () {
        var count = 0;
        for (var i = 0; i <= $rootScope.myWedding.guests.length-1;i++) {
            var gst = $rootScope.myWedding.guests[i];
            if (gst.rsvp == "Attending") {
                count = count + 1;
            }
        }

        return count;
    };

    $rootScope.attendingPercent = function () {
        var num = $rootScope.attending()/$rootScope.myWedding.guests.length;
        return num*100;
    };

    $rootScope.notAttendingPercent = function () {
        var num = $rootScope.notAttending()/$rootScope.myWedding.guests.length;
        return num*100;
    };

    $rootScope.pendingPercent = function () {
        var num = $rootScope.pending()/$rootScope.myWedding.guests.length;
        return num*100;
    };

    $rootScope.notAttending = function () {
        var count = 0;
        for (var i = 0; i <= $rootScope.myWedding.guests.length-1;i++) {
            var gst = $rootScope.myWedding.guests[i];
            if (gst.rsvp == "Not Attending") {
                count = count + 1;
            }
        }

        return count;
    };

    $rootScope.pending = function () {
        var count = 0;
        for (var i = 0; i <= $rootScope.myWedding.guests.length-1;i++) {
            var gst = $rootScope.myWedding.guests[i];
            if (gst.rsvp == "Pending") {
                count = count + 1;
            }
        }

        return count;
    };

    self.currentGuest = function (guest) {
        self.viewGuest = guest;
    }

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
            if (response.data.result === "success"){
                $rootScope.registrationSuccess = true;
            }
            else {
                $rootScope.registererror = true;
            }
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
            self.wedGuuid = guuid;
            getMyWedding(guuid);
        };
        self.openGuestModal = function () {
            self.guestPost={};
            $uibModal.open({
                templateUrl: 'newGuest.html',
                controller: 'ModalInstanceCtrl',
                resolve : {
                    guestPost : function() {
                        return self.guestPost;
                    }
                }
            }).result.then(function (result) {
                $rootScope.myWedding.guests.push(result.guest);
                self.guestPost = result;
                $http
                    .post('addGuest',self.guestPost)
                    .success(function (data) {
                        console.log("success")
                    })
                    .error(function (data, status) {
                        console.log(status);
                    });
                self.$apply();

            });
        };



        self.openGuestEditModal = function (gst) {
            self.guestPost = gst;
            $uibModal.open({
                templateUrl: 'editGuest.html',
                controller: 'ModalInstanceEditCtrl',
                resolve : {
                    guestPost : function() {
                        return self.guestPost;
                    }
                }
            }).result.then(function (result) {


                $http
                    .post('updateGuest',result)
                    .success(function (data) {
                        console.log("success")
                    })
                    .error(function (data, status) {
                        console.log(status);
                    });
                self.$apply();

            });
        };

    }])
    .controller('home', function($http) {
        var self = this;
        self.helloWorld = "hello world from home controller";
        $http.get('/resource/').then(function(response) {
            self.greeting = response.data;
        })
    })
    .controller('ModalInstanceCtrl', function ($scope,$uibModalInstance,$http,weddingGuuid,guestPost) {
        $scope.guestPost = guestPost;

        /*$scope.guest = guestPost;*/

        $scope.button = "rsvp";
        $scope.actions = [
            "Attending", "Not Attending", "Pending"
        ];

        $scope.change = function(name){
            $scope.button = name;
        };


        $scope.ok = function () {
            var wguuid = weddingGuuid.get();
            $scope.guestPost.guest=$scope.guest;
            $scope.guestPost.guest.rsvp = $scope.button;
            $scope.guestPost.guuid=wguuid;
            var data = {guest:$scope.guest,guuid:wguuid};
            $uibModalInstance.close($scope.guestPost);
        };

        $scope.addGuest = function () {
            $scope.guest.rsvp = $scope.button;
            var wguuid = weddingGuuid.get();

            $scope.guestPost.guest=$scope.guest;
            $scope.guestPost.guuid=wguuid;
            var data = {guest:$scope.guest,guuid:wguuid};
            $http
                .post('addGuest',data)
                .success(function (data) {
                    $scope.cancel();
                })
                .error(function (data, status) {
                    console.log(status);
                });
        };

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    })
    .controller('ModalInstanceEditCtrl', function ($scope,$uibModalInstance,$http,weddingGuuid,guestPost) {
        $scope.guestPost = {};
        $scope.guest = guestPost;
        /*$scope.guest = guestPost;*/

        $scope.button = guestPost.rsvp;
        $scope.actions = [
            "Attending", "Not Attending", "Pending"
        ];

        $scope.change = function(name){
            $scope.button = name;
        };


        $scope.ok = function () {
            var wguuid = weddingGuuid.get();
            $scope.guestPost.guest=$scope.guest;
            $scope.guestPost.guest.rsvp = $scope.button;
            $scope.guestPost.guuid=wguuid;
            var data = {guest:$scope.guest,guuid:wguuid};
            $uibModalInstance.close($scope.guestPost);
        };

        $scope.addGuest = function () {
            $scope.guest.rsvp = $scope.button;
            var wguuid = weddingGuuid.get();

            $scope.guestPost.guest=$scope.guest;
            $scope.guestPost.guuid=wguuid;
            var data = {guest:$scope.guest,guuid:wguuid};
            $http
                .post('addGuest',data)
                .success(function (data) {
                    $scope.cancel();
                })
                .error(function (data, status) {
                    console.log(status);
                });
        };

    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});
