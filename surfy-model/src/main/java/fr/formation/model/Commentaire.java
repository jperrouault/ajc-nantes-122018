package fr.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name="commentaire")
public class Commentaire {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COM_ID")
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Evenement getEvenement() {
		return evenement;
	}

	public void setEvenement(Evenement evenement) {
		this.evenement = evenement;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@ManyToOne
	@JoinColumn(name="COM_PRODUIT_ID")
	private Produit produit;

	@ManyToOne
	@JoinColumn(name="COM_EVENEMENT_ID")
	private Evenement evenement;

	@ManyToOne
	@JoinColumn(name="COM_CLIENT_ID")
	private Client client;

	@Column(name="COM_NOTE")
	@NotEmpty
	@NotNull
	private int note;

	@Column(name="COM_COMMENTAIRE",columnDefinition="TEXT")
	@Positive
	private String commentaire;

	@Column(name="COM_PHOTO")
	private String photo;

}
