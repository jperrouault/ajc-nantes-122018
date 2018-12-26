package fr.loupgarou.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="partie")
public class Partie {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PAR_ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="PAR_HISTOIRE_ID")
	private Histoire histoire;
	
	@ManyToOne
	@JoinColumn(name="PAR_CAPITAINE_ID")
	private Joueur capitaine;
	
	@OneToMany(mappedBy="partie")
	private List<Participation> participations;
	
	@OneToMany(mappedBy="partie")
	private List<Message> messages;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Histoire getHistoire() {
		return histoire;
	}

	public void setHistoire(Histoire histoire) {
		this.histoire = histoire;
	}

	public Joueur getCapitaine() {
		return capitaine;
	}

	public void setCapitaine(Joueur capitaine) {
		this.capitaine = capitaine;
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