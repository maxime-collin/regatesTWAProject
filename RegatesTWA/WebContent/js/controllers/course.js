monAppControllers.controller('CourseController', [
	'$scope', '$http', '$location', '$routeParams', function ($scope, $http, $location, $routeParams) {
		
		$scope.course = {};
		$scope.course.id = "";
		$scope.course.coefficient = 0;
		
		$scope.course.dateDebut = {};
		$scope.course.dateDebut.day = 0;
		$scope.course.dateDebut.month = 0;
		$scope.course.dateDebut.year = 0;
		$scope.course.dateDebut.hour = 0;
		$scope.course.dateDebut.min = 0;
		
		$scope.course.dateFin = {};
		$scope.course.dateFin.day = 0;
		$scope.course.dateFin.month = 0;
		$scope.course.dateFin.year = 0;
		$scope.course.dateFin.hour = 0;
		$scope.course.dateFin.min = 0;

		
		$scope.addCourse = function (){
			$http({
				method: 'POST',
				url: 'enregistrerCourse.htm',
				headers: {'Content-Type': 'application/json'},
				data:  $scope.course
			}).success(function (data){
				$scope.erreurs = data;
				if(data.res == "SUCCESS"){
					$scope.mess = "Course " + $scope.course.id + " enregistrée";
					$location.path("/regate/" + $routeParams.regateId);
				}
			});
		};
				
		$scope.initUpdate = function() {
			$http({
				method: 'GET',
				url: 'modifierCourse.htm',
				headers: {'Content-Type': 'application/json'},
				params: {idCourse: $routeParams.courseId}
			}).success(function (data) {
				$scope.course = data;
			});
		};
		
		$scope.updateCourse = function (){
			$http({
				method: 'POST',
				url: 'modifierCourse.htm',
				headers: {'Content-Type': 'application/json'},
				data:  $scope.course
			}).success(function (data){
				$scope.erreurs = data;
				if(data.res == "SUCCESS"){
					$scope.mess = "Course " + $scope.course.nom + " modifiée";
					$location.path("/regate/" + $routeParams.regateId);
				}
			});
		};
	}
]);
