package fr.codenames.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import fr.codenames.projection.Views;

@Entity
@Table(name = "[case]")
public class Case {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CASE_ID")
	@JsonView(Views.Common.class)
	private int id;

	@Column(name = "CASE_COULEUR")
	@Enumerated(EnumType.ORDINAL)
	@JsonView({ Views.PlateauEspion.class, Views.CaseReponse.class })
	private Couleur couleur;

	@Column(name = "CASE_EST_REVELEE", nullable=false)
	@JsonView({ Views.Plateau.class, Views.CaseReponse.class })
	private boolean revelee;

	@ManyToOne
	@JoinColumn(name = "CASE_GRILLE_ID")
	private Grille grille;

	@ManyToOne
	@JoinColumn(name = "CASE_CARTE_ID")
	@JsonView({ Views.Plateau.class, Views.CaseReponse.class })
	private Carte carte;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}

	public boolean isRevelee() {
		return revelee;
	}

	public void setRevelee(boolean revelee) {
		this.revelee = revelee;
	}
	

	@JsonView(Views.PlateauJoueur.class)
	public Couleur getConditionalCouleur() {
		if (this.isRevelee()) {
			return this.getCouleur();
		}
		
		return null;
	}
}