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
  'monAppControllers'
]);

monApp.config(function ($routeProvider) {
	$routeProvider
		.when('/regates', {
			templateUrl: 'views/regate_list.html',
			controller: 'RegateController'
		})
		.when('/regate/:regateId', {
			templateUrl: 'views/regate_visu.html',
			controller: 'RegateController'
		})
		.when('/inscription-regate/:regateId', {
			templateUrl: 'views/regate_inscription.html',
			controller: 'RegateController'
		})
		.when('/new-regate', {
			templateUrl: 'views/regate_add.html',
			controller: 'RegateController'
		})
		.when('/update-regate/:regateId', {
			templateUrl: 'views/regate_update.html',
			controller: 'RegateController'
		})
		.when('/new-course/:regateId', {
			templateUrl: 'views/course_add.html',
			controller: 'CourseController'
		})
		.when('/update-course/:regateId/:courseId', {
			templateUrl: 'views/course_update.html',
			controller: 'CourseController'
		})
		.when('/users', {
			templateUrl: 'views/user_list.html',
			controller: 'UserController'
		})
		.when('/new-user', {
			templateUrl: 'views/user_add.html',
			controller: 'UserController'
		})
		.when('/update-user/:userId', {
			templateUrl: 'views/user_update.html',
			controller: 'UserController'
		})
		.when('/connect', {
			templateUrl: 'views/user_connect.html',
			controller: 'UserController'
		})
		.when('/new-bateau', {
			templateUrl: 'views/bateau_add.html',
			controller: 'BateauController'
		})
		.when('/update-bateau/:bateauId', {
			templateUrl: 'views/bateau_update.html',
			controller: 'BateauController'
		})
		.otherwise({
			redirectTo: '/regates'
		});
});

var monAppControllers = angular.module('monAppControllers', []);