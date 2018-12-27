package fr.codenames.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="participation")
public class Participation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name="PART_PARTIE_ID")
	private Partie partie;
	
	@Id
	@ManyToOne
	@JoinColumn(name="PART_JOUEUR_ID")
	private Joueur joueur;
	
	@Column(name="PART_ROLE_ID")
	@Enumerated(EnumType.ORDINAL)
	private Role role;

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}