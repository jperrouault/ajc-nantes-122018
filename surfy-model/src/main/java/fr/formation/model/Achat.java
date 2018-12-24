package fr.formation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name="achat")
public class Achat {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ACH_ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="ACH_COMMANDE_ID")
	private Commande commande;
	
	@ManyToOne
	@JoinColumn(name="ACH_PRODUIT_ID")
	private Produit produit;

	@Column(name="ACH_PRIXUNITAIRE", nullable=false)
	@Positive
	private float prixunitaire;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public float getPrixunitaire() {
		return prixunitaire;
	}

	public void setPrixunitaire(float f) {
		this.prixunitaire = f;
	}

	public int getQuantité() {
		return quantité;
	}

	public void setQuantité(int quantité) {
		this.quantité = quantité;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	@Column(name="ACH_QUANTITE")
	@NotEmpty
	@NotNull
	private int quantité;

	@Column(name="ACH_REMARQUE", columnDefinition="TEXT")
	private String remarque;

}
