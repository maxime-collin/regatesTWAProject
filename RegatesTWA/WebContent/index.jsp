<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="monApp">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Cache-Control" content="no-cache" />
				
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/menu.css" />
		<link rel="stylesheet" href="css/bateau.css" />
		
		<script type="text/javascript">
		
			function confirmeDelete(e, msg)
			{
				/* fonction utilisee dans les tableaux pour supprimer des elements
 				 * format des tableaux :
 	 			 * <tr onClick="document.location.href="showItem";>
 	 			 * 		<td>Item</td>
 	 			 *		<td onClick="return confirmeDelete(event, 'msg')">ButtonDelete</td>
 	 			 * </tr>
 				 */
				
				// on stoppe les evenement parents pour ne pas changer de page si on annule
				// ici c'est l'evenement de la balise <td> onClick="document.location.href="..."
				e.stopPropagation && e.stopPropagation() || (e.cancelBubble = true);

				// on affiche une popup de confirmation et on retourne son resultat
				return confirm(msg);
			}
		</script>

		<title>Projet TWA - Jean-sébastien Rolland &amp; Maxime Collin</title>
	</head>
	<body>
		<div class="container">
			<div id="menu" ng-include="'views/menu.html'"></div>
			<div id="corps" ng-view></div>
		</div>
		
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
		<script type="text/javascript" src="https://code.angularjs.org/1.3.14/angular-route.js"></script>

		<script type="text/javascript" src="js/app.js"></script>
		<script type="text/javascript" src="js/controllers/user.js"></script>
		<script type="text/javascript" src="js/controllers/regate.js"></script>
		<script type="text/javascript" src="js/controllers/course.js"></script>
		<script type="text/javascript" src="js/controllers/bateau.js"></script>
		
	</body>
</html>