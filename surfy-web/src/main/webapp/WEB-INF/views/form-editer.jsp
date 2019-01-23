<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>LE SUPERBE TITRE ICI</title>
	</head>
	
	<body>
		<form method="POST" >
			<input type="hidden" name="id" value="${ produit.id }" />
			
			<div>
				<label>Nom du produit</label>
				<input type="text" name="modele" value="${ produit.modele }" />
			</div>
			
			<div>
				<label>Prix du produit</label>
				<input type="number" name="prix" value="${ produit.prix }" step="0.1" />
			</div>
			
			<div>
				<label>Fournisseur</label>
				<select name="fournisseur.id">
					<option value="1" <c:if test="${ produit.fournisseur.id == 1 }">selected</c:if>>Amazon</option>
					<option value="2" <c:if test="${ produit.fournisseur.id == 2 }">selected</c:if>>GoPRO</option>
				</select>
			</div>
			
			<div>
				<input type="submit" value="OK !" />
			</div>
		</form>
	</body>
</html>