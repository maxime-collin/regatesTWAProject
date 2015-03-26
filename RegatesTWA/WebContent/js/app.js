'use strict';
/**
* @ngdoc overview
* @name monApp
* @description
* # monApp
*
* Main module of the application.
*/

var monApp = angular.module('monApp', [
  'ngRoute',
  'authenticate.js',
  'monAppControllers'
]);

monApp.config(['AuthenticateJSProvider', function (AuthenticateJSProvider) {
    AuthenticateJSProvider.setConfig({
        host: '/',                  // your base api url
        loginUrl: 'connect',        // login api url
        logoutUrl: 'logout',      // logout api url
        loggedinUrl: 'users',  // api to get the user profile and roles

        unauthorizedPage: '/unauthorized',  // url (frontend) of the unauthorized page
        targetPage: '/regate',           // url (frontend) of the target page on login success
        loginPage: '/connect'                 // url (frontend) of the login page
    });
}]);

monApp.config(function ($routeProvider) {
	$routeProvider
		.when('/regates', {
			templateUrl: 'views/regate_list.html',
			controller: 'RegateController',
			security: true
		})
		.when('/regate/:regateId', {
			templateUrl: 'views/regate_visu.html',
			controller: 'RegateController',
			security: true
		})
		.when('/inscription-regate/:regateId', {
			templateUrl: 'views/regate_inscription.html',
			controller: 'RegateController',
			security: true
		})
		.when('/new-regate', {
			templateUrl: 'views/regate_add.html',
			controller: 'RegateController',
			security: true
		})
		.when('/update-regate/:regateId', {
			templateUrl: 'views/regate_update.html',
			controller: 'RegateController',
			security: 'admin'
		})
		.when('/new-course/:regateId', {
			templateUrl: 'views/course_add.html',
			controller: 'CourseController',
			security: true
		})
		.when('/update-course/:regateId/:courseId', {
			templateUrl: 'views/course_update.html',
			controller: 'CourseController',
			security: 'admin'
		})
		.when('/users', {
			templateUrl: 'views/user_list.html',
			controller: 'UserController',
			security: true
		})
		.when('/new-user', {
			templateUrl: 'views/user_add.html',
			controller: 'UserController',
			security: false
		})
		.when('/update-user/:userId', {
			templateUrl: 'views/user_update.html',
			controller: 'UserController',
			security: true
		})
		.when('/connect', {
			templateUrl: 'views/user_connect.html',
			controller: 'UserController',
			security: false
		})
		.when('/logout', {
			templateUrl: 'views/user_connect.html',
			controller: 'UserController',
			security: true
		})
		.when('/unauthorized',{
			templateUrl: 'views/user_unauthorized.html',
			controller: 'UserController',
			security: false
		})
		.when('/new-bateau', {
			templateUrl: 'views/bateau_add.html',
			controller: 'BateauController',
			security: true
		})
		.when('/update-bateau/:bateauId', {
			templateUrl: 'views/bateau_update.html',
			controller: 'BateauController',
			security: 'admin'
		})
		.otherwise({
			redirectTo: '/regates'
		});
});

var monAppControllers = angular.module('monAppControllers', []);