monAppControllers.controller('UserController', [
	'$scope', '$http', '$location', '$routeParams', function ($scope, $http, $location, $routeParams) {
		
		$scope.user = {};
		$scope.user.id = "";
		$scope.user.numeroLicence = "";
		$scope.user.nom = "";
		$scope.user.prenom = "";
		$scope.user.url = "";
		$scope.user.identifiant = "";
		$scope.user.mdp = "";
		
		$scope.connection = {};
		$scope.connection.identifiant = "";
		$scope.connection.password = "";
		
		$scope.$watch('$viewContentLoaded', function(){
			$http({
				method: 'GET',
				url: 'listerUsers.htm',
				headers: {'Content-Type': 'application/json'},
			}).success(function (data) {
				$scope.listeUsers = data;
			});
		 });
		
		$scope.connect = function() {
			console.log("connect envoyé");
			console.log(JSON.parse(JSON.stringify($scope.connection)));
			$http({
				method: 'POST',
				url: 'connectUser.htm',
				headers: {'Content-Type': 'application/json'},
				data:  $scope.connection
			}).success(function (data) {
				AuthenticateJS.getLoggedinUser();
				$scope.erreurs = data;
				if(data.res == "SUCCESS"){
					$scope.mess = "User " + $scope.user.nom + " connecté";
				}
			});
		};
		
		$scope.logout = function() {
			AuthenticateJS.logout();
			$location.path("/connect");
		};
		
		$scope.redirectToAddUser = function() {
			$location.path("/new-user");
		};
		
		$scope.deleteUser = function(u){
			$http({
				method: 'POST',
				url: 'supprimerUser.htm',
				headers: {'Content-Type': 'application/json'},
				data:  u
			}).success(function (data) {
				$scope.erreurs = data;
				if(data.res == "SUCCESS"){
					$scope.mess = "User " + $scope.user.nom + " supprimé";
				}				
			});
			
			window.location.reload();
		};
		
		$scope.redirectToUpdateUser = function(user) {
			$location.path("/update-user/"+user.id);
		};
		
		$scope.initUpdate = function() {
			$http({
				method: 'GET',
				url: 'modifierUser.htm',
				headers: {'Content-Type': 'application/json'},
				params: {id: $routeParams.userId}
			}).success(function (data) {
				$scope.user = data;
			});
		};
		
		$scope.updateUser = function (){
			$http({
				method: 'POST',
				url: 'modifierUser.htm',
				headers: {'Content-Type': 'application/json'},
				data:  $scope.user
			}).success(function (data){
				$scope.erreurs = data;
				if(data.res == "SUCCESS"){
					$scope.mess = "User " + $scope.user.nom + " modifié";
					$location.path("/users");
				}
			});
		};
	
		$scope.openUserURL = function(user) {
			window.open(
					user.url,
					'_blank');
		};
		
		$scope.addUser = function (){
			$http({
				method: 'POST',
				url: 'enregistrerUser.htm',
				headers: {'Content-Type': 'application/json'},
				data:  $scope.user
			}).success(function (data){
				$scope.erreurs = data;
				if(data.res == "SUCCESS"){
					$scope.mess = "User " + $scope.user.nom + " enregistré";
					$location.path("/users");
				}
			});

			
		};
	}
]);