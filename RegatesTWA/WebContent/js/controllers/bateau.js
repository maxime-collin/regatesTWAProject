/*
 * $scope.currentUser ???
 */

monAppControllers.controller('BateauController', [
	'$scope', '$http', '$location', '$routeParams', function ($scope, $http, $location, $routeParams) {
	    
	    $scope.bateau = {};
	    $scope.bateau.id = "";
	    $scope.bateau.numero = "";
	    $scope.bateau.nom = "";
	    $scope.bateau.type = "";
	    $scope.bateau.nationalite = "";
	    $scope.bateau.equipage = [];
	   
	    // liste des types a afficher dans le select
		$scope.types = [
		                  { label : '420', value : '420'},
		                  { label : '470', value : '470'},
		                  { label : '505', value : '505'},
		                  { label : 'Fireball', value : 'Fireball'},
		                  { label : 'Laser', value : 'Laser'},
		                  { label : 'Laser 5000', value : 'Laser5000 '},
		                  { label : 'Optimist', value : 'Optimist'},
		                  { label : 'Sprinto', value : 'Sprinto'},
		              ];
		// le type selectionne dans le cas d'une modification
		$scope.selectedType = {};
		$scope.selectedType.value = "";
		$scope.selectedType.label = "";
		
		// liste des types a afficher dans le select
		$scope.nationalites = [
		                  { label : 'Française', value : 'French'},
		                  { label : 'Anglaise', value : 'English'},
		              ];
		// le type selectionne dans le cas d'une modification
		$scope.selectedNationalite = {};
		$scope.selectedNationalite.value = "";
		$scope.selectedNationalite.label = "";
		
		$scope.listEquipiers = [];
		
		// selected equipiers
		$scope.equipierSelection = [];

		// helper method to get selected equipiers
		$scope.selectedEquipiers = function selectedEquipiers() {
			return filterFilter($scope.listEquipiers, { selected: true });
		};

		// watch listEquipiers for changes
		$scope.$watch('listEquipiers|filter:{selected:true}', function (nv) {
			$scope.equipierSelection = nv.map(function (equipier) {
				return equipier.numeroLicence;
			});
		}, true);
		
		
	    
	    // recupere la liste des users pour l'equipage
	    $scope.initForm = function() {
	    	$http({
				method: 'GET',
				url: 'listerUsers.htm',
				headers: {'Content-Type': 'application/json'},
			}).success(function (data) {
				$scope.listEquipiers = data;
				
				for(var i=0; i<$scope.listEquipiers.length; i++) {
					$scope.listEquipiers[i].selected = false;
					
					// on ajoute le capitaine (l'utilisateur courant, celui qui cree le bateau) dans l'equipage
					//if ($scope.users[i].id == $scope.currentUser.id)
						//$scope.bateau.equipage = [$scope.users[i]];
				}
			});
	    }
		
		$scope.addBateau = function () {
			$scope.bateau.type = $scope.selectedType.value;
			$scope.bateau.nationalite = $scope.selectedNationalite.value;
			$scope.bateau.equipage = $scope.equipierSelection;
			
			$http({
				method: 'POST',
				url: 'enregistrerBateau.htm',
				headers: {'Content-Type': 'application/json'},
				data:  $scope.bateau
			}).success(function (data){
				$scope.erreurs = data;
				if(data.res == "SUCCESS"){
					$scope.mess = "Bateau " + $scope.course.id + " enregistré";
					//$location.path("/user/" + $scope.currentUser.id);
				}
			});
		};
		
		// recupere le bateau avant de le modifier
		$scope.initUpdate = function() {
			$scope.initForm();
			
			$http({
				method: 'GET',
				url: 'modifierBateau.htm',
				headers: {'Content-Type': 'application/json'},
				params: {idBateau: $routeParams.bateauId}
			}).success(function (data) {
				$scope.bateau = data;
							
				// trouve le type selectionne
				for(var i=0; i<$scope.types.length; i++) {
					if($scope.types[i].value.valueOf() == $scope.regate.type.valueOf()) {
						$scope.selectedType = $scope.types[i];
					}
				}
				
				// trouve la nationalite selectionnee
				for(var i=0; i<$scope.nationalites.length; i++) {
					if($scope.nationalites[i].value.valueOf() == $scope.bateau.nationalite.valueOf()) {
						$scope.selectedNationalite = $scope.nationalites[i];
					}
				}				
			});
		};
		
		$scope.updateBateau = function (){
			$scope.bateau.type = $scope.selectedType.value;
			$scope.bateau.nationnalite = $scope.selectedNationnalite.value;
			$scope.bateau.equipage = $scope.equipierSelection;
			
			$http({
				method: 'POST',
				url: 'modifierBateau.htm',
				headers: {'Content-Type': 'application/json'},
				data:  $scope.bateau
			}).success(function (data){
				$scope.erreurs = data;
				if(data.res == "SUCCESS"){
					$scope.mess = "Bateau " + $scope.course.nom + " modifiée";
					$location.path("/user/" + $scope.currentUser.id);
				}
			});
		};
	}
]);