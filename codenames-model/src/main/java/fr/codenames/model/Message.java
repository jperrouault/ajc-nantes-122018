package fr.codenames.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="message")
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MSG_ID")
	private int id;
	
	@Column(name="MSG_CONTENU", columnDefinition="TEXT", nullable=false)
	private String contenu;
	
	@ManyToOne
	@JoinColumn(name="MSG_PARTIE_ID")
	private Partie partie;
	
	@ManyToOne
	@JoinColumn(name="MSG_JOUEUR_ID")
	private Joueur joueur;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
}