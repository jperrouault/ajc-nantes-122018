package fr.codenames.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import fr.codenames.projection.Views;

@Entity
@Table(name = "grille")
public class Grille {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GRI_ID")
	@JsonView(Views.Common.class)
	private int id;

	@Column(name = "GRI_DIFFICULE")
	@Enumerated(EnumType.ORDINAL)
	@JsonView({ Views.Grille.class, Views.Plateau.class })
	private Difficulte difficulte;

	@OneToOne(mappedBy = "grille")
	@JsonView(Views.Grille.class)
	private Partie partie;

	@OneToMany(mappedBy = "grille", cascade = CascadeType.PERSIST)
	@JsonView({ Views.Grille.class, Views.Plateau.class })
	private List<Case> cases;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Difficulte getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(Difficulte difficulte) {
		this.difficulte = difficulte;
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public List<Case> getCases() {
		return cases;
	}

	public void setCases(List<Case> cases) {
		this.cases = cases;
	}

	public void addCarte(Carte carte, Couleur couleur) {
		Case myCase = new Case();

		if (this.cases == null) {
			this.cases = new ArrayList<Case>();
		}

		myCase.setCouleur(couleur);
		myCase.setGrille(this);
		myCase.setCarte(carte);

		this.cases.add(myCase);
	}

	public List<Couleur> generate() {
		List<Couleur> myCouleurs = new ArrayList<Couleur>();

		myCouleurs.add(Couleur.NOIRE);
		for (int i = 0; i < 7; i++) {
			myCouleurs.add(Couleur.NEUTRE);
		}

		for (int i = 0; i < 8; i++) {
			myCouleurs.add(Couleur.ROUGE);
		}

		for (int i = 0; i < 9; i++) {
			myCouleurs.add(Couleur.BLEUE);
		}

		return myCouleurs;
	}
}