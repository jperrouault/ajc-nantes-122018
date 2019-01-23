<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>LA LISTE DES PRODUITS</title>
	</head>
	
	<body>
		<h1>Voici tous nos produits</h1>
		<small><a href="ajouter">Ajouter un produit</a></small>
		
		<table>
			<thead>
				<tr>
					<th>Nom</th>
					<th>Prix</th>
					<th>Actions</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${ produits }" var="produit">
					<tr>
						<td>${ produit.modele }</td>
						<td>${ produit.prix }</td>
						<td>
							<a href="supprimer?id=${ produit.id }">SUPPRIMER</a>
							<a href="editer?id=${ produit.id }">EDITER</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>