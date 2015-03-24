monAppControllers.controller('RegateController', [
    '$scope', '$http', '$location', '$routeParams', function($scope, $http, $location, $routeParams) {
    	    	
    	$scope.regate = {};
    	$scope.regate.id = "";
    	$scope.regate.nom = "toto";
    	$scope.regate.niveau = "";
    	$scope.regate.type = "";
    	$scope.regate.description = "";
    	$scope.regate.dateDebut = new Date();
    	$scope.regate.heureDebut = new Date();
    	$scope.regate.dateFin = new Date();
    	$scope.regate.heureFin = new Date();
    	
    	$scope.regate.dateDebutPicker = {};
    	$scope.regate.dateDebutPicker.day = 1;
    	$scope.regate.dateDebutPicker.month = 1;
    	$scope.regate.dateDebutPicker.year = 2000;
    	$scope.regate.dateDebutPicker.hour = 1;
    	$scope.regate.dateDebutPicker.min = 1;
    	
    	$scope.regate.dateFinPicker = {};
    	$scope.regate.dateFinPicker.day = 1;
    	$scope.regate.dateFinPicker.month = 1;
    	$scope.regate.dateFinPicker.year = 2000;
    	$scope.regate.dateFinPicker.hour = 1;
    	$scope.regate.dateFinPicker.min = 1;
    	
    	$scope.regate.inscrBateaux = [];
    	
    	// liste des niveaux a afficher dans le select    	
    	$scope.niveaux = [
    	                  {label : 'Départementale', value : 'Departementale' },
    	                  {label : 'Ligue', value : 'Ligue'},
    	                  {label : 'Inter-Ligue', value : 'InterLigue'},
    	                  {label : 'Mondiale', value : 'Mondiale'},
    	];    	
    	// le niveau selectionne dans le cas d'une modification
    	$scope.selectedNiveau = "";
    	    	
    	// liste des types a afficher dans le select
    	$scope.types = [ 
    	                 {label : 'Inter-Séries', value : 'InterSeries'},
    	                 {label : '420', value : '420'},
    	                 {label : '470', value : '470'},
    	                 {label : '505', value : '505'},
    	                 {label : 'Fireball', value : 'Fireball'},
    	                 {label : 'Laser', value : 'Laser'},
    	                 {label : 'Laser 5000', value : 'Laser5000'},
    	                 {label : 'Optimist', value : 'Optimist'},
    	                 {label : 'Sprinto', value : 'Sprinto'},
    	];
    	// le type selectionne dans le cas d'une modification
    	$scope.selectedType = "";
    	
    	// A CHANGER
    	$scope.$watch('$viewContentLoaded', function() {
    		$http({
    			method : 'GET',
    			url : 'listerRegates.htm',
    			headers : { 'Content-Type' : 'application/json' },
    		}).success(function(data) {
    			$scope.listeRegates = data;
    		});
    	});
    	
    	// recupere la liste des courses et des bateaux de la regate
    	$scope.initVisu = function() {
    		$http({
    			method : 'GET',
    			url : 'modifierRegate.htm',
    			headers : { 'Content-Type' : 'application/json' },
    			params : { id : $routeParams.regateId }
    		}).success(function(data) {
    				$scope.regate = data;
    		});
    		
    		$http({
    			method : 'GET',
    			url : 'listerRegateCourses.htm',
    			headers : { 'Content-Type' : 'application/json' },
    			params : { id : $routeParams.regateId }
    		}).success(function(data) {
    			$scope.listeCourses = data;
    		});
    		
    		$http({
    			method : 'GET',
    			url : 'listerRegateBateaux.htm',
    			headers : { 'Content-Type' : 'application/json' },
    			params : { id : $routeParams.regateId }
    		}).success(function(data) {
    			$scope.listeBateaux = data;
    		});
    	};
    	
    	// recupere la liste des bateaux de l'utilisateur pour les afficher dans le select
    	$scope.initInscription = function() {
    		// recupere la liste des bateaux inscrit a la regate pour les comparer avec ceux du user
    		$scope.initVisu();
    		$http({
    			method : 'GET',
    			url : 'listerUserBateaux.htm',
    			headers : { 'Content-Type' : 'application/json' },
    			params : { id : $scope.currentUser.id }
    		}).success(function(data) {
    					$scope.userBateaux = data;
    					// rajoute un label pour
    					// l'affichage dans le
    					// select
    					for (var i = 0; i < $scope.userBateaux.length; i++)
    						$scope.userBateaux[i].label = 	$scope.userBateaux[i].numero
    														+ " " + $scope.userBateaux[i].nom
    														+ " " + $scope.userBateaux[i].type;
    					
    					$scope.selectedBateau = {};
    					
    					// cherche si le user a déjà inscrit un de ses bateaux
    					for (var indexBateauxRegate = 0; indexBateauxRegate < $scope.listeBateaux.length; indexBateauxRegate++) {
    						
    						for (var indexBateauxUser = 0; indexBateauxUser < $scope.currentUser.bateaux.length; indexBateauxUser++) {
    							
    							if ($scope.listeBateaux[indexBateauxRegate].id == $scope.currentUser.bateaux[indexBateauxUser].id)
    								$scope.selectedBateau = $scope.currentUser.bateaux[indexBateauxUser];
    						}
    					}
    				});
    	};
    	
    	$scope.redirectToVisuRegate = function(regate) {
    		$location.path("/regate/" + regate.id);
    	};
    	
    	$scope.redirectToInscription = function(regate) {
    		$location.path("/regate/" + regate.id + "/inscription");
    	};
    	
    	$scope.redirectToAddRegate = function() {
    		$location.path("/new-regate");
    	};
    	
    	$scope.redirectToUpdateRegate = function(regate) {
    		$location.path("/update-regate/" + regate.id);
    	};
    	
    	$scope.redirectToAddCourse = function(regate, course) {
    		$location.path("/new-course/" + regate.id);
    	};
    	
    	$scope.redirectToUpdateCourse = function(regate, course) {
    		$location.path("/update-course/" + regate.id + "/" + course.id);
    	};
    	
    	$scope.convertDate = function() {
        	var dayDebut = $scope.regate.dateDebutPicker.day;
        	var monthDebut = $scope.regate.dateDebutPicker.month;
        	var yearDebut = $scope.regate.dateDebutPicker.year;
        	var hourDebut = $scope.regate.dateDebutPicker.hour;
        	var minDebut = $scope.regate.dateDebutPicker.min;
    		
    		$scope.regate.dateDebut = new Date(yearDebut, monthDebut, dayDebut);
    		$scope.regate.heureDebut = new Date(yearDebut, monthDebut, dayDebut, hourDebut, minDebut);
    		
    		
        	var dayFin = $scope.regate.dateFinPicker.day;
        	var monthFin = $scope.regate.dateFinPicker.month;
        	var yearFin = $scope.regate.dateFinPicker.year;
        	var hourFin = $scope.regate.dateFinPicker.hour;
        	var minFin = $scope.regate.dateFinPicker.min;
    		
    		$scope.regate.dateFin = new Date(yearFin, monthFin, dayFin);
    		$scope.regate.heureFin = new Date(yearFin, monthFin, dayFin, hourFin, minFin);
    	};
    	
    	$scope.addRegate = function() {
    		$scope.convertDate();
    		$scope.regate.niveau = $scope.selectedNiveau;
    		$scope.regate.type = $scope.selectedType;

    		$http({
    			method : 'POST',
    			url : 'enregistrerRegate.htm',
    			headers : { 'Content-Type' : 'application/json' },
    			data : $scope.regate
    		}).success(function(data) {
    					$scope.erreurs = data;
    					if (data.res == "SUCCESS") {
    						$scope.mess = "Regate " + $scope.regate.nom + " enregistrée";
    						$location.path("/regates");
    					}
    				});
    		
    	};
    	
    	// recupere la regate que l'on veut modifier
    	/*$scope.initUpdate = function() {
    		$http({
    			method : 'GET',
    			url : 'modifierRegate.htm',
    			headers : { 'Content-Type' : 'application/json' },
    			params : { id : $routeParams.regateId }
    		}).success(function(data) {
    					$scope.regate = data;
    					
    					// trouve le niveau selectionne
    					for (var i = 0; i < $scope.niveaux.length; i++) {
    						if ($scope.niveaux[i].label.valueOf() == $scope.regate.niveau.valueOf())
    							$scope.selectedNiveau = $scope.niveaux[i];
    					}
    					
    					// trouve le type selectionne
    					for (var i = 0; i < $scope.types.length; i++) {
    						if ($scope.types[i].label.valueOf() == $scope.regate.type.valueOf())
    							$scope.selectedType = $scope.types[i];
    					}
    				});
    	};*/
    	
    	$scope.updateRegate = function() {
    		$http({
    			method : 'POST',
    			url : 'modifierRegate.htm',
    			headers : { 'Content-Type' : 'application/json' },
    			data : $scope.regate
    		}).success(function(data) {
    					$scope.erreurs = data;
    					
    					if (data.res == "SUCCESS") {
    						$scope.mess = "Regate " + $scope.regate.nom + " modifiée";
    						$location.path("/regates");
    					}
    				});
    	};
    	
    	$scope.deleteRegate = function(regate) {
    		$http({
    			method : 'POST',
    			url : 'supprimerRegate.htm',
    			headers : { 'Content-Type' : 'application/json' },
    			data : regate
    		}).success(	function(data) {
    					$scope.erreurs = data;
    					if (data.res == "SUCCESS") {
    						$scope.mess = "Regate "  + $scope.regate.nom + " supprimée";
    						window.location.reload();
    					}
    				});
    	};
    	
    	// supprime une course de la regate
    	$scope.deleteCourseToRegate = function(course) {
    		$http({
    			method : 'POST',
    			url : 'supprimerCourse.htm',
    			headers : { 'Content-Type' : 'application/json' },
    			data : course
    		}).success(function(data) {
    					$scope.erreurs = data;
    					if (data.res == "SUCCESS") {
    						$scope.mess = "Course " + course.id + " supprimée";
    						window.location.reload();
    					}
    				});
    	};
    	
    	// ouvre l'URL d'un equiper dans un onglet separe
    	$scope.openEquipierURL = function(equiper) {
    		if (equiper.url != '')
    			window.open(equipier.url, '_blank');
    	};
    	
    	// supprime un bateau de la regate
    	$scope.deleteBateauToRegate = function(regate, bateau) {
    		dataBateau = { regate : regate, bateau : bateau };
    		$http({
    			method : 'POST',
    			url : 'supprimerBateauToRegate.htm',
    			headers : { 'Content-Type' : 'application/json' },
    			data : dataBateau
    		}).success( function(data) {
    					$scope.erreurs = data;
    					if (data.res == "SUCCESS") {
    						$scope.mess = "Bateau " + bateau.numero + " supprimé";
    						window.location.reload();
    					}
    				});
    	};
    } 
]);