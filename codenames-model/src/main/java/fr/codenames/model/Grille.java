package fr.codenames.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "grille")
public class Grille {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GRI_ID")
	private int id;
	
	@Column(name = "GRI_DIFFICULE")
	@Enumerated(EnumType.ORDINAL)
	private Difficulte difficulte;
	
	@OneToMany(mappedBy="grille")
	private List<Case> cases;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Case> getCases() {
		return cases;
	}

	public void setCases(List<Case> cases) {
		this.cases = cases;
	}

	public Difficulte getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(Difficulte difficulte) {
		this.difficulte = difficulte;
	}
}