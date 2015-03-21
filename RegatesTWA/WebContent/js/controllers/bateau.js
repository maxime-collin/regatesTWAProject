/*
 * $scope.currentUser ???
 */

monAppControllers.controller('BateauController', [
	'$scope', '$http', '$location', '$routeParams', function ($scope, $http, $location, $routeParams) {

	    $scope.users = [];
	    
	    $scope.bateau = {};
	    $scope.bateau.id = "";
	    $scope.bateau.numero = "";
	    $scope.bateau.nom = "";
	    $scope.bateau.capitaine = $scope.currentUser;
	    $scope.bateau.type = "";
	    $scope.bateau.nationalite = "";
	    $scope.bateau.equipage =  [];
	   
	    // liste des types a afficher dans le select
		$scope.types = [
		                  { label : 'Inter-Séries', value : 'Inter-Series'},
		                  { label : '420', value : '420'},
		                  { label : '470', value : '470'},
		                  { label : '505', value : '505'},
		                  { label : 'Fireball', value : 'Fireball'},
		                  { label : 'Laser', value : 'Laser'},
		                  { label : 'Laser 5000', value : 'Laser '},
		                  { label : 'Optimist', value : 'Optimist'},
		                  { label : 'Sprinto', value : 'Sprinto'},
		              ];
		// le type selectionne dans le cas d'une modification
		$scope.selectedType = {};
		
		// liste des types a afficher dans le select
		$scope.nationalites = [
		                  { label : 'Française', value : 'French'},
		                  { label : 'Anglaise', value : 'English'},
		              ];
		// le type selectionne dans le cas d'une modification
		$scope.selectedNationalite = {};
	    
	    // recupere la liste des users pour l'equipage
	    $scope.initForm = function() {
	    	$http({
				method: 'GET',
				url: 'listerUsers.htm',
				headers: {'Content-Type': 'application/json'},
			}).success(function (data) {
				$scope.users = data;
				
				for(var i=0; i<$scope.users.length; i++) {
					// ajoute un champ userName au user pour l'affichage dans le form
					$scope.users[i].userName = $scope.users[i].prenom + " " + $scope.users[i].nom;
					
					// on ajoute le capitaine (l'utilisateur courant, celui qui cree le bateau) dans l'equipage
					if ($scope.users[i].id == $scope.currentUser.id)
						$scope.bateau.equipage = [$scope.users[i]];
				}
			});
	    }
		
		$scope.addBateau = function (){
			$http({
				method: 'POST',
				url: 'enregistrerBateau.htm',
				headers: {'Content-Type': 'application/json'},
				data:  $scope.bateau
			}).success(function (data){
				$scope.erreurs = data;
				if(data.res == "SUCCESS"){
					$scope.mess = "Bateau " + $scope.course.id + " enregistré";
					$location.path("/user/" + $scope.currentUser.id);
				}
			});
		};
		
		// recupere le bateau avant de le modifier
		$scope.initUpdate = function() {
			$http({
				method: 'GET',
				url: 'modifierBateau.htm',
				headers: {'Content-Type': 'application/json'},
				params: {idBateau: $routeParams.bateauId}
			}).success(function (data) {
				$scope.bateau = data;
							
				// trouve le type selectionne
				for(var i=0; i<$scope.types.length; i++) {
					if($scope.types[i].label.valueOf() == $scope.regate.type.valueOf()) {
						$scope.selectedType = $scope.types[i];
					}
				}
				
				// trouve la nationalite selectionnee
				for(var i=0; i<$scope.nationalites.length; i++) {
					if($scope.nationalites[i].label.valueOf() == $scope.bateau.nationalite.valueOf()) {
						$scope.selectedNationalite = $scope.nationalites[i];
					}
				}				
			});
		};
		
		$scope.updateBateau = function (){
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


/* @Src : http://blog.boxelderweb.com/2013/08/22/angularjs-multi-select-widget/
 * @Author : Alec LaLonde
 */
monAppControllers.directive('multiSelect', function($q) {
  return {
    restrict: 'E',
    require: 'ngModel',
    scope: {
      selectedLabel: "@",
      availableLabel: "@",
      displayAttr: "@",
      available: "=",
      model: "=ngModel"
    },
    template: '<div class="multiSelect">' + 
                '<div class="select">' + 
                  '<label class="control-label" for="multiSelectSelected">{{ selectedLabel }} ' +
                      '({{ model.length }})</label>' +
                  '<select id="equipage" ng-model="selected.current" multiple ' + 
                      'class="pull-left" ng-options="e as e[displayAttr] for e in model">' + 
                      '</select>' + 
                '</div>' + 
                '<div class="select buttons">' + 
                  '<button class="btn mover left" ng-click="add()" title="Add selected" ' + 
                      'ng-disabled="selected.available.length == 0">' + 
                    '<i class="icon-arrow-left"></i>' + 
                  '</button>' + 
                  '<button class="btn mover right" ng-click="remove()" title="Remove selected" ' + 
                      'ng-disabled="selected.current.length == 0">' + 
                    '<i class="icon-arrow-right"></i>' + 
                  '</button>' +
                '</div>' + 
                '<div class="select">' +
                  '<label class="control-label" for="multiSelectAvailable">{{ availableLabel }} ' +
                      '({{ available.length }})</label>' +
                  '<select id="multiSelectAvailable" ng-model="selected.available" multiple ' +
                      'ng-options="e as e[displayAttr] for e in available"></select>' +
                '</div>' +
              '</div>',
    link: function(scope, elm, attrs) {
      scope.selected = {
        available: [],
        current: []
      };

      /* Handles cases where scope data hasn't been initialized yet */
      var dataLoading = function(scopeAttr) {
        var loading = $q.defer();
        if(scope[scopeAttr]) {
          loading.resolve(scope[scopeAttr]);
        } else {
          scope.$watch(scopeAttr, function(newValue, oldValue) {
            if(newValue !== undefined)
              loading.resolve(newValue);
          });  
        }
        return loading.promise;
      };

      /* Filters out items in original that are also in toFilter. Compares by reference. */
      var filterOut = function(original, toFilter) {
        var filtered = [];
        angular.forEach(original, function(entity) {
          var match = false;
          for(var i = 0; i < toFilter.length; i++) {
            if(toFilter[i][attrs.displayAttr] == entity[attrs.displayAttr]) {
              match = true;
              break;
            }
          }
          if(!match) {
            filtered.push(entity);
          }
        });
        return filtered;
      };

      scope.refreshAvailable = function() {
        scope.available = filterOut(scope.available, scope.model);
        scope.selected.available = [];
        scope.selected.current = [];
      }; 

      scope.add = function() {
        scope.model = scope.model.concat(scope.selected.available);
        scope.refreshAvailable();
      };
      scope.remove = function() {
        scope.available = scope.available.concat(scope.selected.current);
        scope.model = filterOut(scope.model, scope.selected.current);
        scope.refreshAvailable();
      };

      $q.all([dataLoading("model"), dataLoading("available")]).then(function(results) {
        scope.refreshAvailable();
      });
    }
  };
})
