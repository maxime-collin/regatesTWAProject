monAppControllers.controller('CourseController', [
	'$scope', '$http', '$location', '$routeParams', function ($scope, $http, $location, $routeParams) {
		
		$scope.course = {};
		$scope.course.id = "";
		$scope.course.numero = 0;
		$scope.course.coefficient = 0;
    	$scope.course.dateDebut = new Date();
    	$scope.course.heureDebut = new Date();
    	$scope.course.dateFin = new Date();
    	$scope.course.heureFin = new Date();
    	
    	$scope.course.dateDebutPicker = {};
    	$scope.course.dateDebutPicker.day = 1;
    	$scope.course.dateDebutPicker.month = 1;
    	$scope.course.dateDebutPicker.year = 2000;
    	$scope.course.dateDebutPicker.hour = 1;
    	$scope.course.dateDebutPicker.min = 1;
    	
    	$scope.course.dateFinPicker = {};
    	$scope.course.dateFinPicker.day = 1;
    	$scope.course.dateFinPicker.month = 1;
    	$scope.course.dateFinPicker.year = 2000;
    	$scope.course.dateFinPicker.hour = 1;
    	$scope.course.dateFinPicker.min = 1;

		
		$scope.convertDate = function() {
        	var dayDebut = $scope.course.dateDebutPicker.day;
        	var monthDebut = $scope.course.dateDebutPicker.month;
        	var yearDebut = $scope.course.dateDebutPicker.year;
        	var hourDebut = $scope.course.dateDebutPicker.hour;
        	var minDebut = $scope.course.dateDebutPicker.min;
    		
    		$scope.course.dateDebut = new Date(yearDebut, monthDebut, dayDebut);
    		$scope.course.heureDebut = new Date(yearDebut, monthDebut, dayDebut, hourDebut, minDebut);
    		
    		
        	var dayFin = $scope.course.dateFinPicker.day;
        	var monthFin = $scope.course.dateFinPicker.month;
        	var yearFin = $scope.course.dateFinPicker.year;
        	var hourFin = $scope.course.dateFinPicker.hour;
        	var minFin = $scope.course.dateFinPicker.min;
    		
    		$scope.course.dateFin = new Date(yearFin, monthFin, dayFin);
    		$scope.course.heureFin = new Date(yearFin, monthFin, dayFin, hourFin, minFin);
    	};
		
		$scope.addCourse = function (){
    		$scope.convertDate();
			
			$http({
				method: 'POST',
				url: $routeParams.regateId + '/enregistrerCourse.htm',
				headers: {'Content-Type': 'application/json'},
				data: $scope.course
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
