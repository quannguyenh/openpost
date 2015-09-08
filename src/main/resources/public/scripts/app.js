
var app = angular.module('openpost', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/list.html',
        controller: 'ListCtrl'
    }).when('/create', {
        templateUrl: 'views/create.html',
        controller: 'CreateCtrl'
    }).otherwise({
        redirectTo: '/'
    })
});

app.controller('ListCtrl', function ($scope, $http) {
    $http.get('/api/ctx/posts').success(function (data) {
        $scope.posts = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })

    $scope.postStatusChanged = function (post) {
        console.log(post);
        $http.put('/api/ctx/posts' + post.id, post).success(function (data) {
            console.log('status changed');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});

app.controller('CreateCtrl', function ($scope, $http, $location) {
    $scope.post = {
        done: false
    };

    $scope.createPost = function () {
        console.log($scope.post);
        $http.post('/api/ctx/posts', $scope.post).success(function (data) {
            $location.path('/');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});