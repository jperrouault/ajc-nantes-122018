package fr.codenames.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import fr.codenames.projection.Views;

@Entity
@Table(name = "partie")
public class Partie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PAR_ID")
	@JsonView(Views.Common.class)
	private int id;

	@OneToOne
	@JoinColumn(name = "PAR_GRILLE_ID")
	@JsonView(Views.Partie.class)
	private Grille grille;

	@ManyToOne
	@JoinColumn(name = "PAR_CAPITAINE_ID")
	@JsonView(Views.Partie.class)
	private Joueur capitaine;

	@Column(name = "PAR_EST_TERMINEE", nullable = false)
	@JsonView(Views.Partie.class)
	private boolean terminee;

	@OneToMany(mappedBy = "partie")
	private List<Participation> participations;

	@OneToMany(mappedBy = "partie")
	private List<Message> messages;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public Joueur getCapitaine() {
		return capitaine;
	}

	public void setCapitaine(Joueur capitaine) {
		this.capitaine = capitaine;
	}

	public boolean isTerminee() {
		return terminee;
	}

	public void setTerminee(boolean terminee) {
		this.terminee = terminee;
	}

	public List<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}