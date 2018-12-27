package fr.codenames.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("1")
public class Joueur extends Utilisateur {
	@Column(name="UTI_PSEUDO", length=150, nullable=false)
	@NotNull
	@NotEmpty
	@Size(max=150)
	private String pseudo;
	
	@Column(name="UTI_EST_BANNI", nullable=false)
	private boolean banni;

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public boolean isBanni() {
		return banni;
	}

	public void setBanni(boolean banni) {
		this.banni = banni;
	}

	@Override
	public TypeUtilisateur getType() {
		return TypeUtilisateur.JOUEUR;
	}
}