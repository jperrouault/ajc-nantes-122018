package fr.loupgarou.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("0")
public class Administrateur extends Utilisateur {
	@Override
	public TypeUtilisateur getType() {
		return TypeUtilisateur.ADMINISTRATEUR;
	}
}