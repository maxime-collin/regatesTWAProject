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
    	
    	$scope.dateDebutPicker = {};
    	$scope.dateDebutPicker.day = 1;
    	$scope.dateDebutPicker.month = 1;
    	$scope.dateDebutPicker.year = 2000;
    	$scope.dateDebutPicker.hour = 1;
    	$scope.dateDebutPicker.min = 1;
    	
    	$scope.dateFinPicker = {};
    	$scope.dateFinPicker.day = 1;
    	$scope.dateFinPicker.month = 1;
    	$scope.dateFinPicker.year = 2000;
    	$scope.dateFinPicker.hour = 1;
    	$scope.dateFinPicker.min = 1;

		
		$scope.convertDate = function() {
        	var dayDebut = $scope.dateDebutPicker.day;
        	var monthDebut = $scope.dateDebutPicker.month;
        	var yearDebut = $scope.dateDebutPicker.year;
        	var hourDebut = $scope.dateDebutPicker.hour;
        	var minDebut = $scope.dateDebutPicker.min;
    		
    		$scope.course.dateDebut = new Date(yearDebut, monthDebut, dayDebut);
    		$scope.course.heureDebut = new Date(yearDebut, monthDebut, dayDebut, hourDebut, minDebut);
    		
    		
        	var dayFin = $scope.dateFinPicker.day;
        	var monthFin = $scope.dateFinPicker.month;
        	var yearFin = $scope.dateFinPicker.year;
        	var hourFin = $scope.dateFinPicker.hour;
        	var minFin = $scope.dateFinPicker.min;
    		
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
