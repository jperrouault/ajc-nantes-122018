package fr.loupgarou.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "organisation")
public class Organisation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "ORG_HISTOIRE_ID")
	private Histoire histoire;

	@Id
	@ManyToOne
	@JoinColumn(name = "ORG_PERSONNAGE_ID")
	private Personnage personnage;

	@Column(name = "ORG_ORDRE", nullable = false)
	private int ordre;

	public Histoire getHistoire() {
		return histoire;
	}

	public void setHistoire(Histoire histoire) {
		this.histoire = histoire;
	}

	public Personnage getPersonnage() {
		return personnage;
	}

	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}

	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}
}